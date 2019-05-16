import org.testng.annotations.Test;

import java.io.IOException;


interface Practical{
    int getI();
}

public class practice implements Practical{

    private int i = 123;

    public int getI(){
        return i;
    }

    @Test
    void testPractice() throws IOException {
        int a=4, b=8, c=-5;

        System.out.println(a--*b/c--);
    }

    @Test
    void testPractice_2(){
        System.out.println(new practice_2().st);
    }

    @Test
    void testPractice_3(){
        practice_3 p3 = new practice_3();
        p3.method();
    }

    @Test
    void test4(){
       practice_3 x = new A3();
        System.out.println(x instanceof practice_3);
    }

}

class practice_2{
    String st = " Practice 2 class";
}

class practice_3 {
    String one = "one";
    String two = new String("two");
    void method(){
        System.out.println( one.concat("two"));
        System.out.println( one.equals(two)); //true
        System.out.println(one == two);//false
        System.out.println( one.equals("onetwo"));//false
        System.out.println( two == "one"); //falce
    }

}

class A3 extends practice_3{
   String a = "subclass";
    boolean method(String a){
        return  a.equals("aaa");
    }
}