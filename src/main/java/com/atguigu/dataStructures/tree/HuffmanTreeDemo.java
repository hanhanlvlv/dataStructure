package com.atguigu.dataStructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};

        Node node = createHuffmanTree(arr);

        preOrder(node);
    }


    public static void preOrder(Node node){
        if (node != null) {
            node.preOrder();
        }else {
            System.out.println("空的赫夫曼树不能遍历");
        }
    }

    //赫夫曼树
    public static Node createHuffmanTree(int[] arr){

        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){

            Collections.sort(nodes);
            //System.out.println(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            //System.out.println(nodes);
        }
        return nodes.get(0);
    }


}

class Node implements Comparable<Node>{

    int value;

    Node left;

    Node right;

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
