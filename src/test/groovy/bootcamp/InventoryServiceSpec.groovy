package bootcamp

import bootcamp.model.inventory.InventoryItem
import bootcamp.model.order.Order
import bootcamp.model.products.Product
import bootcamp.service.InventoryService
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import spock.lang.Specification


class InventoryServiceSpec extends Specification {
	
	def "Adding a Product List to the Inventory List"(){
		
		given: "An InventoryService"
		InventoryService inventoryService = new InventoryService()
		
		and: "an empty inventory list"
		
		inventoryService.inventoryList = new ArrayList<Product>();
		
		and: "A list of 1 product"
		List<Product> productList = new ArrayList<>();
		Product p1 = new Product();
		productList.add(p1);
		
		when: "inventory is received"
		inventoryService.receiveInventory(productList);
		
		then: "The inventory list count should be 1"
		inventoryService.inventoryList.size() == 1;
		
		and: "The inventorylist should contain the product p1"
		inventoryService.inventoryList.contains(p1) == true;
		
		
	}
	
   def "Store receives order from customer"(){
	   given: "An order"
	   Order order = new Order();

	   // Select id 2 to buy and customer wants 3 of item 2
	   order.setId(2);
	   order.setQuantity(3);

	   and: "An inventory"
	   Map<String, InventoryItem> inventory = new HashMap<String, InventoryItem>();
	   InventoryItem itemA = new InventoryItem();
	   InventoryItem itemB = new InventoryItem();
	   InventoryItem itemC = new InventoryItem();

	   itemA.setId(1);
	   itemB.setId(2);
	   itemC.setId(3);

	   itemA.setNumber_available(5);
	   itemB.setNumber_available(5);
	   itemC.setNumber_available(5);

	   itemA.setRetail_price(2.00);
	   itemB.setRetail_price(2.00);
	   itemC.setRetail_price(2.00);


	   inventory.put("1", itemA);
	   inventory.put("2", itemB);
	   inventory.put("3", itemC);

	   and: "A store with revenue"
	   Store fakeStore = new Store();
	   fakeStore.setRevenue(5000);

	   Store store = Stub(Store.class);
	   store.getRevenue() >> fakeStore.getRevenue();

	   when: "that order has processed"

	   then: "the money is added to revenue"

	   // payment = qty of items bought * retail price of that item
	   double payment = order.getQuantity() * inventory.get(""+order.getId()).getRetail_price();
	   fakeStore.setRevenue(fakeStore.getRevenue() + payment);
	   fakeStore.getRevenue() == 5006.0

	   and: "the item purchased is remove from inventory"
	   itemB.setNumber_available(itemB.getNumber_available() - order.quantity)
	   itemB.getNumber_available() == 2

   }

}
