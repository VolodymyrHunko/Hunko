package coreJava;

public class AnonymousClass { //does not implement interface
    private static String str = "Main class string";
    //it is a regular implementation of inner ClASS as external class  (for comparison)
    // diff - no body
    final static private B abc = new B(111);

    public static void main (String [] args){
        // anonymous class from A interface
        // same as local class, but without name (uses the name of parent call)
        // skipp an implementation of interface
        // only ONE interface can implement (real class can multiply)
        // has access to outer var
        // can be replaced with lambda if only one method has implemented interface.
        A a = new A() {
            @Override
            public void output() { //must override all methods from interface
                System.out.println("anonymous of A class + "+ str);
            }

            @Override
            public void setVar() {
                abc.output(); // have access to final static fields of outer class
            }
        };
        a.output();

        // anonymous local class from B real class
        // CANNOT have constructor (no name)
        B b = new B(333){
            // added some extra functionality, CANNOT be static!!!
            // but CAN be constant (static final)
            final int z = 123;
            void output(){ // override the method
                System.out.println("output of anonymous class: "+z+", from B class:"+v);
            }
        };
        b.output() ;

        //it is a regular implementation of external class as local class
        // public, private or protected modifiers are prohibited (for anonymous too)
        B bb = new B(44);
        bb.output();
    }
}

//interface can be real Java, such sa Runnable()
interface A{
    void output();
    void setVar();
}

//class can be a real Java, such as Thread()
class B{
    int v;
    B(int a){
       this.v = a;
    }
    void output(){
        System.out.println("output of B class: "+v);
    }
}
