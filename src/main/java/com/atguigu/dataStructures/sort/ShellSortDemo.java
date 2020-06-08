package com.atguigu.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSortDemo {

    //希尔排序
    public static void main(String[] args) {

//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        shellSort2(arr);
//
//        shellSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

//        shellSort(arr);
        shellSort2(arr);

        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));

    }


    //希尔排序交换法
    public static void shellSort(int[] arr){

        int temp = 0;
        //int count = 0;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++){
                for (int k = j - i; k >= 0; k -= i){
                    if (arr[k] > arr[k + i]){
                        temp = arr[k];
                        arr[k] = arr[k + i];
                        arr[k + i] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
        }

    }

    //希尔排序移动法
    public static void shellSort2(int[] arr){

        int temp = 0;
        int k = 0;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++){

                k = j;
                temp = arr[k];
                if (arr[k] < arr[k - i]){
                    while (k - i >= 0 && temp < arr[k - i]){
                        arr[k] = arr[k - i];
                        k -= i;
                    }
                    arr[k] = temp;
                }

            }
            //System.out.println(Arrays.toString(arr));
        }
    }


}
