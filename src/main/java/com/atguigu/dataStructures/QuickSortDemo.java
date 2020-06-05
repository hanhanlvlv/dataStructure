package com.atguigu.dataStructures;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSortDemo {

    public static void main(String[] args) {

//        int[] arr = {-5,89,0,-98,-56,98};
//        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};

//        quickSort(arr,0,arr.length - 1);

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        quickSort(arr,0,arr.length - 1);
        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));

        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (l < r){
            while (arr[l] < pivot){
                l += 1;
            }
            while (arr[r] > pivot){
                r -= 1;
            }

            if (l >= r){
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[r] == pivot){
                l += 1;
            }

            if (arr[l] == pivot){
                r -= 1;
            }

        }

        if (l == r){
            l += 1;
            r -= 1;
        }

        if (left < r){
            quickSort(arr,left,r);
        }

        if (right > l){
            quickSort(arr,l,right);
        }

    }


}
