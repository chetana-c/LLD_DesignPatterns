package Creational.Builder.ComputerBuilder;

 class Computer {
    private String HDD;
    private String RAM;
    private boolean isGraphicsEnabled;
    private boolean isBluetoothEnabled;

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setGraphicsEnabled(boolean graphicsEnabled) {
        isGraphicsEnabled = graphicsEnabled;
    }

    public void setBluetoothEnabled(boolean bluetoothEnabled) {
        isBluetoothEnabled = bluetoothEnabled;
    }

    public String toString(){
        return "HDD = "+ HDD + " RAM = "+ RAM + " isGraphicsEnable = "+ isGraphicsEnabled + " isBluetoothEnabled = "+ isBluetoothEnabled;
    }
}

class ComputerBuilder {
        private String HDD;
        private String RAM;
        private boolean isGraphicsEnabled;
        private boolean isBluetoothEnabled;
         Computer computer = new Computer();

        public ComputerBuilder setHDD(String HDD) {
            computer.setHDD(HDD);
            return this;
        }

        public ComputerBuilder setRAM(String RAM) {
            computer.setRAM(RAM);
            return this;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsEnabled) {
            computer.setGraphicsEnabled(isGraphicsEnabled);
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            computer.setBluetoothEnabled(isBluetoothEnabled);
            return this;
        }

        public Computer build(){
            Computer builtComputer = this.computer;
            computer = new Computer();
            return builtComputer;
        }
    }

    class ComputerBuilderMain {
    public static void main(String[] args) {
        Computer comp = new ComputerBuilder().setHDD("500gb").setRAM("128gb").setBluetoothEnabled(true).build();
        System.out.println(comp);
    }
}

// Build a computer with certain parameters (may or may not include all criteria)
// usually used when there are multiple parameters which are played around for a single type