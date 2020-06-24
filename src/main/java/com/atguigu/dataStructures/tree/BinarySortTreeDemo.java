package com.atguigu.dataStructures.tree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9,2,8,11};

        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Nodes(arr[i]));
        }

        System.out.println("中序遍历");
        binarySortTree.infixOrder();

//        binarySortTree.del(2);
//        binarySortTree.del(5);
//        binarySortTree.del(9);
//        binarySortTree.del(12);
//        binarySortTree.del(1);
//        binarySortTree.del(3);
//        binarySortTree.del(7);
        binarySortTree.del(10);
        System.out.println("删除后的中序遍历");
        binarySortTree.infixOrder();

    }

}


class BinarySortTree{

    private Nodes root;

    public void add(Nodes nodes){

        if (root == null){
            root = nodes;
        }else {
            root.add(nodes);
        }

    }

    public void infixOrder(){

        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("空树不能输出");
        }

    }


    public Nodes search(int value){

        if (root != null){
            return root.search(value);
        } else {
            System.out.println("空树没有要查询的数据");
            return null;
        }

    }


    public Nodes searchParent(int value){

        if (root != null){
            return root.searchParent(value);
        } else {
            System.out.println("空树没有要查询的数据");
            return null;
        }

    }


    public int delRightTreeMin(Nodes nodes){

        Nodes target = nodes;

        while (target.left != null){
            target = target.left;
        }

        del(target.value);

        return target.value;

    }


    public void del(int value){

        if (root == null){
            return;
        } else {

            Nodes targetNode = search(value);

            if (targetNode == null){
                return;
            }

            if (root.left == null && root.right == null){
                root = null;
                return;
            }

            Nodes parent = searchParent(value);

            //这说明删除的是一个叶子结点
            if (targetNode.left == null && targetNode.right == null){

                if (parent.left != null && parent.left.value == value){
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value){
                    parent.right = null;
                }

            //这说明删除的是一个非叶子结点，而且有左结点和右子结点
            } else if (targetNode.left != null && targetNode.right != null){

                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            //这说明删除的是一个只有一个叶子结点的非叶子结点
            } else {

                if (targetNode.left != null){
                    if (parent.left.value == value){
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    if (parent.left.value == value){
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }

            }
        }

    }


}


class Nodes{

    public int value;

    public Nodes left;

    public Nodes right;

    @Override
    public String toString() {
        return "Nodes{" +
                "value=" + value +
                '}';
    }

    public Nodes(int value) {
        this.value = value;
    }

    public void add(Nodes nodes){

        if (nodes == null){
            return;
        }

        if (this.value > nodes.value){
            if (this.left == null){
                this.left = nodes;
            } else {
                this.left.add(nodes);
            }
        } else {
            if (this.right == null){
                this.right = nodes;
            } else {
                this.right.add(nodes);
            }
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


    public Nodes search(int value){

        if (value == this.value){
            return this;
        } else if (value < this.value){

            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }

    }


    public Nodes searchParent(int value){

        //如果当前节点就是要删除的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }

}
