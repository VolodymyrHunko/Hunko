package collectionsTesting;//import java.util.HashMap;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

public class mapExamplees {
    // This is how to declare HashMap
    Map<Integer, String> hMap;
    Set<Map.Entry<Integer, String>> setOfEntries;

    @BeforeTest
    void setHashMap() {
        hMap = new HashMap<>();
        //Adding elements to HashMap
        hMap.put(12, "USA");
        hMap.put(2, "India");
        hMap.put(7, "Not Russia");
        hMap.put(49, "Canada");
        hMap.put(3, "Ukraine");
        hMap.put(7, "Russia"); //put the new value in the same key (will replace it);
        hMap.put(9, "USA"); //put the double value for diff key

        setOfEntries = hMap.entrySet(); //get the Set list of all entries
        System.out.println("Set of all entries=> " + setOfEntries);
    }


    @Test (description = "Display content using Iterator (Old stile)")
    public void test_1() {
        Iterator<Map.Entry<Integer, String>> iterator = setOfEntries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> mEntry = iterator.next(); //Entry as class of Map, or Entry <K,V>
            System.out.println("key is: " + mEntry.getKey() + " & Value is: " + mEntry.getValue());
        }
    }

    @Test (description = "we can use for each construction too... (new style, without Iterator)")
    public void test_2() {
        for (Map.Entry<Integer, String> me : hMap.entrySet()) {
            System.out.println("Key: " + me.getKey() + " & Value: " + me.getValue());
        }
    }

    @Test (description = "Get values & keys, !!!! get all keys with same Value !!!!!")
    public void test_3() {
        //get all values of map
        System.out.println("All values=> " + hMap.values());
        System.out.println("All keys=> " + hMap.keySet());
        //Get values based on key
        System.out.println("Value at index 9 is: " + hMap.get(9));

        // Get the all keys based on value using new HashSet()
        Set<Integer> keys = new HashSet<>();
        for (Map.Entry<Integer, String> entry : hMap.entrySet()) {
            if (Objects.equals("USA", entry.getValue())) {
                keys.add(entry.getKey()); //add to new hashSet
            }
        }
        System.out.println("USA value has a keys=> " + keys.toString());
    }

    @Test (description = "Remove values based on key")
    public void test_4() {
        hMap.remove(5);//remove not existed key -> no effect
        hMap.remove(7);
        System.out.println("Map key and values after removal:");
        Set<Map.Entry<Integer, String>> set2 = hMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Integer, String> mentry2 = iterator2.next();
            System.out.print("Key is: " + mentry2.getKey() + " & Value is: ");
            System.out.println(mentry2.getValue());
        }
    }

    @Test (description = "sort Map")
    public void test_5() {
        //        sort Map by key
        Map<Integer, String> sortMap = new TreeMap<>(hMap); //convert hashMap to treeMap
        for (Map.Entry<Integer, String> me2 : sortMap.entrySet()) {
            System.out.println("After sorting => Key: " + me2.getKey() + " & Value: " + me2.getValue());
        }

//        //to sort by value, we should use Comparator (old style)
//        //first convert entrySet to List
//        List<Map.Entry<Integer, String>> list = new ArrayList<>(hMap.entrySet());
//        list.sort(new Comparator() {//create Comparator inside sort()
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((Comparable) ((Map.Entry) (o1)).getValue())
//                        .compareTo(((Map.Entry) (o2)).getValue());
//            }
//        });
//        //next - copying the sorted list in Linked HashMap
//        HashMap<Integer, String> sortedHashMap = new LinkedHashMap<>();
//        for (Object aList : list) {
//            Map.Entry<Integer, String> sortEntry = (Map.Entry)  aList;
//            sortedHashMap.put(sortEntry.getKey(), sortEntry.getValue());
//        }
//        System.out.println("Sorted by value=> " + sortedHashMap.toString());

        // sorted by Value using stream API (new stile)
        System.out.println("Sorted by value with key >5 => ");
        hMap.entrySet()
                //Returns a sequential Stream with this collection as its source
                .stream()
                //Sorted according to the provided Comparator (in interface)
                .sorted(Map.Entry.comparingByValue())
                // some filter
                .filter(e -> e.getKey() > 5)
                //Performs an action for each element of this stream
                .forEach(System.out::println);
    }


    @Test (description = "compare two HashMap by value")
    public boolean test222(){
        Map<String, String> asiaCapital1 = new HashMap<String, String>();
        asiaCapital1.put("Japan", "Tokyo");
        asiaCapital1.put("South Korea", "Seoul");

        Map<String, String> asiaCapital2 = new HashMap<String, String>();
        asiaCapital2.put("South Korea", "Seoul");
        asiaCapital2.put("Japan", "Tokyo");
        asiaCapital2.put("India", "Delhi");

        if( asiaCapital1.size() == asiaCapital2.size()){
            for(Map.Entry<String, String> me : asiaCapital1.entrySet()){
                String key = me.getKey();
                String value = me.getValue();
                String value2 = asiaCapital2.get(key);
                if(!value.equals(value2)) {
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

}



