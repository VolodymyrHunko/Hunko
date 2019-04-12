package collectionsTesting;

import java.util.Comparator;

/*
 *   Class for implementation examples of Collection interface
 *   as Generic type <T>
 */
public class sampleCollectionClass implements Comparable<sampleCollectionClass> {
    private int id;
    private String name;
    private double rate;

    sampleCollectionClass(int id, String name, double rate) { //constructor
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }//to get var 'name'

    public Integer getId() {
        return id;
    }//to get var 'id'

    public Double getRate() {
        return rate;
    }//to get var 'rate'

    //to print out the object's data
    @Override
    public String toString() {
        return "[ id= " + id + ", name= " + name + ", rate= " + rate + "]";
    }

    //to use Comparable implementation
    @Override
    public int compareTo(sampleCollectionClass o) {
        int minValue = this.getRate().compareTo(o.rate);
        //if rate the same, sort by name
        return minValue == 0 ? this.getName().compareTo(o.name) : minValue;
    }

    int idComparator(sampleCollectionClass o){
        return this.getId().compareTo(o.id);
    }

    int nameComparator(sampleCollectionClass o){
        return this.getName().compareTo(o.name);
    }


    //use Comparator for sorting objects by 'name' property with anonymous class
    static Comparator<sampleCollectionClass> name2Comparator = new Comparator<sampleCollectionClass>() {
        @Override
        public int compare(sampleCollectionClass o1, sampleCollectionClass o2) {
            String compName1 = o1.getName().toLowerCase();
            String compName2 = o2.getName().toLowerCase();
            //ascending order
            return compName1.compareTo(compName2);
        }
    };

    //use Comparator for sorting the list by var 'id' as lambda exp
    static Comparator<sampleCollectionClass> id2Comparator = (o1, o2) -> {
        int compId1 = o1.getId();
        int compId2 = o2.getId();
        return compId1 - compId2;
    };
}

