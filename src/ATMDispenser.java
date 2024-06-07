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

class Dipenser500 implements DispenseChain {
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

public class ATMDispenser{

}


