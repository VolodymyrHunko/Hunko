import org.testng.annotations.Test;


interface Practical{
    int getI();
}

public class practice implements Practical{

    private int i = 123;

    public int getI(){
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
       practice_3 x = new A3();
        System.out.println(x instanceof practice_3);
    }

}

class practice_2{
    String st = " Practice 2 class";
}

class practice_3{
    String a = "class";
    String b = new String("class");
    String c = new String("class");

    static boolean method(){
       return  "aaa".equals("aaa");
    }
}

class A3 extends practice_3{
   String a = "subclass";
    boolean method(String a){
        return  a.equals("aaa");
    }
}