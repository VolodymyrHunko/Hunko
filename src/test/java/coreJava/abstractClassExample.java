package coreJava;

import java.util.Objects;

interface oneInterface{
    void method_A(int a);
    void method_B();
    void method_C(long b);
    void method_D();
}

abstract class oneAbstractClass implements oneInterface{ //abstract class does not implement all methods from interface
    private int z;
    private String q;

    oneAbstractClass(int z, String q) {
        this.z = z;
        this.q = q;
    }

    public void method_B(){
        System.out.println("Method B: "+z);
    }

    abstract void method_F();

    String getQ() {
        return q;
    }

    int getZ() {
        return z;
    }
}

class oneRealClass extends oneAbstractClass{
    oneRealClass(int z, String s) {//must implement constructor if no default constructor in supper class
        super(z, s);
    }

    //must implement all abstract methods except 'method_B' was implemented in abstract class
    @Override
    public void method_F() {

    }

    @Override
    public void method_A(int a) {

        System.out.println("Method A: "+a);
    }

    @Override
    public void method_C(long b) {

    }

    @Override
    public void method_D() {

    }

    @Override
    public int hashCode(){
        //this method override the standard hashCode() just for practice...
        return Objects.hash(getQ(), getZ());
    }
}


public class abstractClassExample {
    public static void main(String [] args){
        oneRealClass foo = new oneRealClass(7, "Some string");
        oneAbstractClass foo2 = new oneRealClass(7, "Some string");
        foo.method_B();
        foo.method_A(6);
        System.out.println("Hashcode: "+foo.hashCode()+"\nString passed: "+ foo.getQ());
        System.out.println("Hashcode: "+foo2.hashCode()+"\nString passed: "+ foo2.getQ());
        System.out.println(foo.equals(foo2));
    }
}
