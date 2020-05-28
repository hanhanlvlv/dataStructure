package com.atguigu.dataStructures;

import java.util.Scanner;

public class ArrQueueDemo {

    public static void main(String[] args) {

        //ArrQueue arrQueue = new ArrQueue(3);   //普通的数组队列
        CircleArray arrQueue = new CircleArray(3);  //环形的数组队列

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){

            switch (scanner.next()){
                case "a":
                    try {
                        System.out.println("请输入数据");
                        int value = scanner.nextInt();
                        arrQueue.add(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        Object o = arrQueue.get();
                        System.out.println("获取到的数据为" + o);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "h":
                    try {
                        Object o = arrQueue.HeadFront();
                        System.out.println("获取到的头部数据为" + o);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    try {
                        arrQueue.showQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
            }

        }

    }


}

class ArrQueue{

    private int maxSize; //数组的最大长度
    private int front; //队列头
    private int rear; //队列尾
    private Object arr[];

    public ArrQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isEmply(){   //判断队列是否为空
        return rear == front;
    }

    public boolean isFull(){   //判断队列是否已满
        return rear == maxSize - 1;
    }

    public void add(Object n){    //往队列里面插入数据
        if (isFull()){
            throw new RuntimeException("队列已满");
        }
        rear++;
        arr[rear] = n;
    }

    public Object get(){  //获得队列中的数据
        if (isEmply()){
            throw new RuntimeException("队列为空");
        }
        front++;
        Object o = arr[front];
        arr[front] = null;
        return o;
    }

    public void showQueue(){  //显示队列里的数据
        for (int i = 0; i < maxSize; i++) {
            System.out.println("队列数据为:\t" + arr[i]);
        }
    }

    public Object HeadFront(){  //获取队列里面的头部数据
        if (isEmply()){
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }

}

class CircleArray{

    private int maxSize; //数组的最大长度
    private int front; //指向队列头的第一个元素
    private int rear; //指向队列尾最后一个元素的后一个位置，因为希望空出一个空间做约定
    private Object arr[];

    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        arr = new Object[maxSize];
    }

    public boolean isEmply(){   //判断队列是否为空
        return rear == front;
    }

    public boolean isFull(){   //判断队列是否已满
        return (rear + 1) % maxSize == front;
    }

    public void add(Object n){    //往队列里面插入数据
        if (isFull()){
            throw new RuntimeException("队列已满");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public Object get(){  //获得队列中的数据
        if (isEmply()){
            throw new RuntimeException("队列为空");
        }
        Object o = arr[front];
        front = (front + 1) % maxSize;
        return o;
    }

    public void showQueue(){  //显示队列里的数据
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n" ,i % maxSize, arr[i % maxSize]);
        }
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public Object HeadFront(){  //获取队列里面的头部数据
        if (isEmply()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

}
