package com.lxr;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int boss[] = new int[65535];

        int bossId = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int firstId = in.nextInt();
            int secondId = in.nextInt();
            int salary = in.nextInt();
            // boss[firstId] += salary;

            // boss[bossId] += salary / 100;

            if (secondId == 0) {
                boss[0] += salary / 100 * 15;
            } else {
                boss[secondId] += salary / 100 * 15;
                // System.out.println(secondId);
            }
        }


        int temp = 0;
        for (int i = 1; i < boss.length; ++i) {
            // boss[0] += boss[i] / 100 * 15;
            temp += boss[i] / 100 * 15;
        }

        // System.out.println(bossId + " " + boss[0]);
        System.out.println(bossId + " " + temp);

    }
}
