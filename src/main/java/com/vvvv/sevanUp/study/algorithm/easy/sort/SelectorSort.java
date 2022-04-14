package com.vvvv.sevanUp.study.algorithm.easy.sort;

import java.util.Arrays;

public class SelectorSort {

    public static void main(String[] args) {
        int[] a = {5, 4, 2, 1, 8, 6};

        for (int i = 0; i < a.length - 1; i++) {
            int temp = a[i], minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < temp) {
                    minIndex = j;
                }
            }
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
