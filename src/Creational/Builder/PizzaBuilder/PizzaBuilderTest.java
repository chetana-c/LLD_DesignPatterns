package Creational.Builder.PizzaBuilder;

public class PizzaBuilderTest {

    public static void main(String[] args) {
        Pizza pizza = new PizzaBuilder().withSize(new MediumSize()).withCrust(new ThinCrust()).withTopping(new CornTopping()).withTopping(new OnionTopping()).build();
        System.out.println("Topping: " + pizza.getTopping());
        System.out.println("Expected Topping: " + new OnionTopping() + new CornTopping());
        System.out.println("Size: " + pizza.getSize());
        System.out.println("Expected Size: " + new LargeSize());
        System.out.println("Crust: " + pizza.getCrust());
        System.out.println("Expected Crust: " + new ThinCrust());
        System.out.println("Total Price: " + pizza.getTotal_price());
    }
}
