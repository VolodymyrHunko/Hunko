package coreJava;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularExpressionsExamples {
    public static void main(String [] args){
        /*
        * 1) java.util.regex.Pattern – Used for defining patterns
        * 2) java.util.regex.Matcher – Used for performing match operations on text using patterns
         */
        String content = "This is Chaitanya from Beginnersbook.com.";
        String pattern = ".*boOk.*";
        String pattern2 = ".com";

        //for single match we can use it...
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("The text contains 'boOk'? " + isMatch);
        //several examples>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println(Pattern.matches("[tT]im|[jJ]in", "Tim"));//True
        System.out.println(Pattern.matches("[tT]im|[jJ]in", "jin"));//True

        // returns true if the string contains "abc" at any place
        System.out.println(Pattern.matches(".*abc.*", "deabcpq"));//True

        // returns true if the string does not have a number at the beginning
        System.out.println(Pattern.matches("^[^\\d].*", "123abc")); //False
        System.out.println(Pattern.matches("^[^\\d].*", "abc123")); //True

        // returns true if the string contains of three letters
        System.out.println(Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "aPz"));//True
        System.out.println(Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "aAA"));//True
        System.out.println(Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "apZx"));//False

        // returns true if the string contains 0 or more non-digits
        System.out.println(Pattern.matches("\\D*", "abcde")); //True
        System.out.println(Pattern.matches("\\D*", "abcde123")); //False

        // Boundary Matchers example ^ denotes start of the line $ denotes end of the line
        System.out.println(Pattern.matches("^This$", "This is Chaitanya")); //False
        System.out.println(Pattern.matches("^This$", "This")); //True
        System.out.println(Pattern.matches("^This$", "Is This Chaitanya")); //False

        //for matching multiply times we should use Pattern.compile()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(content);
        isMatch = matcher.matches();
        System.out.println("The text contains case insensitive 'booOk'? " + isMatch);

        //for find the place of pattern in string...
        Pattern pat2 = Pattern.compile(pattern2);
        Matcher matcher2 = pat2.matcher(content);
        while(matcher2.find()) {
            System.out.println("Found at: "+ matcher2.start()+" - " + matcher2.end());
        }
    }

    @Test (description = "convert phone #")
    void testRegEx(){
        String phoneNumber = "123-456-789";
        String regularExpression = "\\D*"; //"[^0-9]" select all non-numbers
        String currentNumber = phoneNumber.replaceAll(
                regularExpression, "");
        System.out.println(currentNumber);
    }

    @Test (description = "validate SSN")
    void testSSN(){
        if("1qw2-222".matches("\\w{4}-\\w{3}")){
            System.out.println("pass");
        }
        else{
            System.out.println("false");
        }
    }

    @Test (description = "validate IP address")
    void testIP(){
        // \d{1,2} - catch 0-9, 00-99
        // [0-1]\d{2} - catch 000-099, 100-99
        // 2[0-4]\d - catch 200-249
        // d|25[0-5] - catch 250-255
        String ip = "(\\d{1,2}|[0-1]\\d{2}|2[0-4]\\d|25[0-5])";
        if("000.123.22.255".matches(ip+"\\."+ip+"\\."+ip+"\\."+ip)){
            System.out.println("pass");
        }
        else{
            System.out.println("false");
        }
    }

    @Test (description = "delite double words in stringi line")
    void testDupe(){
        String input = "This this is a word Word worD";
        String regex = "\\b(\\w+)(\\s+\\1\\b)+"; /*start any word and assign it as group#1, next word is the same
        as group #1 after one or more space, + repeat again */
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        while (m.find()) {
            System.out.println("Found at: "+ m.start()+" - " + m.end());
            input = input.replaceAll(m.group(), m.group(1)); /* replace all found group with first group*/
        }
        System.out.println(input);
    }
}



