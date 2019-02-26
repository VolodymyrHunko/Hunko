package AutoValue;

import org.testng.annotations.Test;

public class AnimalTest {
    @Test
    public void test(){
        Animal dog = Animal.create("dog", 3);
        Animal cat = Animal.create("cat", 4);
        Animal cat2 = Animal.create("cat", 4);
        System.out.println("dog hashCode: "+dog.hashCode()+" -> "+dog.toString());
        System.out.println("cat  hashCode: "+cat.hashCode()+" -> "+cat.toString());
        System.out.println("cat2 hashCode: "+cat2.hashCode()+" -> "+cat2.toString());
        System.out.println(cat.equals(cat2));
    }
}
