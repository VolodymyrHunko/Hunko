import org.assertj.core.util.Arrays;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate currentDeviceDate = LocalDate.now();
        LocalDate strtDate = currentDeviceDate.minusDays(3);
        int currentYear = currentDeviceDate.getYear();
        int startYear = strtDate.getYear();


        //diff conditions for formatter
        String startDateAppFormat;
        String currentDateAppFormat;
        if (currentYear != startYear) {
            startDateAppFormat = "d MMM yyyy";
            currentDateAppFormat = "d MMM yyyy";
        } else {
            if (!strtDate.getMonth().equals(currentDeviceDate.getMonth())) {
                startDateAppFormat = "d MMM";
                currentDateAppFormat = "d MMM yyyy";
            } else {
                startDateAppFormat = "d";
                currentDateAppFormat = "d MMMM yyyy";
            }
        }


        DateTimeFormatter startDateAppFormatter = DateTimeFormatter.ofPattern(startDateAppFormat);
        DateTimeFormatter currDateAppFormatter = DateTimeFormatter.ofPattern(currentDateAppFormat);

        String currentDate = currentDeviceDate.format(currDateAppFormatter);
        String startDate = strtDate.format(startDateAppFormatter);

        System.out.println("current date: "+currentDate+"; Start: "+startDate);
    }

}

