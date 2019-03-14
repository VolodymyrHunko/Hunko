package coreJava;

import org.testng.annotations.Test;

import java.util.List;

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
}
