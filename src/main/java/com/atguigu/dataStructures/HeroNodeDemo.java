package com.atguigu.dataStructures;


import java.util.Stack;

public class HeroNodeDemo {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        System.out.println("原始数据1：");
        singleLinkedList1.addOrder(new HeroNode(1,"吴用","智多星"));
        singleLinkedList1.addOrder(new HeroNode(3,"宋江","及时雨"));
        singleLinkedList1.addOrder(new HeroNode(5,"林冲","豹子头"));
        singleLinkedList1.addOrder(new HeroNode(7,"卢俊义","玉麒麟"));
        singleLinkedList1.printNode();

        System.out.println("原始数据2：");
        singleLinkedList2.addOrder(new HeroNode(2,"吴用","智多星"));
        singleLinkedList2.addOrder(new HeroNode(4,"宋江","及时雨"));
        singleLinkedList2.addOrder(new HeroNode(6,"林冲","豹子头"));
        singleLinkedList2.addOrder(new HeroNode(8,"卢俊义","玉麒麟"));
        singleLinkedList2.printNode();

        System.out.println("合并原始数据：");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.mergeTwoNode(singleLinkedList1.getNode(), singleLinkedList2.getNode());
        singleLinkedList.printNode();

        /*System.out.println("反转后的数据：");
        SingleLinkedList.reverseNode(singleLinkedList.getNode());

        singleLinkedList.printNode();

        System.out.println("逆序打印的数据：");
        SingleLinkedList.reversePrint(singleLinkedList.getNode());*/

        //singleLinkedList.printNode();

        /*System.out.println("修改后的数据：");
        singleLinkedList.updateNode(new HeroNode(2,"晁盖","晁天王"));

        singleLinkedList.printNode();

        System.out.println("删除后的数据：");
        singleLinkedList.del(3);
//        singleLinkedList.del(2);
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);

        singleLinkedList.printNode();

        System.out.println("链表中有效节点数量：" + SingleLinkedList.getLength(singleLinkedList.getNode()));

        System.out.println("链表中的第K个节点" + SingleLinkedList.getReciprocal(singleLinkedList.getNode(),1));*/

    }

}


class SingleLinkedList{

    private HeroNode node = new HeroNode(0,"",""); //链表的头部不能动，不存放具体数据

    public HeroNode getNode() {
        return node;
    }

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

    //删除链表中的数据
    public void del(int no){

        HeroNode temp = node;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("链表为空");
        }

    }

    //判断链表中的有效节点数量
    public static int getLength(HeroNode heroNode){

        if (heroNode.next == null){
            System.out.println("链表中没有数据");
            return 0;
        }
        int length = 0; //统计链表中的有效节点
        HeroNode temp = heroNode.next;
        while (temp != null){
            temp = temp.next;
            length++;
        }
        return length;

    }

    //查找链表中倒数第K个节点
    public static HeroNode getReciprocal(HeroNode heroNode,int index){

        if (heroNode.next == null){
            System.out.println("链表中没有数据");
            return null;
        }

        int size = getLength(heroNode);  //统计链表的有效个数

        if (index <=0 || index > size){
            System.out.println("链表越界");
            return null;
        }

        HeroNode temp = heroNode.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //反转链表
    public static void reverseNode(HeroNode heroNode){

        if (heroNode.next == null || heroNode.next.next == null){
            return;
        }

        HeroNode temp = heroNode.next;
        HeroNode cur = null;  //用来记录当前节点的下个节点
        HeroNode reverseNode = new HeroNode(0,"","");

        while (temp != null){
            cur = temp.next;
            temp.next = reverseNode.next;
            reverseNode.next = temp;
            temp = cur;
        }
        heroNode.next = reverseNode.next;

    }


    //打印反转链表
    public static void reversePrint(HeroNode heroNode){

        if (heroNode.next == null){
            return;
        }

        Stack<HeroNode> stack = new Stack<>(); //创建一个栈，栈的特点就是先进后出

        HeroNode temp = heroNode.next;
        while (temp != null){

            stack.push(temp);
            temp = temp.next;

        }
        while (stack.size() > 0){   //栈中的值被取出时，栈的长度就会变小
            System.out.println(stack.pop());
        }

    }

    //1 3 5 7
    //2 4 6 8
    public HeroNode mergeTwoNode(HeroNode heroNodeOne,HeroNode heroNodeTwo){

        if (heroNodeOne.next == null || heroNodeTwo.next == null){
            return null;
        }

        HeroNode heroNode1 = heroNodeOne.next;
        HeroNode heroNode2 = heroNodeTwo.next;
        HeroNode cur = null;

        while (heroNode1 != null){
            cur = heroNode1.next;
            addOrder(heroNode1);
            heroNode1 = cur;

        }

        while (heroNode2 != null){
            cur = heroNode2.next;
            addOrder(heroNode2);
            heroNode2 = cur;

        }

        /*boolean flag1 = false;  //此方法有问题，问题在于break到c标记为的时候，node1不会重新循环
        boolean flag2 = false;

        while (node2 != null){
            if (node2.next == null){
                break;
            }
            c:while (node1 != null){
                if (node1.next == null){
                    break c;
                }
                if (node1.next.no > node2.no){
                    flag1 = true;
                    break c;
                }
                if (node1.no > node2.no){
                    flag2 = true;
                    break c;
                }
                node1 = node1.next;
            }
            if (flag1){
                node2.next = node1.next;
                node1.next = node2;
            }else if (flag2){
                node2.next = node1;
            }
            node2 = node2.next;
        }*/

        return node;
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