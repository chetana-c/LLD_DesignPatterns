package Creational.Builder.PhoneBuilder;

public class Phone {
    private OperatingSystem os;
    private RAM ram;
    private Processor processor;
    private Battery battery;

    private double total_price;

    public void setOs(OperatingSystem os) {
        this.os = os;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public String toString(){
        return "Phone [os = "+ os + ", ram = "+ ram + ", processor = "+ processor + ", batter = "+ battery +"]";
    }

    public double getPrice(){
        double total_price = 0.0;

        if (os != null) {
            total_price += os.getPrice();
        }
        if (ram != null) {
            total_price += ram.getPrice();
        }
        if (processor != null) {
            total_price += processor.getPrice();
        }
        if (battery != null) {
            total_price += battery.getPrice();
        }

        return total_price;
    }


}
