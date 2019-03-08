import org.testng.annotations.Test;

public class practice {
    private int i = 123;
    int getI(){
        return i;
    }

    @Test
    void testPractice(){
        System.out.println(getI()+new practice_2().st);
    }

    @Test
    void testPractice_2(){
        System.out.println(new practice_2().st);
    }

    @Test
    void testPractice_3(){
        practice_3 p3 = new practice_3();
    }
}

class practice_2{
    String st = " Practice 2 class";
}

class practice_3{

}