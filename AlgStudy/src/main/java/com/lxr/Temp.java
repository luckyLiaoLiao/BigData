package com.lxr;

import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static void main(String[] args) {

        String inputs[] = {"abcabcbb","bbbbb","pwwkew","qwertyu"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i]);
            System.out.println(
                    countMax(inputs[i])
            );

        }
    }

    public static int countMax(String s){
        int start = 0;
        int max = 1;
        HashMap<Character,Integer> hm = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c= s.charAt(i);
            if (hm.containsKey(c)){
                start = Math.max(start,hm.get(c)+1);
            }

            hm.put(c,i);
            max = Math.max(max,i-start+1);
        }


        return max;

    }


}
