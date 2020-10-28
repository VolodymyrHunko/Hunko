package coreJava;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

    /*
    * to get a sum of all numbers in value '12345'
     */
    public int sum (int x){
       // return x==0?0 : (x%10 + sum(x/10));
        if(x==0){
            return 0;
        }else{
            return x%10+sum(x/10);
        }
    }
    public int product (int x){
        int prod =1;
        while (x != 0) {
            prod = prod * (x % 10);
            x = x / 10;
        }
        return prod;
    }
    @Test (description = "get a sum and product of numbers")
    void test_3(){
        System.out.println(sum (12345));
        System.out.println("Product - " + product(12345));
    }

    /*
    to convert decimal to binary
     */
    public void convert(int y){
        if (y > 0) {
            convert(y / 2);
            System.out.printf("%d", y % 2);
        }
    }
    @Test (description = "20 == 010100")
    void test_4 (){
        convert(20);
    }

    /*
    to found Fibonacci
     */
    public int fibo (int i){
        if(i==0)
            return 0;
        if(i==1)
            return 1;
        return fibo(i - 1) + fibo(i - 2);
    }
    @Test (description = "fibo raw")
    void test_5 (){ System.out.println("10 element in fibo raw = "+ fibo (10)); }

}
