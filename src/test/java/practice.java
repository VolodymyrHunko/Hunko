import org.testng.annotations.Test;

import java.util.Scanner;

public class practice {

    //Scanner works only with main() method, not @Test
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter something here:");
        String s = scan.nextLine();
        System.out.println("You entered: " + s);
    }

    @Test
    void method() {

//        String st=  (x, y) -> x + y;
    }
}

