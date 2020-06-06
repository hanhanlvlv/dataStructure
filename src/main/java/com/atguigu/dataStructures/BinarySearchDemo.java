package com.atguigu.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchDemo {

    public static void main(String[] args) {

        int[] arr = {1,8,10,89,1000,1000,1234,1234};

//        int i = binarySearch(arr, 0, arr.length - 1, 81);
//        System.out.println(i);

        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1234);
        System.out.println(list);

    }

    //二分查找算法
    public static int binarySearch(int[] arr,int left,int right,int findVal){

        if (left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){
            return binarySearch(arr,mid + 1,right,findVal);
        }else if(findVal < midVal){
            return binarySearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }

    }


    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){

        if (left > right){
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){
            return binarySearch2(arr,mid + 1,right,findVal);
        }else if(findVal < midVal){
            return binarySearch2(arr,left,mid - 1,findVal);
        }else {
            List<Integer> list = new ArrayList<>();

            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp -= 1;
            }

            list.add(mid);

            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp += 1;
            }

            return list;
        }

    }


}
