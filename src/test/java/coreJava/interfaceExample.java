package coreJava;

public class interfaceExample {
    public static void main(String [] args) throws Exception {
        sample s = new sample();
        s.oldMethod(); //regular implementation
        s.newMethod(2); //overloaded default method (java8)
        s.newMethod(); // call default method (Java8)
        sample.staticMethod(); //static from class
        someInterface.staticMethod(); //static from interface (java8)
        oneMoreInterface.staticMethod(); //static from second interface(java8)



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