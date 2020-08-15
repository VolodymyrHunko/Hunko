package coreJava;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//functional interface with one ABSTRACT method will used in tests 3 and 4
@FunctionalInterface
interface CheckPerson {
    boolean test(Person p);
}

//functional interface instead 'Predicate' standard interface
@FunctionalInterface
interface PrintPersonInfo{
    void printOut(Person p);
}

//function interface instead of 'Function' standard interface
//return String, accept two Generic parameters (instead 'Person p')
interface ReturnAdditionalValue<P,S> {
   String getValue(P p);
}

public class LambdaExamples {
    private List<Person> roster = Person.createRoster();

    // Approach 1: Create Methods that Search for Persons that Match One Characteristic
    private static void printPersonsOlderThan(@NotNull List<Person> roster, int age) {
        System.out.println("Persons older than " + age);
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods
    private static void printPersonsWithinAgeRange(@NotNull List<Person> roster, int low, int high) {
        System.out.println("Persons between the ages of " + low + " and " + high);
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    private static void printPersons(@NotNull List<Person> roster, CheckPerson tester) { //accepts object of defined interface
        System.out.println("Persons who are eligible for Selective Service ");
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 6: Use Standard Functional Interfaces (Predicate) with Lambda Expressions
    //accepts object of standard interface (all the same as with 'CheckPerson' interface
    private static void printPersonsWithPredicate(@NotNull List<Person> roster, Predicate<Person> tester) {
        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate parameter):");
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 7.1: Use Lambda Expressions Throughout Your Application
    //method accepts one more parameter - instance of standard interface 'Consumer' (return void) to call a printPerson
    //in this method I used the defined interface 'PrintPersonInfo' instead of standard 'Consumer'
    //and 'CheckPerson' instead of standard 'Predicate'
    private static void processPersons(@NotNull List<Person> roster, CheckPerson tester, PrintPersonInfo personInfo) {//Consumer<Person> block) {
        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate and Consumer parameters):");
        for (Person p : roster) {
            if (tester.test(p)) {
               // block.accept(p);
                personInfo.printOut(p);
            }
        }
    }

    // Approach 7.2, second example
    // accept one more parameter - instance of 'Function' interface to return String (changed to ReturnAdditionalValue)
    private static void processPersonsWithFunction(
            @NotNull List<Person> roster,
            CheckPerson tester, //Predicate<Person> tester,
            ReturnAdditionalValue<Person,String> mapper,//Function<Person, String> mapper,
            Consumer<String> block) {
        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate, Function, and Consumer parameters):");
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.getValue(p);//.apply(p);
                block.accept(data);
            }
        }
    }

    // Approach 8: Use Generics More Extensively<T, V>
    private static <X, Y> void processElements(
            @NotNull Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        System.out.println("Persons who are eligible for Selective Service " +
                "(generic version):");
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    @BeforeClass
    void beforeTest() {
        for (Person p : roster) {
            p.printPerson();
        }
        System.out.println("*****************************");
    }

    // Approach 1: Create Methods that Search for Persons that Match One
    // Characteristic
    @Test
    void approach1() {
        printPersonsOlderThan(roster, 20);
    }

    // Approach 2: Create More Generalized Search Methods
    @Test
    void approach2() {
        printPersonsWithinAgeRange(roster, 14, 30);
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    @Test
    void approach3() {
        System.out.println("(Local Class)");
        //local class (do not mandatory to implement/extend super class)
        class CheckPersonEligibleForSelectiveService implements CheckPerson {
            @Override
            public boolean test(Person p) { //should be public, such as interface is public
                return p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 &&
                        p.getAge() <= 50;
            }
        }
        CheckPerson chP = new CheckPersonEligibleForSelectiveService();
        //call the method by passing reference to inner class
        printPersons(roster, chP);
    }

    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    @Test
    void approach4() {
        System.out.println("(Anonymous class)");
        //new CheckPerson is anonymous class passed to method (can be replaced with lambda exp)
        printPersons(roster, new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE &&
                                p.getAge() >= 18 &&
                                p.getAge() <= 50;
                    }
                }
        );
    }

    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    @Test
    void approach5() {
        System.out.println("(lambda expression):");
        // anonymous class replaced with lambda exp
        printPersons(roster,
                (Person p) -> p.getGender() == Person.Sex.MALE &&
                             p.getAge() >= 18 &&
                             p.getAge() <= 50
        );
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    @Test
    void approach6() {
        // replace lambda exp (Person p) as standard functional interface 'Predicate' (or any another ?)
        printPersonsWithPredicate(roster,
                p -> p.getGender() == Person.Sex.MALE &&
                p.getAge() >= 18 &&
                p.getAge() <= 25
        );
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application
    @Test
    void approach7_1() {
        // added one argument to printPerson
        processPersons(roster,
                (Person p) -> p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 &&
                        p.getAge() <= 65,
                (Person p) -> p.printPerson()
        );
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application with 'method reference'
    @Test
    void approach7_1_1() {
        // added one argument to printPerson
        processPersons(roster,
                (Person p) -> p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 &&
                        p.getAge() <= 65,
                Person::printPerson //method reference
        );
    }

    // Approach 7, second example
    @Test
    void approach7_2() {
        // get email and sent it to method instead of printPerson
        processPersonsWithFunction(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );
    }

    // Approach 8: Use Generics More Extensively
    @Test
    void approach8() {
        // similar as previous
        processElements(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                //email -> System.out.println(email)
                System.out::println //replace lambda exp with method reference
        );
    }

    // Approach 9: Use Bulk Data Operations That Accept Lambda Expressions as Parameters
    @Test
    void approach9() {
        System.out.println("Persons who are eligible for Selective Service " +
                "(with bulk data operations):");

        roster
                .stream()

                .filter(p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25)

                .map(p -> p.getEmailAddress())

                .forEach(email -> System.out.println(email));
    }

    //example of aggregation operation
    @Test
    void aggregatingOperationTest(){
        // example to use 'forEach' aggregation operation on sample List
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.stream().filter(l -> l>3).forEach(l -> System.out.println(l)); //print all elements in list
        list.forEach(System.out::println); //replace lambda exp with method reference
        // aggregation on list of class Person
        roster
                .stream()
                .filter(e -> e.getGender() == Person.Sex.FEMALE)
                .forEach(e -> System.out.println(e.getName()));
    }

    //sort list of strings with lambda
    @Test
    void sortList(){
        List<String> list = new ArrayList<>();
        list.add("Xxxxx");
        list.add("Aaaaaa");
        list.add("Ffffff");
        list.add("");
        list.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println(list); // sorted list
        List<String> filtered =
                list.stream()
                .filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered); //filtered new list
    }
}

/*
 * class for testing LambdaExamples
 */
class Person {

    public enum Sex {
        MALE,
        FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    private Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
        this.name = nameArg;
        this.birthday = birthdayArg;
        this.gender = genderArg;
        this.emailAddress = emailArg;
    }

    int getAge() {
        return birthday
                .until(IsoChronology.INSTANCE.dateNow())
                .getYears();
    }

    void printPerson() {
        System.out.println(name + ", " + this.getAge());
    }

    Sex getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(@NotNull Person a, @NotNull Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    static List<Person> createRoster() {

        List<Person> roster = new ArrayList<>();
        roster.add(
                new Person(
                        "Fred",
                        IsoChronology.INSTANCE.date(1980, 6, 20),
                        Person.Sex.MALE,
                        "fred@example.com"));
        roster.add(
                new Person(
                        "Jane",
                        IsoChronology.INSTANCE.date(1990, 7, 15),
                        Person.Sex.FEMALE,
                        "jane@example.com"));
        roster.add(
                new Person(
                        "George",
                        IsoChronology.INSTANCE.date(1991, 8, 13),
                        Person.Sex.MALE,
                        "george@example.com"));
        roster.add(
                new Person(
                        "Bob",
                        IsoChronology.INSTANCE.date(2000, 9, 12),
                        Person.Sex.MALE,
                        "bob@example.com"));
        roster.add(
                new Person(
                        "Volo",
                        IsoChronology.INSTANCE.date(1963, 4, 22),
                        Person.Sex.MALE,
                        "volo@gmail.com"));

        return roster;
    }

}

/*
 * one more example of lambda ex with interface accepted more than one parameter
 */
class Calculator {
    @FunctionalInterface
    interface IntegerMath {
        int operation(int a, int b);
    }

    private int operateBinary(int a, int b, @NotNull IntegerMath op) {

        return op.operation(a, b);
    }

    public static void main(String... args) {
        //implement interface
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;

        Calculator myApp = new Calculator();
        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));

        // stream iterating example
        Stream.iterate(1, element->element+1)
                .filter(element->element%5==0)
                .limit(5)
                .forEach(System.out::println);
        Stream.iterate(2, v -> v+2)
                .filter(v -> v%3==0)
                .limit(5)
                .forEach(System.out::println);
    }
}

class findAppearanceInString {
    @FunctionalInterface
    interface searchable {
        int findAppirance(String s, char c);
    }
    public static void main(String [] arg){
        searchable srch1 = (s, c) -> {
            char [] ch = s.toCharArray();
            int count = 0;
            for(char y : ch){
                if(y == c)
                    count++;
            }
            return count;
        };
        System.out.println("Results: "+srch1.findAppirance("Volodymyr", 'y'));
    }
}

class IterateMapUsingLambda {
    public static void main(String[] args) {
        Map<String, Integer> prices = new HashMap<>();
        prices.put("Apple", 50);
        prices.put("Orange", 20);
        prices.put("Banana", 10);
        prices.put("Grapes", 40);
        prices.put("Papaya", 50);
		/* Iterate without
		   for (Map.Entry<String, Integer> entry : prices.entrySet()) {
		   System.out.println("Fruit: " + entry.getKey() + ", Price: " + entry.getValue());
		   }
		*/
        prices.forEach((k,v)->System.out.println("Fruit: " + k + ", Price: " + v));

        List <String>names = new ArrayList<>();
        names.add("Ajay");
        names.add("Ben");
        names.add("Cathy");
        names.add("Dinesh");
        names.add("Tom");
		/* Iterate without
		 Iterator iterator = names.iterator();
		 while (iterator.hasNext()) {
			System.out.println(iterator.next());
		 }
		 implements 'Consumer' interface
		*/
        names.forEach(System.out::println);
    }
}

/*
* class for using lambda in find ODD/EVEN, PRIME/COMPOSE, PALINDROME numbers
* using functional interface and MyMath class
 */
class Solution {
    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        int num = 151;

        op = ob.isOdd(); //get implementation of interface as ODD/EVEN
        ret = ob.checker(op, num); //return boolean by passing num into implementation of interface
        String ans1 = (ret) ? "ODD" : "EVEN";

        op = ob.isPrime();
        ret = ob.checker(op, num);
        String ans2 = (ret) ? "PRIME" : "COMPOSITE";

        op = ob.isPalindrome();
        ret = ob.checker(op, num);
        String ans3 = (ret) ? "PALINDROME" : "NOT PALINDROME";

        System.out.println(ans1 + "..." + ans2 + ",,," + ans3);
    }
}

@FunctionalInterface
interface PerformOperation {
    boolean check(int a);
}
/*
* class for using lambda in examples of 'Solution' class
 */
class MyMath {
    public PerformOperation isOdd(){
        return (a -> a%2!=0);
    }
    public PerformOperation isPrime(){
        return (a -> {
            if(a <= 1)
                return true;
          for(int i = 2; i<=a/2; i++){
              if (a%i == 0)
                  return false;
          }
            return true;
        });
    }
    public PerformOperation isPalindrome(){

        return (a -> {
            int rem, temp=a, sum=0;
            while (a>0){
              rem = a%10;
              sum = (sum*10)+rem;
              a = a/10;
          }
            return temp == sum;
        });
    }

    public boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }
}