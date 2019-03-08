package coreJava;


import org.testng.annotations.Test;

import java.util.Iterator;

public class innerClassExample {
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    //constructor to initializing
    public innerClassExample() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        // Print out values of even indices of the array
        EvenIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
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

}