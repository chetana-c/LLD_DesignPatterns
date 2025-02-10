package Behavioural.StrategyPattern;

import java.util.Scanner;

interface CalculationStrategy {
    public float calculate(float a, float b);
}

 class Addition implements CalculationStrategy{
    @Override
    public float calculate(float a, float b) {
        return a+b;
    }
}

class Subtraction implements  CalculationStrategy{
    @Override
    public float calculate(float a, float b) {
        return a-b;
    }
}

class Multiplication implements CalculationStrategy{
    @Override
    public float calculate(float a, float b) {
        return a*b;
    }
}

class Context{
     private CalculationStrategy calculationStrategy;
     public Context(CalculationStrategy calculationStrategy){
         this.calculationStrategy = calculationStrategy;
     }

     public float executeStrategy(float num1, float num2){
         return calculationStrategy.calculate(num1, num2);
     }
}

class CalculationStrategyMain{
    public static void main(String[] args) {

        while (true) {
            System.out.println("Enter the values : ");
            Scanner scanner = new Scanner(System.in);
            float num1 = scanner.nextFloat();
            float num2 = scanner.nextFloat();
            Context context = new Context(new Addition());
            System.out.println("Sum of values = " + context.executeStrategy(num1, num2));
            context = new Context(new Subtraction());
            System.out.println("Difference of values = " + context.executeStrategy(num1, num2));
            context = new Context(new Multiplication());
            System.out.println("Product of values = " + context.executeStrategy(num1, num2));
        }
    }
}