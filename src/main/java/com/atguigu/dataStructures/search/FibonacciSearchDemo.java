package com.atguigu.dataStructures.search;

import java.util.Arrays;

public class FibonacciSearchDemo {

    public static int maxSize = 20;

    public static void main(String[] args) {

        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(finbonacciSearch(arr,89));

    }


    public static int[] fibonacci(){
        int[] fibonacci = new int[maxSize];

        fibonacci[0] = 1;
        fibonacci[1] = 1;

        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci;
    }

    //斐波拉契算法（黄金分割法）
    public static int finbonacciSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;
        int f[] = fibonacci();

        while (high > f[k] - 1){
            k++;
        }

        int[] temp = Arrays.copyOf(arr,f[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = temp[high];
        }

        while (low <= high){
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]){
                high = mid - 1;
                k --;
            }else if (key > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else {
                if (mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }

        return -1;
    }


}
