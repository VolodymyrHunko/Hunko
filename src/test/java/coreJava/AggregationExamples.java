package coreJava;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AggregationExamples {
    // Create sample data
    List<Person> roster = Person.createRoster();

    @Test
    void test1(){
        System.out.println("Members of the collection (for-each loop):");
        for (Person p : roster) {
            System.out.println(p.getName());
        }
    }

    @Test
    void test2(){
        System.out.println("Members of the collection (bulk data operations):");
        roster
                .stream()
                .forEach(e -> System.out.println(e.getName()));
    }

    @Test
    void test3(){
        System.out.println("Male members of the collection (bulk data operations):");
        roster
                .stream()
                .filter(e -> e.getGender() == Person.Sex.MALE)
                .forEach(e -> System.out.println(e.getName()));
    }

    @Test
    void test4() {
        System.out.println("Male members of the collection (for-each loop):");
        for (Person p : roster) {
            if (p.getGender() == Person.Sex.MALE) {
                System.out.println(p.getName());
            }
        }
    }

    @Test
    void test5(){
        double average = roster
                .stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        System.out.println("Average age of male members (bulk data operations): " + average);
    }

    //print random 10 positive even numbers sorted
    @Test
    void test6(){
        Random random = new Random();
        random
                .ints(0,100)
                .filter(i -> i%2 == 0)
                .limit(10)
                .sorted()
                .forEach(System.out::println);
    }

    //use the map to print n^2
    @Test
    void test7(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        //get list of unique squares
        List<Integer> squaresList =
                numbers
                        .stream()
                        .map( i -> i*i)
                        .distinct()
                        .sorted()
                .collect(Collectors.toList());
        System.out.println(numbers+"\n"+squaresList);
    }
}
