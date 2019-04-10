//package collectionsTesting;
//
//import java.util.*;
//
//
//public class collectionsExamples {
//
//    public static void main(String args[]) {
//        int[] a = {3, 5, 1, 0, 8, 15};
//        String[] strings = {"abc", "Axk", "..."};
//        sample object1 = new sample(a);
//        sample objact2 = new sample(strings);
//        sample object3 = new sample(10, "Volo", 8.3);
//        sample object4 = new sample(12, "Max", 9.1);
//        sample objact5 = new sample(15, "Ole", 6.6);
//
//        //convert array to ArrayList and store it as new objects.
//        ArrayList<Integer> intList = object1.convertIntToList();
//        ArrayList<String> strList = objact2.convertStrToList();
//        intList.add(0, 33); //insert new value
//        System.out.println("list.toString()=>" + intList.toString());
//        strList.forEach((n) -> System.out.println("forEach() with lambda=> "+n));//print list's items by using forEach() and lambda !ONLY JAVA 8!
//
//        //sort ArrayList
//        intList.sort(Integer::compareTo);
//        System.out.println("Sorted List-> "+intList.toString());
//
//        //replays element in index '1'
//        intList.set(1, 15);
//
//        //get the index of 'abc' value
//        int index = strList.indexOf("Axk");
//        System.out.println("Index of 'Axk' => "+index);
//        index = intList.indexOf(15);
//        System.out.println("Index of '15' => "+index);
//
//        //parallel sort of Array
//        Arrays.parallelSort(a);
//        System.out.println("Parallel Sorted List-> "+ Arrays.toString(a));
//
//        //sort using Comparator
//        strList.sort(Comparator.naturalOrder());
//        System.out.println("Sorted as natural Order -> "+strList.toString());
//
//        //sorting as Collection/Arrays classes
//        System.out.println("Sorted ArrayList as Collection class=> " + objact2.sortingMethod(strList).toString());
//        System.out.println("Sorted Array as Arrays class=>" + Arrays.toString(object1.sortingMethod(a)));
//
//        //binary search of Array after sorting, if no found -> return as '-missing point -1' of array
//        System.out.println("Search invalid value 'zxk', index: " + objact2.search(strings, "b"));
//
//        //swapping of Array
//        object1.swapElements(intList);
//
//        //get max/min value
//        object1.maxValue(intList);
//
//        //convert List to treeSet
//        object1.listToTreeSet(intList);
//
//        //sort ArrayList of objects
//        List<sample> arraylist = new ArrayList<sample>();
//        arraylist.add(object3);
//        arraylist.add(object4);
//        arraylist.add(objact5);
//        for (sample str : arraylist) {
//            System.out.println("before sort=> " + str);
//        }
//        Collections.sort(arraylist, sample.nameComparator);//sort arrayList of objects with comparator
//        for (sample str : arraylist) {
//            System.out.println("after sort by name=> " + str);
//        }
//        Collections.sort(arraylist, sample.idComparator);//sort arrayList of objects with comparator
//        for (sample str : arraylist) {
//            System.out.println("after sort by rate=> " + str);
//        }
//        Collections.sort(arraylist);//do not need comparator - we implemented Comparable interface in sorted class
//        for (sample str : arraylist) {
//            System.out.println("after sort by rate and name=> " + str);
//        }
//
//        //using iterator
//        Iterator<Integer> it = intList.iterator();
//        System.out.println("Using iterator=> ");
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//
//        //using listIterator
//        ListIterator<String> li = strList.listIterator();
//        System.out.println("Using listIterator for reverse output=> "+li.hasPrevious());
//        while (li.hasPrevious()){
//            System.out.println(li.previous());
//        }
//        System.out.println("Using listIterator for forward output=> ");
//        while (li.hasNext()){
//            System.out.println(li.next());
//        }


////1.a convert int to ArrayList : Arrays.asList DOES NOT DEAL with boxing, so array has to be Integer
//
//import org.testng.annotations.Test;
//
//private ArrayList<Integer> convertToList(Integer [] li) {
//        return new ArrayList<>(Arrays.asList(li));
//        }
//
////1.b String can be converted with .asList()
//private ArrayList<String> convertToList(String [] st) {
//        return new ArrayList<>(Arrays.asList(st));
//        }
//
////2.a sort ArrayList
//private ArrayList<String> sortingMethod(ArrayList<String> noSort) {
//        Collections.sort(noSort);
//        return noSort;
//        }
//
////2.b overloaded sort() method
//private Integer[] sortingMethod(Integer[] intArray) {
//        Arrays.sort(intArray);
//        return intArray;
//        }
//
//@Test // 1 && 2 methods
//    void test_1_2() {
//            //test 2.a : sort String ArrayList
//            System.out.println(sortingMethod(convertToList(str)));
//            //test 2.b : sort Integer Array
//            for(int x : sortingMethod(i)) {
//            System.out.print(x);
//            }
//            }
//
////3.a binary searching after sorting!
//private int search(String[] noSort, String comp) {
//        Arrays.sort(noSort);
//        return Collections.binarySearch(Arrays.asList(noSort), comp);
//        }
//
////3.b binary searching after sorting!
//private int search(Integer [] sort, Integer comp) {
//        return Arrays.binarySearch(sort, comp);
//        }
//
//@Test // 3 method
//    void test_3() {
//            System.out.println("Index of 'two' in sorted array:" + search(str, "two"));
//            System.out.println("Index of '6' in sorted array:" + search(sortingMethod(i), 6));
//            }
//
//            //4.a swap arrayList elements
//            void swapElements(ArrayList<Integer> sorted) {
//        System.out.println("before swapping=> " + sorted.toString());
//        Collections.swap(sorted, 0, sorted.size() - 1);
//        System.out.println("after swapping=> " + sorted.toString());
//        }
//
//        //5.a max value of Array
//        void maxValue(ArrayList<Integer> array) {
//        System.out.println("Max value: " + Collections.max(array));
//        }
//
//        //6.a convert list to treeSet
//        void listToTreeSet(ArrayList<Integer> aList) {
//        Set<Integer> hSet = new TreeSet<Integer>(aList);
//        System.out.println("Sorted hashSet from arrayList=> " + hSet.toString());
//        }
//
////7.a use Comparator for sorting objects by 'name' property
//static Comparator<SampleCollectionClass> nameComparator = new Comparator<SampleCollectionClass>() {
//@Override
//public int compare(SampleCollectionClass o1, SampleCollectionClass o2) {
//        String compName1 = o1.getName().toLowerCase();
//        String compName2 = o2.getName().toLowerCase();
//        //ascending order
//        return compName1.compareTo(compName2);
//        }
//        };
//
////7.b use Comparator for sorting the list by var 'rate'
//static Comparator<SampleCollectionClass> idComparator = new Comparator<SampleCollectionClass>() {
//@Override
//public int compare(SampleCollectionClass o1, SampleCollectionClass o2) {
//        int compId1 = o1.getId();
//        int compId2 = o2.getId();
//        return compId1 - compId2;
//        }
//        };

//    }
//
//
