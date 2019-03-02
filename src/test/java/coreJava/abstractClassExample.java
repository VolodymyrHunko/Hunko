package coreJava;

import java.util.Objects;

interface oneInterface{
    void method_A(int a);
    <T>void method_B(); //interface of generic method,
    void method_C(long b);
    void method_D(Integer... parameters); //implement vararg parameters
}

abstract class oneAbstractClass implements oneInterface{ //abstract class does not implement all methods from interface
    //private fields to access them only from getter/setter
    private int z;
    private String q;

    oneAbstractClass(int z, String q) { //constructor to initialize class
        this.z = z;
        this.q = q;
    }

    oneAbstractClass(){} //default constructor, should be created manually, as by default it not created id exists one

    public void method_B(){ //can do not accept any parameters, even interface is generic
        System.out.println("Method B of abstract class: "+z+">>"+q); //get the fields
    }

    abstract int method_F(); //method did not get from interface

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
        super(z, s); //initialize super class
        this.www = z; //initialize sub class
    }

    //implement Generic constructor as Number restricted even if class is NOT generic
    <T extends Number> oneRealClass(@org.jetbrains.annotations.NotNull T setNumber){
        www=setNumber.intValue(); //any Number's type of generic will convert to int by .intValue() method
    }

    //must implement all abstract methods except 'method_B' was implemented in abstract class (can be override)
    @Override
    public void method_A(int a) {
        System.out.println("Method A of sub class: "+a);
    }

//    public void method_B(){ //can do not accept any parameters, even interface is generic
//        System.out.println("Method B of real class: "+ super.getQ()); //override
//    }

    @Override
    public void method_C(long b) {
    }

    @Override
    public void method_D(Integer... parameters) { //can send any int params
        System.out.println("Sent parameters: "+parameters.length);
    }

    @Override
    public int method_F() {
        return www;
    }

    @Override
    public int hashCode(){
        //this method override the standard hashCode() of Object (just for practice...)
        return Objects.hash(getQ(), getZ()); //pass any args for override a default method
    }

    @Override
    public String toString(){
        return "Override toString(): "+www;
    }
}

//class implements Generic parameter <T>
class Student <T> implements oneInterface{
    //protected fields to get access from external class
    int a;
    long b;
    T c;

    Student(T object){ //constructor accept any kind of objects <T>
        this.c=object;
    }

    @Override
    public void method_A(int aa) {
       this.a = aa;
    }

    @Override
    public void method_B() {
        System.out.println("Generic value: "+c);
    }

    @Override
    public void method_C(long bbb) {
        this.b = bbb;
    }

    @Override
    public void method_D(Integer... parms) {
        System.out.println("parameters: "+parms.length+" last one: "+parms[parms.length-1]);
    }

    //method implements Generic
    <T1, T2> void methodGeneric (T1 t1, T2 t2){
        System.out.println(t1.getClass().getName()+ " -> "+ t1);
        System.out.println(t2.getClass().getName()+ " -> "+ t2);
    }

}


public class abstractClassExample {
    public static void main(String [] args){
        //two objects with same fields is NOT equals
        oneRealClass foo = new oneRealClass(7, "Some string");
        oneAbstractClass foo2 = new oneRealClass(7, "Some string");
        oneAbstractClass foo3 = new oneRealClass(7.6); //call generic constructor
        //two generic objects from same class
        Student <String> st = new Student<>("some object");
        Student <Integer> st2 = new Student<>(33);

        foo.method_B(); //return super method (as it's NOT override)
        foo.method_A(6); //return sub method
        //two objects have same hash code, but not equal
        System.out.println("Hashcode foo: "+foo.hashCode()+"\nString passed: "+ foo.getQ());
        System.out.println("Hashcode foo2: "+foo2.hashCode()+"\nString passed: "+ foo2.getQ());
        System.out.println("objects are equal? -> "+foo.equals(foo2));
        //diff hash code, double value converted to int
        System.out.println("Hashcode foo3: "+foo3.hashCode()+" Generic passed: "+ foo3.method_F());


        st.method_A(44);
        st.method_C(111);
        st.method_D(3, 5, 7);
        System.out.println("Student class: "+st.a +"..."+ st.b +" -> "+ st.c);
        System.out.println("Student2 class: "+st2.a +"..."+ st2.b +" -> "+ st2.c); //a & b did not initialize
        st2.methodGeneric("Some String", foo); //pass String and object
        st2.method_B(); //return sub method (override)

    }
}