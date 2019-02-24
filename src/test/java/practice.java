import com.google.common.collect.ImmutableList;

public class practice {
    public static void main(String[] args){
//        System.out.println(Arrays.toString(args));
//        List<String> list = new ArrayList<>();
//        list.add("Vova");
//        list.add("Lena");
//        list.add("Max");
//        list.add("Alla");
//        System.out.println(list);
//        for(int a=1; a<list.size();a++) {
//            for (int i = 0; i < list.size() - 1; i++) {
//                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
//                    Collections.swap(list, i, (i + 1));
//                    System.out.println(list);
//                }
//
//            }
//        }

        ImmutableList<String> il = ImmutableList.of("sssss");
        String a = il.get(0);
        System.out.println(a);

    }
}
