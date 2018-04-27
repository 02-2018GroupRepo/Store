package bootcamp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import bootcamp.Payment;
import bootcamp.model.inventory.InventoryItem;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import groovy.util.MapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import bootcamp.model.products.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InvoiceService invoiceService;


    @Value("${vendor-a.url}")
    private String vendor1;

/*    @Value("${supplier-b.url}")
    private String vendor2;

    @Value("${supplier-c.url}")
    private String vendor3;*/

    @Autowired
    private List<Product> inventoryList;
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private Map<Integer, Integer> inv;

    @Autowired
    private SimpleDateFormat dateFormat;

    public void receiveInventory(List<Product> products) {
        inventoryList.addAll(products);
    }

    public List<Product> getInventory() {
        return inventoryList;
    }

    @Scheduled(cron = "${inventory.status.schedule}")
    public void inventoryStatus() {
        log.info("Checking on inventory status at {}", dateFormat.format(new Date()));
        log.debug("Debug goes here");
        checkInventory();
    }

    private void checkInventory() {
        for (Map.Entry<Integer, Integer> m : inv.entrySet()) {
            if (m.getValue() < 4) {

                sendCalls(m.getKey());
            }
        }
    }

    @SuppressWarnings("")
    private void sendCalls(int id) {

        CompletableFuture<InventoryItem> callVendor1;
        CompletableFuture<InventoryItem> callVendor2;
        CompletableFuture<InventoryItem> callVendor3;
        Map<String, BigDecimal> futures = new HashMap();


        try {
            callVendor1 = getInventoryItem(vendor1, id);
            if (callVendor1.get() != null && callVendor1.get().getNumber_available() >= 5) {
                futures.put(vendor1, callVendor1.get().getRetail_price());

            }

        } catch (Exception e) {
            String blank = "blank";
        }
/*        try {
            callVendor2 = getInventoryItem(vendor2, id);
            if (callVendor2.get() != null && callVendor2.get().getNumber_available() >= 5)
                futures.put(vendor2, callVendor2.get().getRetail_price());
        } catch (Exception e) {
            String no = "no";
        }
        try {
            callVendor3 = getInventoryItem(vendor3, id);
            if (callVendor3.get() != null && callVendor3.get().getNumber_available() >= 5)
                futures.put(vendor3, callVendor3.get().getRetail_price());
        } catch (Exception e) {
            String nothing = "nothing";
        }*/
        if (futures.isEmpty())
            return;

        Map.Entry<String, BigDecimal> lowest = new MapEntry("", new BigDecimal(1000000));
        for (Map.Entry<String, BigDecimal> future : futures.entrySet()) {
            lowest =
                    lowest.getValue().doubleValue() < future.getValue().doubleValue() ? lowest : future;

        }


        Payment payment = sendOrderAndReturnPayment(id, lowest.getKey());
        Boolean response = restTemplate.postForObject(lowest.getKey() + "/payment", payment, Boolean.class);
        if (response)
            log.info(lowest.getKey() + " Were paid for Product id " + id);
    }

    @Async
     Payment sendOrderAndReturnPayment(int id, String key) {

        Order order = new Order(id, 5);

        Invoice invoiceItem = restTemplate.postForObject(key + "/order", order, Invoice.class);
        double returned = invoiceService.processInvoice(invoiceItem);

        return new Payment(new BigDecimal(returned).setScale(2, BigDecimal.ROUND_DOWN),
                invoiceItem.getInvoiceId());


    }


    @Async
    CompletableFuture<InventoryItem> getInventoryItem(String vendorUrl, int id) throws Exception {
        String getById = vendorUrl + "/inventory/" + id;
        InventoryItem results = restTemplate.getForObject(getById, InventoryItem.class);

        return CompletableFuture.completedFuture(results);
    }
}
