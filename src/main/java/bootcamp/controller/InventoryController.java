package bootcamp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.model.products.Product;
import bootcamp.service.InventoryService;

@RestController
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

    @Autowired
    @Qualifier("inventory")
    private Map<Integer,Integer> inv;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "inventory/all", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"} )
	public Map<Integer,Integer> showInventory(){
		return inv;
	}

	@RequestMapping(name = "inventory/receive", method=RequestMethod.POST)
    public void getProduct(@RequestBody List<Product> products) {
		log.debug("Receiving products");
    	inventoryService.receiveInventory(products); 
    }
	
}
