package coreJava;

import org.testng.annotations.Test;

public class recursionExample {
    /*
    to count factorial i.e. 5! == 120 as 5*4*3*2*1=120
     */
    public long factorialRecursive( long n ) {
        return n == 1 ? 1 : n * factorialRecursive( n-1 );
    }
    @Test (description = "test recursive method")
    void test_recursion (){
        System.out.println(factorialRecursive(5));
    }
    /*
     same without recursive
     */
    public void countFactorial(long n){
        long r = 1;
        for ( long i = 1; i <= n; i++ ) {
            System.out.println("result: " + (r*=i));
        }
    }
    @Test (description = "output all numbers in factorial raw")
    void test_2(){
        countFactorial(5);
    }
}
