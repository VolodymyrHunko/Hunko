import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class practice {

    private int i = 123;

    int getI(){
        return i;
    }

    @Test
    void testPractice() {
        System.out.println(getI()+new practice_2().st);
    }

    @Test
    void testPractice_2(){
        System.out.println(new practice_2().st);
    }

    @Test
    void testPractice_3(){
        practice_3 p3 = new practice_3();
        System.out.println(p3.method());
    }

    @Test
    void test4(){
        new A3();
    }

}

class practice_2{
    String st = " Practice 2 class";
}

class practice_3{
    String a = "class";
    String b = new String("class");
    String c = new String("class");

    boolean method(){
       return  "aaa".equals("aaa");
    }
}

class A3{
    static{
        System.out.println("static block is invoked");
        System.exit(0);
    }
}