package com.atguigu.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSortDemo {

    public static void main(String[] args) {

//        int[] arr = {4,6,8,5,9,58,-8,-98,36,-87};

//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        heapSort(arr);

        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));
        //System.out.println(Arrays.toString(arr));

    }

    //堆排序
    public static void heapSort(int[] arr){

        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }

        for (int j = arr.length - 1;j > 0;j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }

    }

    public static void adjustHeap(int[] arr,int i,int length){

        int temp = arr[i];

        for (int k = i * 2 + 1;k < length; k = k * 2 + 1){
            if (k + 1 < length && arr[k] < arr[k + 1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }

        arr[i] = temp;

    }


}
