package com.atguigu.dataStructures;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSortDemo {

    public static void main(String[] args) {

//        int[] arr = {101,34,52,547,-8,58,-3};
//
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        insertSort(arr);

        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));

    }

    public static void insertSort(int[] arr){

        int nextNum = 0;
        int frontIndex = 0;
        for (int i = 1; i < arr.length; i++) {

            nextNum = arr[i];
            frontIndex = i - 1;

            while (frontIndex >= 0 && nextNum < arr[frontIndex]){
                arr[frontIndex + 1] = arr[frontIndex];
                frontIndex--;
            }

            arr[frontIndex + 1] = nextNum;

        }

    }

}
