package com.lxr;

import java.util.Scanner;

public class LoopMoveArr_On {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println("n=" + n);
        int m = scan.nextInt();
        System.out.println("m=" + m);
        int[] result= new int[n];
        int[] nums= new int[n];
        for(int i = 0;i<n;++i){
            nums[i] = scan.nextInt();
            System.out.println("nums-" + i + "=" +nums[i]);
        }

        for (int i = 0; i < n; i++) {
            result[i] = nums[(n-m+i)%n];
        }

        for (int i = 0; i < n; i++) {
            System.out.print(result[i] +"   ");
        }
    }
}
