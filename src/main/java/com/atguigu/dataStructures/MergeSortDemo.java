package com.atguigu.dataStructures;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSortDemo {

    public static void main(String[] args) {

//        int arr[] = {8,4,5,7,1,3,6,2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length - 1,temp);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        long start = System.currentTimeMillis();

        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);

        long end = System.currentTimeMillis();

        Date date2 = new Date();
        long time = end - start;
        System.out.println(dateFormat.format(date2));
        System.out.println(time);
//        System.out.println(Arrays.toString(arr));

    }


    //归并排序算法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){

        if (left < right){
            int mid = (left + right) / 2;

            mergeSort(arr,left,mid,temp);

            mergeSort(arr,mid + 1,right,temp);

            merge(arr,left,mid,right,temp);
        }

    }


    public static void merge(int[] arr,int left,int mid,int right,int[] temp){

        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        while (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }


    }


}
