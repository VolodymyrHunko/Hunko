package coreJava;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class StringManipulation {
    String s = "My string for revert";

    @Test (description = "reverting strings in diff way")
    void testPractice() throws IOException {
        //1- by using Array list & Collections.reverse
        List<Character> l = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            l.add(s.charAt(i));
        }
        Collections.reverse(l);
        System.out.println(l);
        //2- By using StringBuilder.reverse
        StringBuilder sb = new StringBuilder(s);
        System.out.println(sb.reverse().toString());
        //3- without reverse, by using recursive method
        String reverseString = recursiveSwap(s);
        System.out.println(reverseString);
    }

    /*
    recursive method call itself until s.length becomes < 1, then returns reversed string
     */
    private String recursiveSwap(String s) {
        if ((null == s) || (s.length() <= 1)) {
            return s;
        }
        System.out.println(s);
        return recursiveSwap(s.substring(1)) + s.charAt(0);
        }

    @Test (description = "converting num <-> string")
    void testPractice_2(){
        //convert int to string
       String a = String.valueOf(1);
        Object b = String.valueOf(5);
        System.out.println(a+b); //15
        // convert string to int
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b.toString());
        System.out.println(x+y); //6
    }

    @Test(description = "count an occurrences in string")
    void testPractice_3(){
        HashMap<Character, Integer> occurrencesMap = new HashMap<Character, Integer>();
        char[] strArray = s.toCharArray();
        //populate the MAP as Char=Key & Occurrences = value
        for (char c : strArray) {
            if(occurrencesMap.containsKey(c)) {
                occurrencesMap.put(c, occurrencesMap.get(c)+1);
            }
            else {
                occurrencesMap.put(c, 1);
            }
        }
        //iterate through entrySet to get condition of value
        for (Map.Entry<Character, Integer> entry : occurrencesMap.entrySet()) {
            Integer key = entry.getValue();
            if(key == 1)
            System.out.println(entry.getKey());
        }

        /*
         *one more way to segregate single and double chars in string
         */
        Set <Character> set = new HashSet<>();
        Set <Character> set2 = new HashSet<>();
        for(Character ch : strArray){
            if(!set.contains(ch)){
                set.add(ch);
            }else{
                set2.add(ch);
            }
        }
        System.out.println("All unique Chars: "+set.toString());
        System.out.println("Double Chars: "+set2.toString());
    }

    @Test (description = "count vowels/consonants in string")
    void test_4(){
        String str = "wedhuinvycccrr";
        int vowels=0;
        int conson=0;
        for(int i=0; i<str.length(); i++){
            String c = String.valueOf(str.charAt(i));
            if(Pattern.matches("[Aa]|[Ee]|[Oo]|[Uu]|[Yy]|[Jj]|[Ii]|[Qq]", c)){
                vowels++;
            }else{
                conson++;
            }
        }
        System.out.println("Vowels: "+vowels+", Consonants: "+conson);
    }
 }
