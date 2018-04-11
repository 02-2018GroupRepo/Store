package bootcamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.model.products.Product;
import bootcamp.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

    @Autowired
    private List<Product> inventoryList;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/product/{id}")
    public Product getProductById(@PathVariable Integer id) {
		log.debug("Retreiving product " + id);
    	return inventoryList.get(id-1);
    }

	@RequestMapping(value ="/test", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
	public List<Product> test(){
		return productService.getListOfThings();
	}


	@RequestMapping("/product")
	public List<Product> getProductList(){
		return inventoryList;
	}
	
}
