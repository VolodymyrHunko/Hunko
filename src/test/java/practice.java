import org.testng.annotations.Test;

import java.util.Scanner;

public class practice {

    //Scanner works only with main() method, not @Test
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter something here:");
        int s = scan.nextInt();
        System.out.println("You entered %3 = " + s%3);
    }

    @Test
    void method() {

    }

}

