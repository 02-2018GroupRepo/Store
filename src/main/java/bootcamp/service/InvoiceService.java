package bootcamp.service;

import bootcamp.model.invoice.Invoice;
import bootcamp.model.invoice.InvoiceItem;
import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class InvoiceService {


    @Autowired
    List<Product> products;

    @Autowired
    Map<Integer, Integer> inv;

    @Autowired
    @Qualifier("Stock")
    double stock;
    @Autowired
    RestTemplate restTemplate;

    public double processInvoice(Invoice invoiceItem) {
        BigDecimal price = invoiceItem.getProduct().getRetail_price();
        double statement = price.doubleValue() * invoiceItem.getCount();

        updateInventory(invoiceItem.getProduct().getId(), price, invoiceItem.getCount());
        //TODO: update store revenue
        
        return statement;
    }

    private void updateInventory(int id, BigDecimal price, int count) {

        for (int i = 1; i <= products.size(); i++) {
            if (products.get(i - 1).getId() == id) {
                Product p = products.get(i-1);
                p.setWholesale_price(price);

                p.setRetail_price(price.multiply(new BigDecimal(
                        price.doubleValue() + price.doubleValue() * stock / 100)));
                products.set(i-1,p);


                int currentInv = inv.get(id);
                inv.put(id, currentInv + count);
            }
        }
    }
}
