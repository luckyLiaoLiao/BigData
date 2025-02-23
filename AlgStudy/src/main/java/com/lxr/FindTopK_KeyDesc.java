//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
//        示例 1:
//        输入: nums = [1,1,1,2,2,3], k = 2
//        输出: [1,2]
//
//        示例 2:
//        输入: nums = [1], k = 1
//        输出: [1]
//
//
//        提示：
//        1 <= nums.length <= 105
//        k 的取值范围是 [1, 数组中不相同的元素的个数]
//        题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
//public int[] topk(int nums[], int k){
//        }

import java.util.Comparator;
import java.util.TreeMap;

public class FindTopK_KeyDesc {
    public static void main(String[] args) {

        int k = 2;
        int arr[] = {3,5,1,2,4,4,2,2,3};
        FindTopK_KeyDesc main = new FindTopK_KeyDesc();
        main.findTopk(k,arr);
    }


    public void findTopk(int k,int [] arr){

//        根据key降序排序
        TreeMap<Integer,Integer> tm = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (tm.containsKey(cur)){
//                tm.put(cur, tm.get(cur)+1);
                tm.merge(cur,1,Integer::sum);
            }else {
                tm.put(cur,1);
            }
        }

        for (int i = 0; i < k; i++) {
            int top = tm.firstKey();
            System.out.println(top);
            tm.remove(tm.firstKey());
        }

    }
}
