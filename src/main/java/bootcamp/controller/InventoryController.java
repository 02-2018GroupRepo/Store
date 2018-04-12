package bootcamp.controller;

import java.util.List;
import java.util.Map;

import bootcamp.model.order.Order;
import bootcamp.service.CustomerService;
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
	private CustomerService customerService;

    @Autowired
    @Qualifier("inventory")
    private Map<Integer,Integer> inv;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "inventory/all", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"} )
	public Map<Integer,Integer> showInventory(){
		return inv;
	}

	@RequestMapping(value = "inventory/purchase", method=RequestMethod.POST)
	public void getPurchase(@RequestBody Order order){
		log.info("Customer purchasing an item");
		customerService.receiveOrderFromCustomer(order);
	}
	
}
