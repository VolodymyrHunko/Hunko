package coreJava;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream vs Collections:
 * Collections is in-memory data structure,
 * Stream - fixed data structure, in which elements are computed on demand
 * disigned for lambda
 */
public class StreamExample {
    List<String> strList = new ArrayList<>(Arrays.asList("Volo", "Olena", "Max", "Rita"));
    List<Integer> arr = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
    Stream<Integer> str = Stream.of(1,2,3,4,5,6); //add by value
    Stream<Integer> str2 = arr.stream(); // get from List

    @Test (description = "print the content")
    void test(){
        //print List
        for (Integer integer : arr) {
            System.out.print(integer + " ");
        }

        //print stream
        str.forEach((i -> System.out.println(i)));

        //print stream 2
        str2.forEach((System.out::println));
    }

    @Test (description = "filter the stream to List")
    void test2(){
        // create a new List by filter
        List<Integer> list = str.filter(x -> x>3).collect(Collectors.toList());
        System.out.println(list);

        //create array by filter
        Integer[] filtered = str2.filter(y -> y<8).toArray(Integer[]::new);
        System.out.println("length of array: "+filtered.length);

        //print filtered List by condition
        strList.stream()
                .filter((s) -> s.startsWith("V"))
                .forEach(System.out::println);
    }

    @Test (description = "sorting and mapping")
    void test3(){
        strList.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
