package coreJava;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * example of regular class with aggregated class 'ArrayList'
 * as notGeneric as well as Generic type
 */
public class GenericExamples {

    /**
     * @deprecated example of deprecated annotation
     */
    @Deprecated
    @SuppressWarnings("unchecked") // suppress the legacy warning of unchecked raw List
    private void nonGeneric() {
        List myList = new ArrayList(); //not Generic type
        // not good approach !
        myList.add("Volo"); //add String
        myList.add(22); //add Integer (as Object, not primitive)
        String st = (String) myList.get(0); //should be casted to String
        Integer i = (Integer) myList.get(1); //should be casted to Int
        System.out.println(st + " " + i);
    }

    private void Generic() {
        List<String> myList = new ArrayList<>(); //Generic type
        myList.add("Volo"); //add String
        myList.add("63"); //add Int not allowed, should be String
        String st = myList.get(0); //should NOT be casted to String
        Integer i = Integer.valueOf(myList.get(1)); //should be converted to Int
        System.out.println(st + " " + i);
    }

    // in static method type 'T' must be before return type
    private static <T> void genericMethod(T param){
        System.out.println("Parameter: "+param);
    }

    //using wildcards symbols
    private void printList(List <?> list){
        for(Object stuf : list){
            System.out.println(stuf);
        }
    }

    @Test
    void test(){
        genericMethod("Some String");
        genericMethod(111);
        genericMethod(new sampleGenericClass<>("Class var value"));
    }

    @Test
    @SuppressWarnings("deprecation") // method used in deprecated annotation, can be suppress
    void test1() {
        nonGeneric();
        Generic();
    }

    @Test
    void test11(){
        List<Integer> lI = Arrays.asList(1,2,4); //short form of assigning fixed arrayList
        List<String> lS = Arrays.asList("one", "two", "three");
        printList(lI);
        printList(lS);
    }

    @Test
    void test2() {
        // specify <T> as String parameter of called class
        sampleGenericClass<String> sg = new sampleGenericClass<>("Olena");
        sg.setVar("Volo");
        String str = sg.getVar();
        sg.setVar("63"); //cannot send a int value
        Integer i = Integer.valueOf(sg.getVar()); //convert to Integer

        // same class with <T> specified as Integer
        sampleGenericClass<Integer> sgInt = new sampleGenericClass<>(68);
        sgInt.setVar(63); // not able to send String
    }

    @Test
    void test3() {
        // declare class as Int & String Type
        MyClass<Integer, String> mk1 = new MyClass<>(11, "Volo");
        // get the two String as returned from classes and pass it to Generic class
        List<String> myList = new ArrayList<>();
        myList.add("Olena");
        sampleGenericClass<String> myClass = new sampleGenericClass<>("Volo");
        MyClass<String, String> mk2 = new MyClass<>(myList.get(0), myClass.getVar());

        System.out.println(mk1.getKey() + " " + mk1.getValue());
        System.out.println(mk2.getKey() + " " + mk2.getValue());
    }

    @Test
    void test4(){
        //pass instance of Generic class as parameter to another generic class
        sampleGenericClass<MyClass<Integer, String>> foo =
                new sampleGenericClass<>(new MyClass<>(1, "two"));
        System.out.println(foo);
    }
}

/**
 * example of declaration of Generic class with parameter 'T'
 *
 * @param <T> Type
 */
class sampleGenericClass<T> { // <T> must be declared as generic type
    private T var; // T stands for 'Type'

    sampleGenericClass(T var){
        this.var = var;
    }

    void setVar(T t) {
        this.var = t;
    }

    T getVar() {
        return var;
    }

    public String toString(){
        return "Value is: " + var;
    }
}

/**
 * example of creation class from generic Type interface with multiply parameters
 *
 * @param <K> key
 * @param <V> value
 */

interface MyInterface<K, V> {
    K getKey();
    V getValue();
}

// class for get example
class MyClass<K,V> implements MyInterface<K,V>{
    private K key;
    private V Value ;

    MyClass(K key, V value){
        this.key = key;
        this.Value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.Value ;
    }

    @Override
    public String toString(){
        return "Object: "+key+" -> "+Value;
    }
}