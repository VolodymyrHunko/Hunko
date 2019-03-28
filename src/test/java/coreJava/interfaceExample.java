package coreJava;

import java.util.function.Predicate;

public class interfaceExample {
    public static void main(String [] args) throws Exception {
        sample s = new sample();
        s.oldMethod(); //regular implementation
        s.newMethod(2); //overloaded default method (java8)
        s.newMethod(); // call default method (Java8)
        sample.staticMethod(); //static from class
        someInterface.staticMethod(); //static from interface (java8)
        oneMoreInterface.staticMethod(); //static from second interface(java8)

        //get instance of functional interface by using lambda exp (Java 8)
        oneMoreInterface oMi = () -> System.out.println("Blah, blah");
        oMi.oldMethod(); // call interface's method
        //get instance of functional interface by using lambda exp with parameters
        therdInterface <String> sI = (a, b) -> {
            String c = a + b;
            System.out.println(c);
        };
        therdInterface <Integer> iI = (a,b) -> {
            Integer c = a+b;
            System.out.println(c);};
        sI.method("Volo ","Hunko");
        iI.method(2,5);

        // use the standard 'Predicate' interface with return boolean exp
        Predicate <Integer> pred = (n) -> n%2 == 0;
        System.out.println(pred.test(3)); //false (3 % 2 =! 0)`
    }
}

interface someInterface {
    //regular interfaces metod
    void oldMethod();
    //default method, only Java8
    default void newMethod(){
        System.out.println("interface1 newMethod");
    }
    //static method only Java8
    static void staticMethod(){
        System.out.println("static interface 1");
    }
}

@FunctionalInterface // only one abstract class in functional interface
interface oneMoreInterface{
    //one more method with same name, no issue
    void oldMethod();
    //same static method as diff interface
    static void staticMethod() {
        System.out.println("static interface2");
    }
    //default method Java8
    default void newMethod(double ds){
        System.out.println("interface2 newMethod");
    }
}


class sample implements someInterface, oneMoreInterface{
    @Override
    public void oldMethod()
    {
        System.out.println("old method override in child class ");
    }

    public void newMethod(double d) {
        System.out.println("new method overload from child class");
    }

    static void staticMethod(){
        System.out.println("static method of sample class");
    }
}

@FunctionalInterface
interface therdInterface <T>{ //Generic interface
     void  method (T a, T b);
}