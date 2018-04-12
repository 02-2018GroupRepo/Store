package bootcamp;

import org.springframework.stereotype.Component;

@Component
public class Store {
    private String name;
    private Double revenue = 5750.00;

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void addRevenue(double amount) {
        revenue += amount;
    }

    public void reduceRevenue(double amount) {
        revenue -= amount;
    }
}
