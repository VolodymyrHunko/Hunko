package collectionsTesting;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;


public class collectionsExamples {

    // public static void main(String args[]) {
    private int[] a = {3, 5, 1, 0, 8, 15};
    private Integer[] ab = {2, 8, 12, 4, -1, 4};
    private String[] strings = {"abc", "Axk", "..."};
    private ArrayList<Integer> intList;
    private ArrayList<String> strList;
    private Set<Integer> hSet;
    private ArrayList<sampleCollectionClass> objList;

    @BeforeTest
    void setArrayList(){
        //convert array to ArrayList and store it as new objects
        intList =  new ArrayList<>(Arrays.asList(ab));
        strList = new ArrayList<>(Arrays.asList(strings));

        //create the arrayList of objects
        sampleCollectionClass object3 = new sampleCollectionClass(10, "Volo", 8.3);
        sampleCollectionClass object4 = new sampleCollectionClass(4, "Max", 9.1);
        sampleCollectionClass objact5 = new sampleCollectionClass(15, "Ole", 8.3);
        objList = new ArrayList<>();
        objList.add(object3);
        objList.add(object4);
        objList.add(objact5);
    }

    /*
     *   Addition to, Replacing and printing of Array Lists
     */
    @Test
    void test_1() {
        //insert new value into exact index
        intList.add(0,33);

        //replace element in index '1'
        intList.set(1,15);

        System.out.println("list.toString()=>"+intList.toString());

        //print list's items by using forEach() and lambda as implementation of 'Consumer' interface !ONLY JAVA 8!
        System.out.print("forEach() with lambda=> ");
        strList.forEach((n)->System.out.print(n + ", "));

        System.out.print("\nforEach() iteration => ");
        for (String n : strList) {
            System.out.print(n + ", ");
        }
    }

    /*
     * Sorting of ArrayLists and Arrays, HashSet
     */
    @Test
    void test_2() {
        //sort ArrayList with comparator's method 'compareTo'
        intList.sort(Integer::compareTo);
        System.out.println("Sorted int List-> " + intList.toString());

        //sort ArrayList using Collections class
        Collections.sort(strList);
        System.out.println("Sorted String List-> " + strList.toString());

        //parallel sort of Array (not List)
        Arrays.parallelSort(a);
        System.out.println("Parallel Sorted List-> "+Arrays.toString(a));

        //sort using Comparator
        strList.sort(Comparator.reverseOrder());
        System.out.println("Sorted as revers Order -> "+strList.toString());

        //sorting as Collection/Arrays classes
        System.out.println("Sorted ArrayList as Collection class=> " + sortingMethod(strList).toString());
        System.out.println("Sorted Array as Arrays class=>" + Arrays.toString(sortingMethod(ab)));

        //convert List to treeSet
        listToTreeSet(intList);
        System.out.println("hashSet from arrayList=> " + hSet.toString());
    }

    //2.a sort ArrayList
    private ArrayList<String> sortingMethod(ArrayList<String> noSort) {
        Collections.sort(noSort);
        return noSort;
    }

    //2.b overloaded sortingMethod() method
    private Integer[] sortingMethod(Integer[] intArray) {
        Arrays.sort(intArray);
        return intArray;
    }

    //2.c convert list to treeSet
    private void listToTreeSet(ArrayList<Integer> aList) {
        hSet = new TreeSet<Integer>(aList);
    }

    /*
     * getting index of element, convert to treeSet
     */
    @Test
    void test_3() {
        //get the index of 'Axk' value
        int index = strList.indexOf("Axk");
        System.out.println("Index of 'Axk' => " + index);

        //return '-1' index, no value
        index = intList.indexOf(15);
        System.out.println("Index of '15' => " + index);
    }

    /*
     * searching of value, swapping of elements, get max/min value,
     */
    @Test
    void test_4() {
        //binary search of Array after sorting, if no found -> return as '-missing point -1' of array
        System.out.println("Search invalid value 'zxk', index: " + search(strings, "zdk"));
        System.out.println("Search valid value 'abc', index: " + search(ab, 8));

        //swapping of ArrayList's first and Last elements
        swapElements(intList);

        //get max/min value
        maxValue(intList);
    }

    //4.a binary searching after sorting!
    private int search(String[] noSort, String comp) {
        Arrays.sort(noSort);
        return Collections.binarySearch(Arrays.asList(noSort), comp);
    }

    //4.b binary searching after sorting!
    private int search(Integer[] sort, Integer comp) {
        return Arrays.binarySearch(sort, comp);
    }

    //4.c swap arrayList elements
    void swapElements(ArrayList<Integer> s) {
        System.out.println("before swapping=> " + s.toString());
        Collections.swap(s, 0, s.size() - 1);
        System.out.println("after swapping first and last => " + s.toString());
    }

    //4.d max value of Array
    void maxValue(ArrayList<Integer> array) {
        System.out.println("Max value: " + Collections.max(array));
    }

    /*
     * sort ArrayList of objects
     */
    @Test
    void test_5() {
        //before sorting List of obj
        for ( sampleCollectionClass str : objList) {
            System.out.println("before sort=> " + str);
        }

        //sort arrayList of objects with comparator (Can use Collections.sort instead)
        objList.sort(sampleCollectionClass::nameComparator);
        for (sampleCollectionClass str : objList) {
            System.out.println("after sort by name=> " + str);
        }

        //sort arrayList of objects with users defined comparator
        objList.sort(sampleCollectionClass::idComparator);
        for (sampleCollectionClass str : objList) {
            System.out.println("after sort by id=> " + str);
        }

        //do not need comparator - we implemented Comparable interface in sorted class
        Collections.sort(objList);
        for (sampleCollectionClass str : objList) {
            System.out.println("after sort by rate and name=> " + str);
        }
    }

//    //using iterator
//    Iterator<Integer> it = intList.iterator();
//        System.out.println("Using iterator=> ");
//        while(it.hasNext())
//
//    {
//        System.out.println(it.next());
//    }
//
//    //using listIterator
//    ListIterator<String> li = strList.listIterator();
//        System.out.println("Using listIterator for reverse output=> "+li.hasPrevious());
//        while(li.hasPrevious())
//
//    {
//        System.out.println(li.previous());
//    }
//        System.out.println("Using listIterator for forward output=> ");
//        while(li.hasNext())
//
//    {
//        System.out.println(li.next());
//    }
//
//

//

//    @Test
//        // 1 && 2 methods
//    void test_1_2() {
//        //test 2.a : sort String ArrayList
//        System.out.println(sortingMethod(convertToList(str)));
//        //test 2.b : sort Integer Array
//        for (int x : sortingMethod(i)) {
//            System.out.print(x);
//        }
//    }
//

//
//    @Test
//        // 3 method
//    void test_3() {
//        System.out.println("Index of 'two' in sorted array:" + search(str, "two"));
//        System.out.println("Index of '6' in sorted array:" + search(sortingMethod(i), 6));
//    }
//

//

//

//
//    //7.a use Comparator for sorting objects by 'name' property
//    static Comparator<sampleCollectionClass> nameComparator = new Comparator<sampleCollectionClass>() {
//        @Override
//        public int compare(sampleCollectionClass o1, sampleCollectionClass o2) {
//            String compName1 = o1.getName().toLowerCase();
//            String compName2 = o2.getName().toLowerCase();
//            //ascending order
//            return compName1.compareTo(compName2);
//        }
//    };
//
//    //7.b use Comparator for sorting the list by var 'rate'
//    static Comparator<sampleCollectionClass> idComparator = new Comparator<sampleCollectionClass>() {
//        @Override
//        public int compare(sampleCollectionClass o1, sampleCollectionClass o2) {
//            int compId1 = o1.getId();
//            int compId2 = o2.getId();
//            return compId1 - compId2;
//        }
//    };

}


