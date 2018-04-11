package bootcamp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import bootcamp.model.inventory.Inventory;
import bootcamp.model.inventory.InventoryItem;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bootcamp.model.products.Product;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${supplier-a.url}")
    String vendor1;

    @Value("${supplier-b.url}")
    String vendor2;

    @Value("${supplier-c.url}")
    String vendor3;

    @Autowired
    private List<Product> inventoryList;
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    Map<Integer, Integer> inv;

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
            if (m.getValue() < 2)
                sendCalls(m.getKey());
        }
    }

    private void sendCalls(int id) {

        CompletableFuture<InventoryItem> callVendor1;
        CompletableFuture<InventoryItem> callVendor2;
        CompletableFuture<InventoryItem> callVendor3;
        ArrayList<InventoryItem> futures = new ArrayList<>();

        try {
            callVendor1 = getInventoryItem(vendor1,id);
            futures.add(callVendor1.get());
        } catch (Exception e) {
            log.info("Vendor 1 failed");
        }
        try {
            callVendor2 = getInventoryItem(vendor1, id);
            futures.add( callVendor2.get());

        } catch (Exception e) {
            log.info("Vendor 2 failed");

        }
        try {
            callVendor3 = getInventoryItem(vendor1, id);
            futures.add(callVendor3.get());

        } catch (Exception e) {
            log.info("Vendor 3 failed");


        }

 //       CompletableFuture.allOf(callVendor1, callVendor2, callVendor3).join();

//        for (Map.Entry<Integer, Integer> m : inv.entrySet()) {
//            if (m.getValue() < 2)
//                for (List l : futures) {
//                    for(InventoryItem ii : futures)
//                }
//        }




    }

    @Async
    private CompletableFuture<InventoryItem> getInventoryItem(String vendorUrl, int id) throws InterruptedException {
        log.info("getting a product");
        String getById = vendorUrl + "/inventory/"+id;
        InventoryItem results = restTemplate.getForObject(getById, InventoryItem.class);


        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

}
