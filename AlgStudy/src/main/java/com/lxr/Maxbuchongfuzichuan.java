package com.lxr;

import java.util.HashMap;
import java.util.Map;

public class Maxbuchongfuzichuan {
    public static void main(String[] args) {

//        String s = "aaaaa";
//        String s = "abc";
//        System.out.println( countMax(s));

        String inputs[] = {"abcabcbb","bbbbb","pwwkew"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i]);
            System.out.println(
                    countMax(inputs[i])
            );

        }
    }

    public static int countMax(String s){
        int len = s.length();
        int start = 0,max = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
//            boolean bool = map.containsKey(c);
//            if (bool){
            if (map.containsKey(c)){
                start = Math.max(map.get(c)+1,start);
            }
            //如果字符已经存在，就更新value，让value变成最后一次出现该字符的位置；如果不存在就插入
            map.put(c,i);
            max = Math.max(max,i-start+1);
            System.out.print("start:" + start);
            System.out.print("，max:" + max);
            System.out.println();
        }
        return max;

//        算法思想：相当于把当前字符之前的字符串当成是已知字符串，每次都往前找离它最近的跟它是同一字符的索引。
//        更新start后，那么start到i-1之间的必然都是不重复的
//        由于每次都会把map里面的字符索引更新为最后一个，所以就是一个最长不重复子串
//        如果找到了某一个重复子串，就是上面找到的，那么任何一个以它为子串的子串，都不可能是不包含重复字符的子串，
//        所以start必须往前移动，即找到的字符之前的所有字符都不可能是最长子串里面的了。
//        如果在map中没找到，那么start就还是start，即没确定一个start，就是所有可能得子串的最左边那个字符了，
//        不可能再往左，即不可能再比它长。


    }

}

/*
https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
* 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
子串
 的长度。

示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

提示：
0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
* */