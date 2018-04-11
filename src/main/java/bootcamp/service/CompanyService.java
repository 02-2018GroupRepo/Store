package bootcamp.service;

import bootcamp.Store;
import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CompanyService {

    @Autowired
    private Store store;

    @Autowired
    private Map<Integer, Integer> inv;

    @Autowired
    private List<Product> getProductList;


    public Double[] getCompanyValue(){

        int currQty;
        Double invValue = 0.0;
        Double currWholeSale;


        // Loop product list and calculate total revenue
        for(int i = 0; i < getProductList.size(); i++){

            // Grab qty from map of current item
            currQty = inv.get(i + 1);

            // whole sale price of the item
            currWholeSale = Double.parseDouble(""+getProductList.get(i).getWholesale_price());
            invValue += currQty * currWholeSale;
        }

        Double companyInfo[] = {store.getRevenue(), invValue};

        return companyInfo;

    }
}
