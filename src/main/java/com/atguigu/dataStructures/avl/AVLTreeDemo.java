package com.atguigu.dataStructures.avl;

public class AVLTreeDemo {

    public static void main(String[] args) {

        int[] arr = {10,11,7,6,8,9};

        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Nodes(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("树的左子树高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度：" + avlTree.getRoot().rightHeight());
        System.out.println("根结点：" + avlTree.getRoot());

    }

}


class AVLTree{

    private Nodes root;

    public Nodes getRoot() {
        return root;
    }

    public void setRoot(Nodes root) {
        this.root = root;
    }

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
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
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

    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }

    public void leftRotate(){

        //创建新的结点，以当前根结点的值
        Nodes newNodes = new Nodes(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNodes.left = left;
        //把新的结点的右子树设置成当前结点的右子树的左子树
        newNodes.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNodes;

    }

    public void rightRotate(){

        Nodes newNodes = new Nodes(value);
        newNodes.right = right;
        newNodes.left = left.right;
        value = left.value;
        left = left.left;
        right = newNodes;

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

        if (rightHeight() - leftHeight() > 1){

            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate();
            } else {
                leftRotate();
            }

            return;

        }

        if (leftHeight() - rightHeight() > 1){

            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
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