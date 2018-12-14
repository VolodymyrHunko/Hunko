package collectionsTesting;//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
import java.util.*;

public class mapExamplees {
    public static void main(String args[]) {

        // This is how to declare HashMap
        Map<Integer, String> hMap = new HashMap<>();
        //Adding elements to HashMap
        hMap.put(12, "USA");
        hMap.put(2, "India");
        hMap.put(7, "Not Russia");
        hMap.put(49, "Canada");
        hMap.put(3, "Ukraine");
        hMap.put(7, "Russia"); //put the new value in the same key (replace it);
        hMap.put(9, "USA"); //put the double value for diff key

        // Display content using Iterator
        Set<Map.Entry<Integer, String>> setOfEntries = hMap.entrySet(); //get the Set list of all entries
        System.out.println("Set of all entries=> " + setOfEntries);
        Iterator<Map.Entry<Integer, String>> iterator = setOfEntries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> mEntry = iterator.next(); //Entry as class of Map, or Entry <K,V>
            System.out.println("key is: " + mEntry.getKey() + " & Value is: " + mEntry.getValue());
        }
        //we can use for each construction too...
        for (Map.Entry<Integer, String> me : hMap.entrySet()) {
            System.out.println("Key: " + me.getKey() + " & Value: " + me.getValue());
        }

        // Get values based on key
        String var = hMap.get(9);
        System.out.println("Value at index 2 is: " + var);

        // Get the all keys based on value
        Set<Integer> keys = new HashSet<>();
        for (Map.Entry<Integer, String> entry : hMap.entrySet()) {
            if (Objects.equals("USA", entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        System.out.println("USA value has a keys=> " + keys.toString());

        // Remove values based on key
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

        //get all values of map
        System.out.println("All values=> " + hMap.values());
        System.out.println("All keys=> " + hMap.keySet());

        //sort Map by key
        Map<Integer, String> sortMap = new TreeMap<>(hMap); //convert hashMap to treeMap
        for (Map.Entry<Integer, String> me2 : sortMap.entrySet()) {
            System.out.println("After sorting => Key: " + me2.getKey() + " & Value: " + me2.getValue());
        }

        //to sort by value, we should use Comparator
        //first convert entryset to List
        List list = new ArrayList(hMap.entrySet());
        System.out.println(list.toString());
        Collections.sort(list, new Comparator() {//create Comparator inside sort()
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        //next - copying the sorted list in Linked HashMap
        HashMap sortedHashMap = new LinkedHashMap();
        for (Object aList : list) {
            Map.Entry sortEntry = (Map.Entry) aList;
            sortedHashMap.put(sortEntry.getKey(), sortEntry.getValue());
        }
        System.out.println("Sorted by value=> "+sortedHashMap.toString());
    }

}



