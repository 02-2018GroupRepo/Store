package bootcamp.service;

import bootcamp.Store;
import bootcamp.model.order.Order;
import bootcamp.model.products.Product;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomerService {

    @Autowired
    private Store store;

    @Autowired
    @Qualifier("Stock")
    double stockPrice;

    @Autowired
    private Map<Integer, Integer> inv;

    @Autowired
    private List<Product> getProductList;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public boolean receiveOrderFromCustomer(Order order) {

        for (Product item : getProductList) {

            // when given id matches an id in the product list
            if (item.getId() == order.getId()) {

                // Check if the amount ask can be placed
                if (order.getQuantity() <= inv.get(order.getId())) {
                    // add payment to revenue
                    double payment = order.getQuantity() * Double.parseDouble("" + item.getRetail_price());
                    store.addRevenue(payment);
                    // store.setRevenue(store.getRevenue() + payment);
                    System.out.println(store.getRevenue());

                    // substract amount from inventory
                    int currProductQty = inv.get(order.getId());
                    inv.put(order.getId(), currProductQty - order.getQuantity());
                    log.info("item id: " + order.getId() + " new qty: " + inv.get(order.getId()));

                    return true;
                }
            }
        }

        log.info("Could not process order for product: " + order.getId());
        return false;
    }


    //TODO: cron based GET stock price into autowired
}
