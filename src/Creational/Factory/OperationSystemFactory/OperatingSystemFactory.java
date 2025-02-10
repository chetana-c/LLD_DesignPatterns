package Creational.Factory.OperationSystemFactory;

public class OperatingSystemFactory {

    public OperatingSystem getInstance(String str){
        if(str.equals("Open"))
            return new Android();
        else
            return new IOS();
    }
}
