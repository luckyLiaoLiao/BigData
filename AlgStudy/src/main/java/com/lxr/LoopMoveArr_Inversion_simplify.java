package com.lxr;

import java.util.Scanner;

public class LoopMoveArr_Inversion {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println("n=" + n);
        int m = scan.nextInt();
        System.out.println("m=" + m);
        int[] nums= new int[n];
        for(int i = 0;i<n;++i){
            nums[i] = scan.nextInt();
            System.out.println("nums-" + i + "=" +nums[i]);
        }

//        注意：这三个反转都必须是二分之反转的长度，否则相当于没有反转，因为前半部分反转完，后半部分又倒回去了
        for (int i = 0; i < (n-m)/2; i++) {
            int temp = nums[i];
            nums[i] = nums[n-m-1-i];
            nums[n-m-1-i] =  temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("nums-" +i + "=" + nums[i] + "   ");
        }
        System.out.println();

        for (int i = 0; i < m/2; i++) {
            int temp = nums[n-m+i];
            nums[n-m+i] = nums[n-1-i];
            nums[n-1-i] =  temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("nums-" +i + "=" + nums[i] + "   ");
        }
        System.out.println();

        for (int i = 0; i < n/2; i++) {
            int temp = nums[i];
            nums[i] = nums[n-1-i];
            nums[n-1-i] =  temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("nums-" +i + "=" + nums[i] + "   ");
        }
        System.out.println();
    }
}
