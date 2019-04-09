import org.testng.annotations.Test;


interface Practical{
    int getI();
}

public class  practice  implements Practical{

    private int i = 123;

    public int getI(){
        return i;
    }

    @Test
    void testPractice() {
        byte x =64;
        byte y = (byte)(256);
        System.out.println(y);

    }

    @Test
    void testPractice_2(){
        practice_2 <practice_3> p_2 = new practice_2<>(new practice_3());
        System.out.println(p_2);
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

class practice_2<T>{
    T obj_3;
    String st = "sss";
    practice_2(T obj){
        this.obj_3 = obj;
    }

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