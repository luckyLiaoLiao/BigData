import java.util.*;

public class FindTopK_ValueDesc {
    public static void main(String[] args) {

        int k = 2;
        int arr[] = {3,5,2,4,4,2,2,3};
        TreeMap<Integer,Integer> tm = new TreeMap<>();

        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (tm.containsKey(cur)){
//                tm.put(cur, tm.get(cur)+1);
                tm.merge(cur,1,Integer::sum);
            }else {
                tm.put(cur,1);
            }
        }

//        将map.entryset()转换为list，然后用Collections的sort方法排序
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(tm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<Integer,Integer> map: list){
            if (k<=0){
                return;
            }
            k--;
            System.out.println(map.getKey() + ":" +map.getValue());
        }
    }
}
