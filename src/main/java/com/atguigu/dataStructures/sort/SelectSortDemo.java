package com.atguigu.dataStructures.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSortDemo {

    //选择排序
    public static void main(String[] args) {

//        int[] arr = {39,5,98,-8,45};
//
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        selectSort(arr);

        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));

    }


    public static void selectSort(int[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {  //优化
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }


}
