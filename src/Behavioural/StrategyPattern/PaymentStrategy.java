package Behavioural.StrategyPattern;

import java.util.ArrayList;
import java.util.List;

interface PaymentStrategy {
    public void pay(int amount);
}

class PaypalStrategy implements PaymentStrategy{
    private String emailId;
    private String password;
    public PaypalStrategy(String emailId, String pwd){
        this.emailId = emailId;
        this.password = pwd;
    }
    public void pay(int amount){
        System.out.println(amount + " paid with paypal");
    }
}

class CreditCardStrategy implements PaymentStrategy{
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;
    public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry){
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit/debit card") ;
    }
}

class Item {
    private String upcCode;
    private int price;
    public Item(String upc, int cost){
        this.upcCode = upc;
        this.price = cost;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }
}

class ShoppingCartContext{
    List<Item> items;
//    private PaymentStrategy paymentMethod;
    public ShoppingCartContext(){
        this.items = new ArrayList<>();
    }
    public void addItem(Item item){
        this.items.add(item);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }
    public int calculateTotal(){
        int sum = 0;
        for(Item item : items){
            sum += item.getPrice();
        }
        return sum;
    }
    public void pay(PaymentStrategy paymentMethod){
        int amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}

class ShoppingCartTest{
    public static void main(String[] args) {
        Item item1 = new Item("1234",10);
        Item item2 = new Item("5678",40);

        ShoppingCartContext cart = new ShoppingCartContext();
        cart.addItem(item1);
        cart.addItem(item2);
        //pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));
        //pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }

}

