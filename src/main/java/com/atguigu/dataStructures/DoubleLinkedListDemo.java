package com.atguigu.dataStructures;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.addOrder(new HeroNode2(5,"林冲","豹子头"));
        doubleLinkedList.addOrder(new HeroNode2(1,"吴用","智多星"));
        doubleLinkedList.addOrder(new HeroNode2(3,"宋江","及时雨"));
        doubleLinkedList.addOrder(new HeroNode2(7,"卢俊义","玉麒麟"));

        System.out.println("原始数据：");
        doubleLinkedList.printNode();

//        doubleLinkedList.updateNode(new HeroNode2(5,"公孙胜","入云龙"));
//        System.out.println("修改后的数据：");
//        doubleLinkedList.printNode();
//
//        doubleLinkedList.del(5);
//        System.out.println("修改后的数据：");
//        doubleLinkedList.printNode();

    }

}

class DoubleLinkedList{

    private HeroNode2 node = new HeroNode2(0,"",""); //链表的头部不能动，不存放具体数据

    public HeroNode2 getNode() {
        return node;
    }

    //不考虑顺序的情况下添加数据
    public void add(HeroNode2 heroNode){

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = node;
        while (true){
            if (temp.next == null){  //如果为null，说明链表没有数据
                break;
            }
            temp = temp.next;  //如果不为null，说明链表中还有数据，继续往下找，找到最后一个节点
        }
        temp.next = heroNode; //当退出while循环时，temp就指向了链表的最后，将最后这个节点的next指向新的节点
        heroNode.pre = temp;

    }

    //按照顺序往链表中插入数据
    public void addOrder(HeroNode2 heroNode){

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = node;
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
            heroNode.pre = temp;
            if (temp.next != null){
                temp.next.pre = heroNode;
            }
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改链表中的数据
    public void updateNode(HeroNode2 heroNode){

        if (node.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }

        HeroNode2 temp = node.next;
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

    //删除链表中的数据
    public void del(int no){

        if (node.next == null){
            return;
        }

        HeroNode2 temp = node.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("链表为空");
        }

    }

    //打印链表中的数据
    public void printNode(){

        if (node.next == null){
            System.out.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = node.next;  //temp指向的是有节点的下一个节点
        while (true){
            if (temp == null){  //判断下个节点是否为null，为null说明链表中没有数据
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

}



class HeroNode2{

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\''  +
                '}';
    }

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

}