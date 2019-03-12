package coreJava;


import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

public class innerClassExample {
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    //constructor to initializing
    private innerClassExample() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    private void printEven() {
        // Print out values of even inside of the array
        // example of local inner class declared externally
        EvenIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("end of method");
    }

    private void print(@NotNull Iterator iterator){
        //accept the instance of EventIterator() class as child of Iterator interface
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    //method accept 'Function' standard class instead of 'EventIterator'
    private void print(java.util.function.Function<Integer, Boolean> function){
        for(int i=0; i<SIZE; i++){
            if(function.apply(i)){
                System.out.println(arrayOfInts[i] + "");
            }
        }
    }

    private void localClassExample(int b) { //method of outer class
        int z = b;
        //z=8; // var no more effectively final, so will compiling error
        //inner local class
        class localClass {
            private int www = 4;

            private localClass(int x) { //constructor call the method directly (as example)
                this.getZ();
            }

            private void getZ() {
                System.out.println("Local Class called from outer method: " + z + www);
                //JDK 8 allow access to 'b' and to 'z' if 'z' is final of did not change (<JDK only final)
                System.out.println("Local Class called from outer method: " + z + b);
            }
        }
        new localClass(z); // invoke inner class
    }


    // Inner class implements the Iterator<E> interface,
    // unlike outer class, inner class can be private!
    private class EvenIterator implements Iterator {
        // Start stepping through the array from the beginning
        private int nextIndex = 0;
        @Override
        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }
        @Override
        public Integer next() {
            // GET DIRECT ACCESS TO OUTER CLASS
            Integer retValue = arrayOfInts[nextIndex];
            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }


    @Test (priority = 1)
    void testIterator() {
        // Fill the array with integer values and print out only
        // values of even indices
        innerClassExample ds = new innerClassExample();
        ds.printEven();
    }

    @Test (priority = 2)
    void testIterator2() {
        // Fill the array with integer values and print out only
        // values of even indices as previous example. but:
        // send new instance of Iterator to method 'print'
        innerClassExample ds = new innerClassExample();
        ds.print(new EvenIterator()); //pass an anonymous class
    }

    // implement anonymous function of 'Function' standard class (step 1 of lambda exp)
    @Test (priority = 3)
    void testIterator1(){
        innerClassExample ds = new innerClassExample();
        ds.print(new Function<Integer, Boolean>() { //next step will replace anonimous class with lambda exp
            @Override
            public Boolean apply(Integer integer) {
                if(integer %2 == 0)
                return true;
                else return false;
            }
        });
    }

    // implement lambda exp of 'Function' standard class (step 2 to simplify anonymous exp)
    @Test (priority = 4)
    void testIterator4(){ //print only even numbers (to print odds - change to '%2 !=0'
        innerClassExample ds = new innerClassExample();
        ds.print(integer -> integer % 2 == 0); //simplify 'return' expression
    }


    //anonymous class as argument
    @Test (priority = 5)
    void testIterator3(){
        new innerClassExample().print(new Iterator() {
            // Start stepping through the array from the first index
            private int nextIndex = 1;
            @Override
            public boolean hasNext() {
                return (nextIndex <= SIZE - 1);
            }

            @Override
            public Object next() {
                // GET DIRECT ACCESS TO OUTER CLASS
                Integer retValue = arrayOfInts[nextIndex];
                // Get the next even element
                nextIndex += 2;
                return retValue;
            }
        });
    }

    @Test (priority = 6)
    void testLocalClass() {
        innerClassExample lc = new innerClassExample();
        lc.localClassExample(55);
        Outer o = new Outer();
        o.doStuff();
    }

    @Test (priority = 7)
    void testOneMoreOuterClass() {
        oneMoreOuterClass omoc = new oneMoreOuterClass();
        System.out.println(omoc.stOut);

        oneMoreOuterClass
                .innerClass_1 in1 = omoc.new innerClass_1();
        System.out.println(in1.stInner_1);

        oneMoreOuterClass
                .innerClass_1
                .innerClass_2 in2 = in1.new innerClass_2();
        System.out.println(in2.stInner_2);

        oneMoreOuterClass
                .innerClass_1
                .innerClass_2
                .innerClass_3 in3 = in2.new innerClass_3();
        System.out.println(in3.stInner_3);

        System.out.println(in3.stInner_3 + in2.stInner_2 + in1.stInner_1 + omoc.stOut);

    }

}


    class oneMoreOuterClass {
        String stOut = "Outer class";

        //inner class
        class innerClass_1 {
            String stInner_1 = "Inner class 1";

            class innerClass_2 {
                String stInner_2 = "Inner class 2";

                class innerClass_3 {
                    String stInner_3 = "Inner class 3";
                }
            }
        }
    }


class Outer {
    private String x = "instance variable";

    void doStuff() {
        String z = "local variable";
        class Inner {
            private void seeOuter() {
                System.out.println("Outer x is : " + x);
                System.out.println("Local variable z is : " + z); //won't compile, but it compiles in JDK 8
            }
        }
        Inner i = new Inner();
        i.seeOuter();
    }
}