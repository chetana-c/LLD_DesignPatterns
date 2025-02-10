package Creational.Factory.OperationSystemFactory;

public class OperatingSystemFactoryMain {
    public static void main(String[] args) {
//        OperatingSystem obj = new Android();
//        obj.spec();

        OperatingSystemFactory osf = new OperatingSystemFactory();
        OperatingSystem os = osf.getInstance("Closed");
        os.spec();
    }
}

// when there can be different types of objects that have same functionality
// if the object call is decided at runtime [which type of object should be created based on conditions or parameters
// if there is a common method/function/attribute that is used by all the objects use a interface