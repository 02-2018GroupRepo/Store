package bootcamp;

import bootcamp.dao.ProductDao;
import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppRunner implements CommandLineRunner {


    @Autowired
    ProductDao productDao;

    @Autowired
    private List<Product> items;


    @Override
    public void run(String... args) throws Exception {
    }


}
