package Creational.Builder.PizzaBuilder;

public class PizzaBuilder {
    Pizza pizza = new Pizza();
    boolean sizeSet = false;
    boolean crustSet = false;
    public PizzaBuilder withTopping(Topping topping){
        pizza.addTopping(topping);
        pizza.addToPrice(topping.getCost());
        return this;
    }

    public PizzaBuilder withSize(Size size){
        pizza.setSize(size);
        pizza.addToPrice(size.getCost());
        return this;
    }

    public PizzaBuilder withCheese(Cheese cheese){
        pizza.setCheese(cheese);
        pizza.addToPrice(cheese.getCost());
        return this;
    }

    public PizzaBuilder withCrust(Crust crust){
        pizza.setCrust(crust);
        pizza.addToPrice(crust.getCost());
        return this;
    }

    public Pizza build(){
        Pizza builtPizza = this.pizza;
        this.pizza = new Pizza();
        return builtPizza;
    }

    public double calculatePrice(){
        return pizza.getTotal_price();
    }
}
