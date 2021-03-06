import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.LocalTime.now;

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

        String month = String.valueOf(currentDeviceDate.getMonth());
        int day = currentDeviceDate.getDayOfMonth();
        LocalTime time =  now();
        int hour = time.getHour();
        int min = time.getMinute();

        System.out.println(month+"_"+day+"_"+hour+":"+min);

        System.out.println("current date: "+currentDate+"; Start: "+startDate);
    }

    @Test (description = "settings of calendar")
    void method_(){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(2020,4-1, 8, 03, 00);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm");
        String end = sdf.format(c.getTime());
        c.add(Calendar.DATE, -6);
        String start = sdfStart.format(c.getTime());


        System.out.println("Calendar has settings: " + end + " - " + start );
        System.out.println(c.get(Calendar.DATE));
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

    @Test
    void method_4(){
        String a = "31 December 2019 – 6 January 2020";
        String b = "31 December 2019 – 6 January 2020";
        boolean x = a.equals(b);
        System.err.println(x);
    }
}

