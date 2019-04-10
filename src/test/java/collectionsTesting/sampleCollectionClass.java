package collectionsTesting;

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
}

