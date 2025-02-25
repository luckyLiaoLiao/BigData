package com.lxr;

import java.util.TreeMap;

public class MinManhadunDistance {

    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> tm1 = new TreeMap<>();
        TreeMap<Integer, Integer> tm2 = new TreeMap<>();
        for (int[] p : points) {
            int x = p[0], y = p[1];
//            merge(key,value,remappingfunction),当map中不存在指定的key时，便将传入的value设置为key的值，相当于map.put(key, value)；
//            当key存在值时，执行一个方法，该方法接收key的旧值和传入的value，执行自定义的方法返回最终结果设置为key的值（value）
//            返回的是与给定键关联的旧值，如果键不存在，则返回null

            // 坐标之和为x+y的点的个数的计数
            tm1.merge(x + y, 1, Integer::sum);
            // 坐标之差为x-y的点的个数的计数
            tm2.merge(x - y, 1, Integer::sum);
        }
        int ans = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0], y = p[1];
//            坐标之和为x+y的点的个数-1后，如果=0，说明去掉这个点会对整体的距离有影响,所以尝试remove它，重新计算下整体距离
//            注意：无论if条件是否成立，它这个操作（即value-1）都做完了，需必须要在后面加回来
            if (tm1.merge(x + y, -1, Integer::sum) == 0) {
                tm1.remove(x + y);
            }

//            注意x+y之和的个数-1 后=0时，x-y的个数-1之后不一定=0，但是它一定需要-1，即无论这个if条件是否成立，-1的操作都做完了
//            如(1,1)(2,2)(3,3)这三个点在tm1中，是key2:value1,key4:value1,key6:value1,在tm2中是key0:value3，
//            在tm1中-1后变为0，在tm2中并为变为0
            if (tm2.merge(x - y, -1, Integer::sum) == 0) {
                tm2.remove(x - y);
            }
//            lastKey()返回TreeMap中存在的最后一个键‌。因为TreeMap是有序的，所以这个方法返回的是TreeMap中键值对中键的最大值‌
            ans = Math.min(
                    ans, Math.max(tm1.lastKey() - tm1.firstKey(), tm2.lastKey() - tm2.firstKey()));
//            曼哈顿距离可以化简为见img.png，即要么是x1+y1与x2+y2的差值的绝对值的最大值，要么是x1-y1与x2-y2的差值的绝对值的最大值
//            就是上面的这个写法，因为TreeMap必定有序

//            上面两个if里面的merge操作，尝试把点的个数-1了，要想判断下一个点，就要先把这个点加回来
            tm1.merge(x + y, 1, Integer::sum);
            tm2.merge(x - y, 1, Integer::sum);
        }
        return ans;
    }
}
