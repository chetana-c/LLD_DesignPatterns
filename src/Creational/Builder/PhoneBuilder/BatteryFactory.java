package Creational.Builder.PhoneBuilder;

public class BatteryFactory
{
    public Battery getInstance(String str){
        if(str.equals("3000"))
            return new Mah3000();
        else return new Mah6000();
    }
}

interface Battery
{
    public double getPrice();
}

class Mah3000 implements Battery{
    @Override
    public double getPrice() {
        return 80;
    }
}

class Mah6000 implements Battery{
    @Override
    public double getPrice() {
        return 120;
    }
}