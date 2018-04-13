package bootcamp.config;

import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CommonConfig {
    private final String GET_PRODUCTS = "SELECT id, name, description, retail_price, wholesale_price FROM product";

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Bean
    @Qualifier("inventory")
    Map<Integer, Integer> getInventory() {
        Map<Integer, Integer> inv = new HashMap<>();
        for (int i = 1; i <= 60; i++) {
            inv.put(i, 0);
        }
        return inv;
    }

    @Bean
    @Qualifier("Stock")
    double getStock() {
        return 47.50;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Bean
    List<Product> getProductList() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_PRODUCTS, new BeanPropertyRowMapper<>(Product.class));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
