package com.atguigu.dataStructures.algorithm;

import java.util.Arrays;

public class PrimAlgorithmDemo {

    //普利姆算法
    public static void main(String[] args) {

        char[] data = new char[]{'A','B','C','D','E','F','G'};

        int verxs = data.length;

        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,1);
    }

}


class MinTree{

    public void createGraph(MGraph mGraph,int verxs,char[] data,int[][] weight){

        int i,j;

        for (i = 0; i < verxs; i++) {
            mGraph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }

    }


    public void showGraph(MGraph mGraph){

        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }

    }


    public void prim(MGraph mGraph,int v){

        int[] visited = new int[mGraph.verxs];

        visited[v] = 1;

        int h1 = -1,h2 = -1,minWeight = 10000;

        for (int k = 1; k < mGraph.verxs; k++) {

            for (int i = 0; i < mGraph.verxs; i++) {
                for (int j = 0; j < mGraph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight){
                        minWeight = mGraph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("边<" + mGraph.data[h1] + "," + mGraph.data[h2] + ">权值:" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;

        }

    }


}


class MGraph{

    //表示图的节点个数
    int verxs;

    //存放结点数据
    char[] data;

    //存放边，就是我们的邻接矩阵
    int[][] weight;

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}