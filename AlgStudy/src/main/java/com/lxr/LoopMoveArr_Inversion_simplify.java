package com.lxr;

import java.util.Scanner;

public class LoopMoveArr_Inversion_simplify {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println("n=" + n);
        int m = scan.nextInt();
        System.out.println("m=" + m);
        int[] nums= new int[n];
        for(int i = 0;i<n;++i){
            nums[i] = scan.nextInt();
//            System.out.println("nums-" + i + "=" +nums[i]);
        }

        rotateRight(nums,m);

        for (int i = 0; i < n; i++) {
            System.out.print("nums-" +i + "=" + nums[i] + "   ");
        }
        System.out.println();

    }

    public static void rotateRight(int[] nums,int k){
        int n = nums.length;
        k = k%n;

        reverse(nums,0,n-1-k);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }

    public static void reverse(int[] nums,int start,int end){
        int len = nums.length;
        while (start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] =temp;
            start++;
            end--;
        }
    }
}
