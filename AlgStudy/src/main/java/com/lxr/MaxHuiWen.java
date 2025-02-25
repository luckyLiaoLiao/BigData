package com.lxr;

public class MaxHuiWen {

    public static void main(String[] args) {
        MaxHuiWen mhw = new MaxHuiWen();

        String inputs[] = {"ababc","abbba","cbc","ababca","abbbab","cb","b",""};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(mhw.findHW(inputs[i]));
        }
    }

    public String findHW(String input){
        int len = input.length();
        int max = 0;
        String result = "";

        if (input.length()<=1){
            return input;
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j <= len; j++) {
//                逐个截取子串，遍历判断是否是回文
                String cur = input.substring(i,j);
//                System.out.println(cur);
                if (doFind(cur)){
                    if (cur.length()>max){
                        max = cur.length();
                        result = cur;
                    }
                }
            }
        }

        return result;
    }

    public boolean doFind(String input){
        int len = input.length();
        if (input==null || "".equals(input)){
            return false;
        }
        for (int i = 0; i <= len/2; i++) {
            if (input.charAt(i)!=input.charAt(len-i-1)){
                return false;
            }
        }

        return true;
    }
}
