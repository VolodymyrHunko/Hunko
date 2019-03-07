package coreJava;

public class AnonymousClass { //does not implement interface
    public static void main (String [] args){
        // anonymous class from A interface
        // same as local class, but without name (uses the name of parent call)
        // skipp an implementation of interface
        // can be replaced with lambda
        A a = new A() {
            @Override
            public void output() { //must override all methods from interface
                System.out.println("anonymous of A class");
            }
        };
        a.output();

        // anonymous class from B real class
        B b = new B(){
            int z = 123; // added some extra functionality
            void output(){ // override the method
                System.out.println("output of B class + "+z);
            }
        };
        b.output() ;

        //it is local inner class!!!
        B bb = new B();
        bb.output();
    }
}

//interface can be real Java, such sa Runnable()
interface A{
    void output();
}

//class can be a real Java, such as Thread()
class B{
    void output(){
        System.out.println("output of B class");
    }
}
