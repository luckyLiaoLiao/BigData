package com.lxr;

public class MaxHuiWen {

    public static void main(String[] args) {
        MaxHuiWen mhw = new MaxHuiWen();
        String input = "abab";
        System.out.println(mhw.getLongestPalindrome(input));;


    }

    public int fun(String s,int begin,int end){
        while (begin>=0 && end<s.length() && s.charAt(begin)==s.charAt(end)){
            begin--;
            end++;
        }

        return end-begin-1;
    }

    public int getLongestPalindrome(String A){
        int maxlen = 1;
        for(int i = 0;i<A.length()-1;i++){
            maxlen = Math.max(maxlen,Math.max(fun(A,i,i),fun(A,i,i+1)));
        }
        return maxlen;
    }
}
