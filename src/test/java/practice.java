import org.junit.Assert;
import org.testng.annotations.Test;

import java.sql.Array;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Test (description = "format date")
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

    @Test (description = "string to array")
    void method_1 (){
        String x = ".14.0 ccc, iii";
        String y = x.replaceAll("\\W+", " ");
        y = y.trim();
        char [] ch = y.toCharArray();
        String [] s = y.split(" ");
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(ch));
    }

    @Test (description = "work with Map")
    void method_3(){
        Map<Character, Integer> hMap = new HashMap<>();
        String str = "Hello world here!";
        String str2 = str.toLowerCase().replaceAll("\\W+", "");
        char[] arr = str2.toCharArray();
        for(char ch : arr){
            if(hMap.containsKey(ch)){
                hMap.put(ch, hMap.get(ch)+1);
            }else{
                hMap.put(ch, 1);
            }
        }

        System.out.println(hMap.entrySet());
        System.out.println(hMap.get('h'));
        for(char c : hMap.keySet()) {
            System.out.println(c);
        }
    }
}

