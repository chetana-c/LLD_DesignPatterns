package Creational.Builder.PhoneBuilder;

public class PhoneBuilderMain {
    public static void main(String[] args) {
        OperatingSystemFactory osf = new OperatingSystemFactory();
        BatteryFactory bf = new BatteryFactory();
        RAMFactory rf = new RAMFactory();
        ProcessorFactory pf = new ProcessorFactory();
        Phone p = new PhoneBuilder()
                .setOs(osf.getInstance("IOS"))
                .setRam(rf.getInstance("128"))
                .setBattery(bf.getInstance("3000"))
                .setProcessor(pf.getInstance("Apple"))
                .getPhone();

        System.out.println(p.getPrice());
    }
}
