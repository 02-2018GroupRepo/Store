package bootcamp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bootcamp.model.products.Product;

@Component
public class ProductDao {
	
	private final String GET_PRODUCTS = "SELECT id, name, description, retail_price, wholesale_price FROM product";
    private final String GET_PRODUCT_BY_ID_SQL = GET_PRODUCTS + " where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Product> getProducts() {
		return jdbcTemplate.query(GET_PRODUCTS, new BeanPropertyRowMapper<>(Product.class));
	}
	
	public Product getProductById(Integer id) {
		return jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID_SQL, new Object[] {id}, new BeanPropertyRowMapper<>(Product.class));
	}
	

}
