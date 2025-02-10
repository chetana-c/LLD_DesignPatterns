package Behavioural.ChainOfResponsibilityPrinciple;

import java.util.Scanner;

class Currency {
    private int amount;
    public Currency(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
}

interface  DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency amount);
}

class Dispenser500 implements DispenseChain {
    private DispenseChain chain;
    public void setNextChain(DispenseChain nextChain){
        this.chain = nextChain;
    }

    public void dispense(Currency amount){
        if(amount.getAmount() >= 500){
            int num = amount.getAmount()/500;
            int rem = amount.getAmount()%500;
            System.out.println(" Dispensing : "+ num + " 500 notes");
            if(rem != 0) this.chain.dispense(new Currency(rem));
        }else {
            this.chain.dispense(amount);
        }
    }
}

class Dispneser100 implements DispenseChain{
    private DispenseChain chain;
    public void setNextChain(DispenseChain nextChain){
        this.chain = nextChain;
    }
    public void dispense(Currency curr){
        if(curr.getAmount() >= 100){
            int num = curr.getAmount()/100;
            int rem = curr.getAmount()%100;
            System.out.println("Dispensing : "+ num + " 100 notes");
            if(rem != 0) this.chain.dispense(new Currency(rem));
        }else {
            this.chain.dispense(curr);
        }
    }
}

 class ATMDispenseChainMain{

    private DispenseChain c1;
    public ATMDispenseChainMain(){
        this.c1 = new Dispenser500();
        DispenseChain c2 = new Dispneser100();

        c1.setNextChain(c2);
    }

    public static void main(String[] args) {
        ATMDispenseChainMain atc = new ATMDispenseChainMain();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            atc.c1.dispense(new Currency(amount));
        }
    }
}


