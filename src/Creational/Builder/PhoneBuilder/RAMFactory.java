package Creational.Builder.PhoneBuilder;

public class RAMFactory {
    public RAM getInstance(String str){
        if(str.equals("128"))
            return new RAM128();
        else return new RAM256();
    }
}


interface RAM {
    public double getPrice();
}

class RAM128 implements RAM{
    public double getPrice(){
        return 200;
    }
}

class RAM256 implements RAM{
    @Override
    public double getPrice() {
        return 400;
    }
}

