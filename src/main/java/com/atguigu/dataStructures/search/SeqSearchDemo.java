package com.atguigu.dataStructures.search;

public class SeqSearchDemo {

    public static void main(String[] args) {


        int[] arr = {3,2,542,748,14,214};

        int i = seqSearch(arr, 542);
        System.out.println(i);

    }

    //线性查找算法
    public static int seqSearch(int[] arr,int value){

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }

        return -1;

    }

}
