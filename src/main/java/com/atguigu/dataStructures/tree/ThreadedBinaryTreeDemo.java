package com.atguigu.dataStructures.tree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");
        HeroNode node7 = new HeroNode(16,"domain");
        HeroNode node8 = new HeroNode(18,"olala");
        HeroNode node9 = new HeroNode(20,"xihaxiha");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
        node4.setRight(node9);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        System.out.println("10号节点的前驱节点是" + node5.getLeft());
        System.out.println("10号节点的后继节点是" + node5.getRight());

        System.out.println("中序遍历线索化二叉树的方法");
        threadedBinaryTree.threadedInFixList();

        System.out.println("前序遍历线索化二叉树的方法");
        threadedBinaryTree.threadedPreList();

        System.out.println("后序遍历线索化二叉树的方法");
        threadedBinaryTree.threadedPostList();

    }

}

//线索二叉树
class ThreadedBinaryTree{

    private HeroNode root;

    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }


    public void threadedPostList(){

        HeroNode node = root;
        pre = null;
        while (node != null){

            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);
            if (pre != null && pre != root){
                System.out.println(pre);
            }

            while (node.getRightType() == 1){
                node = node.getRight();
            }

            pre = node;

            node = node.getRight();

            if (node == null){
                if (pre == root.getRight()){
                    System.out.println(pre);
                }
                System.out.println(root);
            }

        }

    }


    public void threadedPreList(){

        HeroNode node = root;
        while (node != null){

            while (node.getLeftType() == 0){
                System.out.println(node);
                node = node.getLeft();
            }

            while (node.getRightType() == 1){
                System.out.println(node);
                node = node.getRight();
            }

            node = node.getRight();

        }

    }


    public void threadedInFixList(){

        HeroNode node = root;
        while (node != null){

            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);

            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();

        }

    }


    public void threadedNodes(HeroNode node){

        if (node == null) return;

        threadedNodes(node.getLeft());

        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        threadedNodes(node.getRight());

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


    public HeroNode preOrderSearch(int id){
        if (this.root != null){
            return this.root.preOrderSearch(id);
        }else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int id){
        if (this.root != null){
            return this.root.infixOrderSearch(id);
        }else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int id){
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


class HeroNode{

    private int id;

    private String name;

    private HeroNode left;

    private HeroNode right;

    private int leftType;

    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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


    public HeroNode preOrderSearch(int id){
        System.out.println("前序查询");

        if (this.id == id){
            return this;
        }

        HeroNode resNode = null;
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


    public HeroNode infixOrderSearch(int id){

        HeroNode resNode = null;
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


    public HeroNode postOrderSearch(int id){

        HeroNode resNode = null;
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