package com.atguigu.dataStructures;

import javafx.beans.binding.BooleanExpression;

import java.util.Scanner;

public class StackArrDemo {

    public static void main(String[] args) {

//        StackArr stackArr = new StackArr(4);
//
//        Scanner scanner = new Scanner(System.in);
//        String key = "";
//        boolean loop = true;
//        while (loop){
//
//            key = scanner.next();
//            switch (key){
//                case "push":
//                    System.out.println("请输入一个数");
//                    int value = scanner.nextInt();
//                    stackArr.push(value);
//                    break;
//                case "pop":
//                    try {
//                        System.out.println("取出的值为" + stackArr.pop());
//                    }catch (Exception e){
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "show":
//                    stackArr.showStack();
//                    break;
//                case "exit":
//                    scanner.close();
//                    loop = false;
//                    break;
//            }
//
//        }

        StackLinked stackLinked = new StackLinked();

        stackLinked.add(new LinkedStack(1,"宋江"));
        stackLinked.add(new LinkedStack(2,"卢俊义"));
        stackLinked.add(new LinkedStack(3,"晁盖"));
        stackLinked.add(new LinkedStack(4,"吴用"));

        System.out.println("原始数据：");
        stackLinked.showLisi();

        LinkedStack linkedStack1 = stackLinked.get();
        System.out.println("获取第一条数据之后，剩余的数据：" + linkedStack1);
        stackLinked.showLisi();

        LinkedStack linkedStack2 = stackLinked.get();
        System.out.println("再次获取数据之后，剩余的数据：" + linkedStack2);
        stackLinked.showLisi();

        LinkedStack linkedStack3 = stackLinked.get();
        System.out.println("再次获取数据之后，剩余的数据：" + linkedStack3);
        stackLinked.showLisi();

        LinkedStack linkedStack4 = stackLinked.get();
        System.out.println("再次获取数据之后，剩余的数据：" + linkedStack4);
        stackLinked.showLisi();

    }

}


class StackLinked{

    private LinkedStack head = new LinkedStack(0,"");

    public void add(LinkedStack linkedStack){

        if (head.next == null){
            head.next = linkedStack;
        }else {
            linkedStack.next = head.next;
            head.next = linkedStack;
        }

    }


    public LinkedStack get(){

        if (head.next == null){
            System.out.println("链表中没有数据");
            return null;
        }
        LinkedStack next = head.next;
        head.next = head.next.next;

        return next;
    }


    public void showLisi(){

        if (head.next == null){
            System.out.println("链表维欧空");
            return;
        }

        LinkedStack temp = head.next;
        while (temp != null){
            System.out.println("链表中的数据：" + temp);
            temp = temp.next;
        }

    }


}


class LinkedStack{

    public int no;
    public String name;
    public LinkedStack next;

    @Override
    public String toString() {
        return "LinkedStack{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public LinkedStack(int no, String name) {
        this.no = no;
        this.name = name;
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

