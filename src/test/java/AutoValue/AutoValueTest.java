package AutoValue;

import org.testng.annotations.Test;

public class AutoValueTest {
    @Test
    public void testAnimal(){
        Animal dog = Animal.create("dog", 3);
        Animal cat = Animal.create("cat", 4);
        Animal cat2 = Animal.create("cat", 4);
        System.out.println("dog hashCode: "+dog.hashCode()+" -> "+dog.toString());
        System.out.println("cat  hashCode: "+cat.hashCode()+" -> "+cat.toString());
        System.out.println("cat2 hashCode: "+cat2.hashCode()+" -> "+cat2.toString());
        System.out.println(cat.equals(cat2));
    }

    @Test
    public void testMoney(){
        MoneyWithBuilder grivna =  MoneyWithBuilder.builder()
                .setAmount(10)
                .setMoney("GRN")
                .build();
        MoneyWithBuilder rubl = MoneyWithBuilder.builder()
                .setAmount(10)
                .setMoney("RUB")
                .build();
        System.out.println("GRN == RUB : "+grivna.equals(rubl));
        System.out.println(grivna.toString());

    }
}
