package com.atguigu.dataStructures;

import java.util.concurrent.ThreadPoolExecutor;

public class HeroNodeDemo {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        System.out.println("原始数据：");
        singleLinkedList.addOrder(new HeroNode(3,"吴用","智多星"));
        singleLinkedList.addOrder(new HeroNode(1,"宋江","及时雨"));
        singleLinkedList.addOrder(new HeroNode(4,"林冲","豹子头"));
        singleLinkedList.addOrder(new HeroNode(2,"卢俊义","玉麒麟"));

        singleLinkedList.printNode();

        System.out.println("修改后的数据：");
        singleLinkedList.updateNode(new HeroNode(2,"晁盖","晁天王"));

        singleLinkedList.printNode();
    }

}


class SingleLinkedList{

    private HeroNode node = new HeroNode(0,"",""); //链表的头部不能动，不存放具体数据

    //不考虑顺序的情况下添加数据
    public void add(HeroNode heroNode){

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = node;
        while (true){
            if (temp.next == null){  //如果为null，说明链表没有数据
                break;
            }
            temp = temp.next;  //如果不为null，说明链表中还有数据，继续往下找，找到最后一个节点
        }
        temp.next = heroNode; //当退出while循环时，temp就指向了链表的最后，将最后这个节点的next指向新的节点

    }

    //按照顺序往链表中插入数据
    public void addOrder(HeroNode heroNode){

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = node;
        boolean flag = false;  //判断链表中是否有数据
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号%d已经存在了,不能加入\n" , heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


    //修改链表中的数据
    public void updateNode(HeroNode heroNode){

        if (node.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }

        HeroNode temp = node.next;
        boolean flag = false;
        while (true){
            if (temp == null){   //如果为null，说明已经遍历完了
                break;
            }
            if (temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else {
            System.out.println("链表中无此数据");
        }

    }


    //打印链表中的数据
    public void printNode(){

        if (node.next == null){
            System.out.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = node.next;  //temp指向的是有节点的下一个节点
        while (true){
            if (temp == null){  //判断下个节点是否为null，为null说明链表中没有数据
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

}


class HeroNode{

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\''  +
                '}';
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

}