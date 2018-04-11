package bootcamp.model.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

private   List<Inventory> inventories = new ArrayList<>();

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
