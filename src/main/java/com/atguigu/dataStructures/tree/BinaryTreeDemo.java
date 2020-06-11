package com.atguigu.dataStructures.tree;

public class BinaryTreeDemo {

    //二叉树查询删除
    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        HerosNode node1 = new HerosNode(1,"宋江");
        HerosNode node2 = new HerosNode(2,"吴用");
        HerosNode node3 = new HerosNode(3,"卢俊义");
        HerosNode node4 = new HerosNode(4,"林冲");
        HerosNode node5 = new HerosNode(5,"关胜");

        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//        int id = 5;

//        HerosNode resNode = binaryTree.preOrderSearch(id);
//        if (resNode != null){
//            System.out.println("resNode=" + resNode);
//        }else {
//            System.out.println("没有找到" + id);
//        }

//        HerosNode resNode = binaryTree.infixOrderSearch(id);
//        if (resNode != null){
//            System.out.println("resNode=" + resNode);
//        }else {
//            System.out.println("没有找到" + id);
//        }

//        HerosNode resNode = binaryTree.postOrderSearch(id);
//        if (resNode != null){
//            System.out.println("resNode=" + resNode);
//        }else {
//            System.out.println("没有找到" + id);
//        }

        System.out.println("删除前的数据");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后的数据");
        binaryTree.preOrder();

    }

}


class BinaryTree{

    private HerosNode root;

    public void setRoot(HerosNode root) {
        this.root = root;
    }

    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树没有数据");
        }
    }

    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树没有数据");
        }
    }

    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树没有数据");
        }
    }


    public HerosNode preOrderSearch(int id){
        if (this.root != null){
            return this.root.preOrderSearch(id);
        }else {
            return null;
        }
    }

    public HerosNode infixOrderSearch(int id){
        if (this.root != null){
            return this.root.infixOrderSearch(id);
        }else {
            return null;
        }
    }

    public HerosNode postOrderSearch(int id){
        if (this.root != null){
            return this.root.postOrderSearch(id);
        }else {
            return null;
        }
    }


    public void delNode(int id){

        if (root != null){
            if (root.getId() == id){
                root = null;
            }else {
                root.delNode(id);
            }
        }else {
            System.out.println("空树不能删除");
        }

    }


}


class HerosNode{

    private int id;

    private String name;

    private HerosNode left;

    private HerosNode right;

    public HerosNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HerosNode{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HerosNode getLeft() {
        return left;
    }

    public void setLeft(HerosNode left) {
        this.left = left;
    }

    public HerosNode getRight() {
        return right;
    }

    public void setRight(HerosNode right) {
        this.right = right;
    }


    public void preOrder(){
        System.out.println(this);

        if (this.left != null){
            this.left.preOrder();
        }

        if (this.right != null){
            this.right.preOrder();
        }

    }

    public void infixOrder(){

        if (this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null){
            this.right.infixOrder();
        }

    }

    public void postOrder(){

        if (this.left != null){
            this.left.postOrder();
        }

        if (this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);

    }


    public HerosNode preOrderSearch(int id){
        System.out.println("前序查询");

        if (this.id == id){
            return this;
        }

        HerosNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(id);
        }

        if (resNode != null){
            return resNode;
        }

        if (this.right != null){
            resNode = this.right.preOrderSearch(id);
        }

        return resNode;

    }


    public HerosNode infixOrderSearch(int id){

        HerosNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(id);
        }

        if (resNode != null){
            return resNode;
        }

        System.out.println("中序查询");
        if (this.id == id){
            return this;
        }

        if (this.right != null){
            resNode = this.right.infixOrderSearch(id);
        }

        return resNode;

    }


    public HerosNode postOrderSearch(int id){

        HerosNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(id);
        }

        if (resNode != null){
            return resNode;
        }

        if (this.right != null){
            resNode = this.right.postOrderSearch(id);
        }

        if (resNode != null){
            return resNode;
        }

        System.out.println("后序查询");
        if (this.id == id){
            return this;
        }

        return resNode;

    }


    public void delNode(int id){

        if (this.left != null && this.left.id == id){
            this.left = null;
            return;
        }

        if (this.right != null && this.right.id == id){
            if (this.right.left != null){
                if (this.right.right != null) {
                    this.right.left.setRight(this.right.right);
                }
                this.setRight(this.right.left);
                return;
            }
            if (this.right.right != null){
                this.setRight(this.right.right);
                return;
            }
            this.right= null;
            return;
        }

        if (this.left != null){
            this.left.delNode(id);
        }

        if (this.right != null){
            this.right.delNode(id);
        }

    }


}
