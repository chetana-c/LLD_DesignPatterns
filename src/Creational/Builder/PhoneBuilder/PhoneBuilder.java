package Creational.Builder.PhoneBuilder;

public class PhoneBuilder {
    private OperatingSystem os;
    private RAM  ram;
    private Processor processor;
    private Battery battery;
    Phone phone = new Phone();

    public PhoneBuilder setOs(OperatingSystem os) {
        phone.setOs(os);
        return this;
    }

    public PhoneBuilder setRam(RAM ram) {
        phone.setRam(ram);
        return this;
    }

    public PhoneBuilder setProcessor(Processor processor) {
        phone.setProcessor(processor);
        return this;
    }

    public PhoneBuilder setBattery(Battery battery) {
        phone.setBattery(battery);
        return this;
    }

    public Phone getPhone(){
        Phone builtPhone = this.phone;
        phone = new Phone();
        return builtPhone;
    }
}
