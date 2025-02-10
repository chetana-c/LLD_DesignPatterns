package Behavioural.StatePattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
// Ready, DispenseChange, DispenseItem, TransactionCancelled are different states
// also setting a chain of responsibility inside

public interface VendingMachineState {
    public void collectCash(int amount);
    public void dispenseChange(String productCode);
    public void dispenseItem(String productCode);
    public void cancelTransaction();
}

class Ready implements VendingMachineState{
    private VendingMachine vendingMachine;
    Ready(VendingMachine vm){
        this.vendingMachine = vm;
    }
    public void collectCash(int cash){
        this.vendingMachine.addCollectedCash(cash);
        this.vendingMachine.setVendingMachineState(new DispenseChange(this.vendingMachine));
        this.vendingMachine.dispenseChange(this.vendingMachine.getSelectedProduct());
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new RuntimeException("Transaction not initiated yet.. Unable to dispense change");
    }

    public void dispenseItem(String productCode){
        throw new RuntimeException("Transaction not initiated yet.. Unable to dispense");
    }

    @Override
    public void cancelTransaction() {
        this.vendingMachine.setVendingMachineState(new TransactionCancelled(vendingMachine));
        this.vendingMachine.cancelTransaction();
    }
}

class DispenseChange implements VendingMachineState{
    private VendingMachine vendingMachine;
    DispenseChange(VendingMachine vm){
        this.vendingMachine = vm;
    }
    public void collectCash(int cash){
        throw new RuntimeException("Dispensing Change... Cannot collect cash");
    }

    @Override
    public void dispenseChange(String productCode) {
        int change = this.vendingMachine.calculateChange(productCode);
        System.out.println("Change of :" + change + " returned");
        this.vendingMachine.setVendingMachineState(new DispenseItem(this.vendingMachine));
        this.vendingMachine.dispenseItem(productCode);
    }

    public void dispenseItem(String productCode){
        throw new RuntimeException("Dispensing Change ... Unable to dispense Item");
    }

    @Override
    public void cancelTransaction() {
        throw new RuntimeException("Dispensing Change ... Unable to cancel Transaction");
    }
}

class DispenseItem implements VendingMachineState{
    private VendingMachine vendingMachine;
    DispenseItem(VendingMachine vm){
        this.vendingMachine = vm;
    }
    public void collectCash(int cash){
        throw new RuntimeException("Dispensing Item... Cannot collect cash");
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new RuntimeException("Dispensing Item... Cannot dispense change");
    }

    public void dispenseItem(String productCode){
        this.vendingMachine.removeProduct(productCode);
        System.out.println("Dispensing Item : " + productCode);
        vendingMachine.setVendingMachineState(new Ready(this.vendingMachine));
    }

    @Override
    public void cancelTransaction() {
        throw new RuntimeException("Dispensing item ... Unable to cancel Transaction");
    }
}

class TransactionCancelled implements VendingMachineState{
    private VendingMachine vendingMachine;
    TransactionCancelled(VendingMachine vm){
        this.vendingMachine = vm;
    }
    public void collectCash(int cash){
        throw new RuntimeException("Cancelling Transaction... Cannot collect cash");
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new RuntimeException("Cancelling Transaction... Cannot dispense change");
    }

    public void dispenseItem(String productCode){
        throw new RuntimeException("Cancelling Transaction... Cannot dispense item");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Returning collected cash " + vendingMachine.getCollectedCash());
        vendingMachine.setCollectedCash(0);
        vendingMachine.setVendingMachineState(new Ready(vendingMachine));
    }
}

class VendingMachine implements VendingMachineState{
    private int collectedCash;
    private String selectedProduct;
    private VendingMachineState vendingMachineState;
    Map<String, Set<String>> productCodeItemMap;
    Map<String, Integer> productCodePriceMap;
    public VendingMachine() {
        this.collectedCash = 0;
        this.vendingMachineState = new Ready(this);
    }
    public void collectCashAndSelectProduct(int amount, String productCode) {
        if (!productCodePriceMap.containsKey(productCode)) {
            throw new RuntimeException("Invalid product code!");
        }
        if (amount < productCodePriceMap.get(productCode)) {
            throw new RuntimeException("Insufficient funds! Required: " + productCodePriceMap.get(productCode));
        }
        this.selectedProduct = productCode;
        this.vendingMachineState.collectCash(amount);
    }

    public void addCollectedCash(int collectedCash){
        this.collectedCash = collectedCash;
    }
    public VendingMachine setCollectedCash(int collectedCash){
        this.collectedCash = collectedCash;
        return this;
    }
    public VendingMachineState getVendingMachineState(){
        return vendingMachineState;
    }

    public VendingMachine setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
        return this;
    }

    @Override
    public void collectCash(int amount) {
        this.vendingMachineState.collectCash(amount);
    }

    public void dispenseChange(String productCode){
        this.vendingMachineState.dispenseChange(productCode);
    }
    public void cancelTransaction(){
        this.vendingMachineState.cancelTransaction();
    }
    public void removeProduct(String productCode) {
        Set<String> products = productCodeItemMap.get(productCode);
        if (products != null && !products.isEmpty()) {
            products.remove(products.iterator().next());
        }
    }
    @Override
    public void dispenseItem(String productCode) {
        this.vendingMachineState.dispenseItem(productCode);
    }
    public int calculateChange(String productCode){
        return collectedCash - productCodePriceMap.get(productCode);
    }
    public int getCollectedCash(){
        return this.collectedCash;
    }

    public String getSelectedProduct(){
        return this.selectedProduct;
    }
}

class Client{
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Setting up some dummy product data
        Map<String, Set<String>> productCodeItemMap = new HashMap<>();
        Map<String, Integer> productCodePriceMap = new HashMap<>();

        // Adding products
        Set<String> cokeStock = new HashSet<>();
        cokeStock.add("Coke-1");
        cokeStock.add("Coke-2");

        Set<String> pepsiStock = new HashSet<>();
        pepsiStock.add("Pepsi-1");
        pepsiStock.add("Pepsi-2");

        productCodeItemMap.put("COKE", cokeStock);
        productCodeItemMap.put("PEPSI", pepsiStock);

        // Adding product prices
        productCodePriceMap.put("COKE", 50);
        productCodePriceMap.put("PEPSI", 45);

        // Injecting the product data into VendingMachine
        vendingMachine.productCodeItemMap = productCodeItemMap;
        vendingMachine.productCodePriceMap = productCodePriceMap;

        // Test case: Successful transaction (only one method call)
        System.out.println("Starting transaction...");
        vendingMachine.collectCashAndSelectProduct(100, "COKE");
        System.out.println("Transaction Complete!");
        vendingMachine.collectCashAndSelectProduct(50,"PEPSI" );
    }
}