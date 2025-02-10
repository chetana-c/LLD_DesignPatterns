package Creational.Singleton;

public class Singleton {
    private static Singleton instance;
    private Singleton(){
    }
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){ // multiple threads will create only single instance
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void log(String log){
        System.out.println(log);
    }
}

class LoggerMain{
    public static void main(String[] args) {
        Singleton logger1 = Singleton.getInstance();
        System.out.println(logger1);
        Singleton logger2 = Singleton.getInstance();
        System.out.println(logger2);

    }
}

class MyClass {
    public static void doSomething(Singleton logger , String text){
        logger.log(text + " logger ");
    }
}
