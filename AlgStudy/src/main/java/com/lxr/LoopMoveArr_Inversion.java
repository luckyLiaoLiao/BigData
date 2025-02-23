package com.lxr;

import java.util.Scanner;

public class LoopMoveArr_On {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println("n=" + n);
        int m = scan.nextInt();
        System.out.println("m=" + m);
        int[] arr= new int[n];
        int[] arrTemp= new int[n];
        for(int i = 0;i<n;++i){
            arr[i] = scan.nextInt();
            arrTemp[i] = arr[i];
            System.out.println("arr-" + i + "=" +arr[i]);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = arrTemp[(n-m+i)%n];
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] +"   ");
        }
    }
}
