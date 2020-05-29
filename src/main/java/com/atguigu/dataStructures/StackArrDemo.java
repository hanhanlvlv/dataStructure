package com.atguigu.dataStructures;

import java.util.Scanner;

public class StackArrDemo {

    public static void main(String[] args) {

        StackArr stackArr = new StackArr(4);

        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        while (loop){

            key = scanner.next();
            switch (key){
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stackArr.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("取出的值为" + stackArr.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    stackArr.showStack();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }

        }

    }

}


class StackArr{

    private int maxSize;
    private int[] stack;
    private int top;

    public StackArr(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
        top = -1;
    }


    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmply(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop(){
        if (isEmply()){
            throw new RuntimeException("栈为空");
        }

        int value = stack[top];
        top--;
        return value;
    }


    public void showStack(){

        if (isEmply()){
            System.out.println("栈为空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n" ,i, stack[i]);
        }

    }


}

