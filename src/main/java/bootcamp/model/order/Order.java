package bootcamp.model.order;

public class Order {
//prod id, quantity
    private int id;
    private int quantity;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
