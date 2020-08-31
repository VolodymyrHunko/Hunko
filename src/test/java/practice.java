import org.assertj.core.util.Arrays;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.google.common.primitives.Ints.asList;

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
    }

}

