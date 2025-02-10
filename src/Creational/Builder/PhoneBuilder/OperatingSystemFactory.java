package Creational.Builder.PhoneBuilder;

public class OperatingSystemFactory{
    private static OperatingSystem currentOS;
    public OperatingSystem getInstance(String str){
    if(str.equals("Android"))
        currentOS =  new Android();
    else
        currentOS = new IOS();

    return currentOS;
    }

    public static OperatingSystem getCurrentOS(){
        return currentOS;
    }
}

interface OperatingSystem {
    public double getPrice();
}

class Android implements OperatingSystem{
    public double getPrice(){
        return 300.0;
    }
}

class IOS implements OperatingSystem{
    public double getPrice(){
        return 500.0;
    }
}