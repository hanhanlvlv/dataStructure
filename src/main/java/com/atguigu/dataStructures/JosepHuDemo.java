package com.atguigu.dataStructures;

public class JosepHuDemo {

    public static void main(String[] args) {

        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.add(20);
        circleLinkedList.showBoy();

        circleLinkedList.Reported(10,5,20);

    }

}


class CircleLinkedList{

    public Boy head = null;

    public void add(int nums){

        if (nums < 1){
            System.out.println("填的数据不对");
            return;
        }

        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                head = boy;
                head.setNext(head);
                curBoy = head;
            }else {
                curBoy.setNext(boy);
                boy.setNext(head);
                curBoy = boy;
            }
        }

    }


    public void showBoy(){

        if (head == null){
            System.out.println("节点为空");
            return;
        }

        Boy curBoy = head;
        while (curBoy != null){
            System.out.printf("编号为 %d \n",curBoy.getNo());
            if (curBoy.getNext() == head) break;
            curBoy = curBoy.getNext();
        }

    }


    public void Reported(int startNum,int countNume,int nums){

        if(head == null || startNum < 1 || startNum > nums){
            System.out.println("输入的参数有误");
            return;
        }

        Boy helper = head;
        while (helper != null){
            if (helper.getNext() == head){
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < startNum - 1; i++) {
            head = head.getNext();
            helper = helper.getNext();
        }

        while (true){

            if (helper == head){  //说明只剩下最后个编号了
                break;
            }

            for (int i = 0; i < countNume - 1; i++) {
                head = head.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d出圈 \n",head.getNo());
            head = head.getNext();
            helper.setNext(head);
        }
        System.out.printf("剩最后一个编号 %d \n",head.getNo());


    }


}


class Boy{

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
