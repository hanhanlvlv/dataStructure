package com.atguigu.dataStructures.algorithm;

public class HanoitowerDemo {

    public static void main(String[] args) {

        hanoitower(5,'A','B','C');

    }

    //分治算法完成汉诺塔游戏
    public static void hanoitower(int num,char a,char b, char c){

        if (num == 1){
            System.out.println("第1个盘从" + a + "->" + c);
        } else {

            hanoitower(num - 1,a,c,b);

            System.out.println("第" + num + "个盘从" + a + "->" + c);

            hanoitower(num - 1,b,a,c);
        }

    }

}
