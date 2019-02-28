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

    oneAbstractClass(){} //default constructor

    public void method_B(){
        System.out.println("Method B: "+z);
    }

    abstract int method_F();

    String getQ() {
        return q;
    }

    int getZ() {
        return z;
    }
}

class oneRealClass extends oneAbstractClass{
    private int www;
    oneRealClass(int z, String s) {//must implement constructor if no default constructor in supper class
        super(z, s);
        this.www = z;
    }

    <T extends Number> oneRealClass(T setNumber){ //implement Generic constructor
        www=setNumber.intValue();
    }

    //must implement all abstract methods except 'method_B' was implemented in abstract class
    @Override
    public int method_F() {
         return www;
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
        oneAbstractClass foo3 = new oneRealClass(7.5); //call generic constructor
        foo.method_B();
        foo.method_A(6);
        System.out.println("Hashcode foo: "+foo.hashCode()+"\nString passed: "+ foo.getQ());
        System.out.println("Hashcode foo2: "+foo2.hashCode()+"\nString passed: "+ foo2.getQ());
        System.out.println("Hashcode foo3: "+foo3.hashCode()+" Generic passed: "+ foo3.method_F());
        System.out.println(foo.equals(foo2));
        Student <String> st = new Student<>("some object");
        Student <Integer> st2 = new Student<>(33);
        st.method_A(44);
        st.method_C(111);
        System.out.println(st.a +"..."+ st.b +" -> "+ st.c+"***");
        st2.method_B();
        st2.methodGeneric("Some String", foo);
    }
}

//class implements Generic parameter <T>
class Student <T> implements oneInterface{
    int a;
    long b;
    T c;
    Student(T object){ //constructor accept any kind of objects <T>
        this.c=object;
    }

    @Override
    public void method_A(int a) {
       this.a = a;
    }

    @Override
    public void method_B() {

        System.out.println(c);
    }

    @Override
    public void method_C(long b) {
        this.b = b;
    }

    @Override
    public void method_D() {

    }

    //method implements Generic
    <T1, T2> void methodGeneric (T1 t1, T2 t2){
        System.out.println(t1.getClass().getName()+ " -> "+ t1);
        System.out.println(t2.getClass().getName()+ " -> "+ t2);
    }
}