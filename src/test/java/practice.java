import org.assertj.core.util.Arrays;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.google.common.primitives.Ints.asList;

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
        String s = "Nvvvgg";
        String sub = s.substring(1);
        Character c = sub.charAt(0);

        System.out.println(Pattern.matches("[Vv]|Nn]",c.toString()));
    }

}

