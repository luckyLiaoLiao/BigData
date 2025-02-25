package com.lxr;

public class JieYuShui {

    public static int trap(int[] height) {

        int length = height.length;
        //保存左右俩边的最大值
        int[] max_left = new int[length];
        int[] max_right = new int[length];
        int max_trap = 0 ;

        //找到每根柱子以左的最大值并保存起来
        max_left[0] = height[0];
        for (int i = 1; i < length; i++) {
            max_left[i] = Math.max(max_left[i-1],height[i]);
        }

        //找到每根柱子以右的最大值并保存起来
        max_right[length-1] = height[length-1];
        for (int i = length-2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i+1],height[i]);
        }

        //计算每根柱子的存水容量
        for (int i = 0; i < length; i++) {
            max_trap = Math.min(max_left[i],max_right[i]) - height[i] + max_trap;
        }
        return max_trap ;
    }

    public static void main(String[] args) {
        int [] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        trap(height);
    }

}


/*

https://leetcode.cn/problems/trapping-rain-water/

* 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
示例 1：
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
*
示例 2：
输入：height = [4,2,0,3,2,5]
输出：9

提示：
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*
* */