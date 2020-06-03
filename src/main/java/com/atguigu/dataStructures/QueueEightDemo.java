package com.atguigu.dataStructures;

public class QueueEightDemo {


    int max = 8;

    int[] array = new int[max];

    static int count = 0;

    static int judgecount = 0;

    public static void main(String[] args) {

        QueueEightDemo queueEightDemo = new QueueEightDemo();

        queueEightDemo.check(0);

        System.out.println(count);

        System.out.println(judgecount);

    }

    public void check(int n){
        if (n == max){
            print();
            count++;
            return;
        }

        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)){
                check(n + 1);
            }
        }
    }


    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
            judgecount++;
        }
        return true;
    }


    public void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }



}
