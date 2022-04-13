package com.vvvv.sevanUp.study.easy.sort;

public class QuickSort {

    // 递归一个要有
    // 1：基线条件
    // 2：递归条件
    private void quick (int[] array,int low,int high){
        int lo = low,hi =high;
        if(lo>=hi){
            return;
        }
        boolean flag = false;
        while(lo<hi){
 /*           if(array.get(lo).getId()<array.get(hi).getId()){
                Menus temp = array.get(lo);
                array.set(lo,array.get(hi));
                menus.set(hi,temp);
                flag=!flag;
            }*/
            if(flag)
                lo++;
            else
                hi--;
        }
        lo--;hi++;
//        quick(low,lo);
//        quick(hi,high);
    }
}
