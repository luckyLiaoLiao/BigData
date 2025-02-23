package com.lxr;

import java.util.TreeMap;

public class MinDistance {

    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> tm1 = new TreeMap<>();
        TreeMap<Integer, Integer> tm2 = new TreeMap<>();
        for (int[] p : points) {
            int x = p[0], y = p[1];
//            merge(key,value,remappingfunction),当map中不存在指定的key时，便将传入的value设置为key的值，相当于map.put(key, value)；
//            当key存在值时，执行一个方法，该方法接收key的旧值和传入的value，执行自定义的方法返回最终结果设置为key的值
//            返回的是与给定键关联的旧值，如果键不存在，则返回null
            tm1.merge(x + y, 1, Integer::sum);
            tm2.merge(x - y, 1, Integer::sum);
        }
        int ans = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0], y = p[1];
            if (tm1.merge(x + y, -1, Integer::sum) == 0) {
                tm1.remove(x + y);
            }
            if (tm2.merge(x - y, -1, Integer::sum) == 0) {
                tm2.remove(x - y);
            }
//            lastKey()返回TreeMap中存在的最后一个键‌。因为TreeMap是有序的，所以这个方法返回的是TreeMap中键值对中键的最大值‌
            ans = Math.min(
                    ans, Math.max(tm1.lastKey() - tm1.firstKey(), tm2.lastKey() - tm2.firstKey()));
//            曼哈顿距离可以化简为见img.png，即要么是x1+y1与x2+y2的差值的绝对值的最大值，要么是x1-y1与x2-y2的差值的绝对值的最大值
//            就是上面的这个写法，因为TreeMap必定有序
            tm1.merge(x + y, 1, Integer::sum);
            tm2.merge(x - y, 1, Integer::sum);
        }
        return ans;
    }
}
