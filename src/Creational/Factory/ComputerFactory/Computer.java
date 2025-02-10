package Creational.Factory.ComputerFactory;

public abstract class Computer {
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();

    public String toString(){
        return "RAM = " + this.getRAM()
                + "HDD = " + this.getHDD()
                + "CPU = "+ this.getCPU();

    }
}

class PC extends Computer{
    private String ram;
    private String cpu;
    private String hdd;

    public PC(String ram, String hdd, String cpu){
        this.ram = ram;
        this.cpu = cpu;
        this.hdd = hdd;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }
}

class Server extends  Computer{
    private String ram;
    private String cpu;
    private String hdd;
    public Server(String ram, String hdd, String cpu){
        this.ram = ram;
        this.cpu = cpu;
        this.hdd = hdd;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}

class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu){
        if("PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
        else if("Server".equalsIgnoreCase(type)) return new Server(ram,hdd, cpu);

        return null;
    }
}

class ComputerFactoryMain {
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("pc","2gb","500gb","2.4GHz");
        Computer server = ComputerFactory.getComputer("server","16gb","2Tb","4.4GHz");
        System.out.println("PC Config : "+ pc);
        System.out.println("Server Config : "+ server);
    }
}

// FActory Pattern - given a type(string value) of computer, give the reference of the object without specifying in main
// usually used when there are different types of a single object