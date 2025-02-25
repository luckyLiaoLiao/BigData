package com.lxr;

import java.util.Arrays;

public class MaxEqualFrep {

    // cnt，数组下标是数值，数组内的值为该值出现的次数
    // sum，数组下标为出现次数，数组内的值为出现为该次数的数值有多少个
    static int[] num_cnt = new int[100010], cnt_sum = new int[100010];
    public int maxEqualFreq(int[] nums) {
        Arrays.fill(num_cnt, 0); Arrays.fill(cnt_sum, 0);
        int n = nums.length, max = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            // 值cnt[curNum]出现次数加1
            // 并且判断新出现的这个值的出现次数
            int curNumCnt = ++num_cnt[curNum];
            // 前缀的长度为i+1
            int cur_len = i + 1;
            // 出现次数为cur的个数+1，同时出现次数为cur-1的个数-1，因为一个数不能同时出现cur次和cur-1次
            cnt_sum[curNumCnt]++;
            cnt_sum[curNumCnt - 1]--;

            // 每次都判断出现次数最多的次数，唯一有可能改变过去出现最多次数的就是新判断的这个数
            // 如果新出现的这个数是出现次数比较少的那一挂的，就不会改变max
            max = Math.max(max, curNumCnt);
            if (max == 1) res = cur_len;
            if (max * cnt_sum[max] + 1 == cur_len) res = cur_len;
            if ((max - 1) * cnt_sum[max - 1] + max == cur_len) res = cur_len;
        }
        return res;
    }
}


/*
*https://leetcode.cn/problems/maximum-equal-frequency/
* 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。

示例 1：
输入：nums = [2,2,1,1,5,3,3,5]
输出：7
解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
示例 2：
输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
输出：13

提示：
2 <= nums.length <= 105
1 <= nums[i] <= 105
* */