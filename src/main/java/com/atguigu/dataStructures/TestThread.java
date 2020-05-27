package com.atguigu.dataStructures;

import java.util.concurrent.TimeUnit;

public class TestThread {

    public static void main(String[] args) {

        TestSynchronized testSynchronized = new TestSynchronized();
        TestSynchronized testSynchronized01 = new TestSynchronized();

        new Thread(() -> {
            testSynchronized.printOne();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            testSynchronized01.printTwo();
            //testSynchronized.printThree();
        }).start();


    }

}


class TestSynchronized{


    public static synchronized void printOne(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试synchronized001");
    }

    public synchronized void printTwo(){
        System.out.println("测试synchronized002");
    }

    public void printThree(){
        System.out.println("测试synchronized003");
    }

}
