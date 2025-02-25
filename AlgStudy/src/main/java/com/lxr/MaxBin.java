package com.lxr;

public class MaxBin {

    public String maximumBinaryString(String a) {

//        找出0的个数
        String b = a.replaceAll("0", "");
        int count0 = a.length()-b.length();

        if (count0<=1) {
            return a;
        }

//        把字符串中的0全部换成1
        StringBuffer sb = new StringBuffer(a.replaceAll("0", "1"));

//        找出第一个0的位置
        int firstIndexOf0 = a.indexOf("0");

//        在经过规则中的操作之后，只需要把0全部变成1，然后把“第一个0的位置 + 0的个数 - 1 ”，这个位置的1变成0即可
        sb.setCharAt(firstIndexOf0 + count0-1, '0');

        // sb.replace(firstIndexOf0+count0-1, firstIndexOf0+count0, "0");

        return sb.toString();
    }

}



/*
https://leetcode.cn/problems/maximum-binary-string-after-change/description/
* 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：

操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
比方说， "00010" -> "10010"
操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
比方说， "00010" -> "00001"
请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。



示例 1：

输入：binary = "000110"
输出："111011"
解释：一个可行的转换为：
"000110" -> "000101"
"000101" -> "100101"
"100101" -> "110101"
"110101" -> "110011"
"110011" -> "111011"
示例 2：

输入：binary = "01"
输出："01"
解释："01" 没办法进行任何转换。


提示：

1 <= binary.length <= 105
binary 仅包含 '0' 和 '1' 。
*
* */