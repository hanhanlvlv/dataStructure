package com.atguigu.dataStructures;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSortDemo {


    public static void main(String[] args) {

//        int[] arr = {3,2,542,748,14,214};
//
//        radixSort(arr);

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        radixSort(arr);

        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));

    }

    //基数排序
    public static void radixSort(int[] arr){

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n = 1;i < maxLength;i++,n *= 10){

            for (int j = 0;j < arr.length;j++){
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;

            for (int k = 0;k < bucketElementCounts.length;k++){
                if (bucketElementCounts[k] != 0){
                    for (int l = 0;l < bucketElementCounts[k];l++){
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }

            //System.out.println("第" + (i + 1) + "轮：" + Arrays.toString(arr));

        }

    }


}
