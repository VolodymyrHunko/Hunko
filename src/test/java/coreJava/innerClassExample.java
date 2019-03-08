package coreJava;


import org.testng.annotations.Test;

import java.util.Iterator;

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

    private void localClassExample(int b){ //method of outer class
        int z = b;
        //inner local class
        class localClass {
            private localClass(int x){ //constructor call the method directly (as example)
                this.getZ();
            }
            private void getZ() {
                System.out.println("Local Class called from outer method: "+z);
            }
        }
        new localClass(z); // invoke inner class
    }


    // Inner class implements the Iterator<E> interface,
    // unlike outer class, inner class can be private!
    private class EvenIterator implements Iterator {
        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {
            // GET DIRECT ACCESS TO OUTER CLASS
            Integer retValue = arrayOfInts[nextIndex];
            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }


    @Test
    void testIterator() {
        // Fill the array with integer values and print out only
        // values of even indices
        innerClassExample ds = new innerClassExample();
        ds.printEven();
    }
    @Test
    void testLocalClass(){
        innerClassExample lc = new innerClassExample();
        lc.localClassExample(55);

    }

    @Test
    void testOneMoreOuterClass(){
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

class oneMoreOuterClass{
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