package Creational.Builder.PizzaBuilder;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private float total_price = 0;
    private Size size;
    private List<Topping> topping = new ArrayList<>();
    private Crust crust;
    private Cheese cheese;

    public float getTotal_price() {
        return total_price;
    }

    public void addToPrice(float price) {
        this.total_price = total_price + price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void addTopping(Topping topping) {
        this.topping.add(topping);
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }
}
