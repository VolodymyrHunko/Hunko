import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class practice {
    public static void main(String[] args){
//        System.out.println(Arrays.toString(args));
        List<String> list = new ArrayList<>();
        list.add("Vova");
        list.add("Lena");
        list.add("Max");
        list.add("Alla");
        System.out.println(list);
        List<String> list2 = new ArrayList<>();
        list2.add("Vova");
        list2.add("Lena");
        list2.add("Max");
        list2.add("Alla");
        System.out.println(list2.equals(list));
        System.out.println(list.hashCode()+"..."+list2.hashCode());
        System.out.println(list.getClass()+"..."+list2.getClass());

//        for(int a=1; a<list.size();a++) {
//            for (int i = 0; i < list.size() - 1; i++) {
//                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
//                    Collections.swap(list, i, (i + 1));
//                    System.out.println(list);
//                }
//
//            }
//        }

//        ImmutableList<String> il = ImmutableList.of("sssss");
//        String a = il.get(0);
//        System.out.println(a);

    }
}
