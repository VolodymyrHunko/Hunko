package collectionsTesting;

import org.testng.annotations.Test;

import java.util.*;

/*
 *   Class for implementation examples of Collection interface
 *   as Generic type <T>
 */
public class SampleCollectionClass<T> implements Comparable<SampleCollectionClass> {
    private int[] a = {1, 3, 2, 5};
    private Integer [] aa = {2, 4, 6, 8};
    private String[] str = {"one", "two", "three", "for"};
    private int id;
    private String name;
    private double rate;

    SampleCollectionClass() { //constructor
    }

    SampleCollectionClass(int[] q) {//constructor
        this.a = q;
    }

    SampleCollectionClass(String[] str) { //constructor
        this.str = str;
    }

    SampleCollectionClass(int id, String name, double rate) { //constructor
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

    //1.a convert int to ArrayList : Arrays.asList DOES NOT DEAL with boxing
    ArrayList<Integer> convertIntToList() {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int aB : a) {
            list1.add(aB);
        }
        return list1;
    }

    //1.b String can be converted with .asList()
    ArrayList<String> convertStrToList() {
        return new ArrayList<String>(Arrays.asList(str));
    }

    ArrayList<T> convertObjectToList(){
        return new ArrayList<T>(Arrays.asList((T) aa));
    }

    //2.a sort ArrayList
    ArrayList<String> sortingMethod(ArrayList<String> noSort) {
        System.out.println(noSort);
        Collections.sort(noSort);
        System.out.println(noSort);
        return noSort;
    }
    @Test
    void test1() {
        System.err.println(convertStrToList());
        System.err.println(convertIntToList());
        System.err.println(convertObjectToList());
        Integer [] i = {1, 5, 7};
        System.out.println(i[1]);
    }

    //2.b overloaded sort() method
    int[] sortingMethod(int[] intArray) {
        Arrays.sort(intArray);
        return intArray;
    }

    //3.a binary searching after sorting!
    int search(String[] noSort, String comp) {
        Arrays.sort(noSort);
        return Collections.binarySearch(Arrays.asList(noSort), comp);
    }

    //4.a swap arrayList elements
    void swapElements(ArrayList<Integer> sorted) {
        System.out.println("before swapping=> " + sorted.toString());
        Collections.swap(sorted, 0, sorted.size() - 1);
        System.out.println("after swapping=> " + sorted.toString());
    }

    //5.a max value of Array
    void maxValue(ArrayList<Integer> array) {
        System.out.println("Max value: " + Collections.max(array));
    }

    //6.a convert list to treeSet
    void listToTreeSet(ArrayList<Integer> aList) {
        Set<Integer> hSet = new TreeSet<Integer>(aList);
        System.out.println("Sorted hashSet from arrayList=> " + hSet.toString());
    }

    //7.a use Comparator for sorting objects by 'name' property
    static Comparator<SampleCollectionClass> nameComparator = new Comparator<SampleCollectionClass>() {
        @Override
        public int compare(SampleCollectionClass o1, SampleCollectionClass o2) {
            String compName1 = o1.getName().toLowerCase();
            String compName2 = o2.getName().toLowerCase();
            //ascending order
            return compName1.compareTo(compName2);
        }
    };

    //7.b use Comparator for sorting the list by var 'rate'
    static Comparator<SampleCollectionClass> idComparator = new Comparator<SampleCollectionClass>() {
        @Override
        public int compare(SampleCollectionClass o1, SampleCollectionClass o2) {
            int compId1 = o1.getId();
            int compId2 = o2.getId();
            return compId1 - compId2;
        }
    };

    //to print out the object's data
    @Override
    public String toString() {
        return "[ id=" + id + ", name=" + name + ", rate=" + rate + "]";
    }

    //to use Comparable implementation
    @Override
    public int compareTo(SampleCollectionClass o) {
        int minValue = this.getRate().compareTo(o.rate);
        //if rate the same, sort by name
        return minValue == 0 ? this.getName().compareTo(o.name) : minValue;
    }

}

