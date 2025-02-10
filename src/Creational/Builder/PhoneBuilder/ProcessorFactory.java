package Creational.Builder.PhoneBuilder;

import java.security.InvalidParameterException;

public class ProcessorFactory {
    private static Processor currentProcessor;
    public Processor getInstance(String str){
        if(str.equals("Snapdragon") && OperatingSystemFactory.getCurrentOS() instanceof Android)
            currentProcessor= new Snapdragon();
        else if(str.equals("Google") && OperatingSystemFactory.getCurrentOS() instanceof Android)
            currentProcessor= new Google();
        else if(str.equals("Apple") && OperatingSystemFactory.getCurrentOS() instanceof IOS)
            currentProcessor= new Apple();

        return currentProcessor;
    }
}


interface Processor {
    public double getPrice();
}
class Snapdragon implements Processor{
    @Override
    public double getPrice() {
        return 100;
    }
}

class Google implements Processor{
    @Override
    public double getPrice() {
        return 150;
    }
}

class Apple implements Processor{
    @Override
    public double getPrice() {
        return 250;
    }
}
