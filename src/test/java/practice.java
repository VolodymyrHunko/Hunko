import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        String s = "My string";
        StringBuilder sb = new StringBuilder(s);
        List<Character> l = new ArrayList<>();;
        for(int i =0; i<s.length(); i++) {
            l.add(s.charAt(i));
        }
        Collections.reverse(l);
        System.out.println(l.toString());
        System.out.println(sb.reverse().toString());
    }

    @Test
    void testPractice_2(){
        Object a = String.valueOf(1);
        Object b = String.valueOf(1);
        System.out.println(a.equals(b));
    }

    @Test
    void testPractice_3(){
        practice_3 p3 = new practice_3();
        p3.method();
    }

    @Test
    void test4(){

        System.out.println("xxxx");
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