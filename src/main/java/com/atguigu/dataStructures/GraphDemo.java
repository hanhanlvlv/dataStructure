package com.atguigu.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphDemo {

    private List<String> vertexList;

    private int[][] edges;

    private int numOfEdge;

    public static void main(String[] args) {

        int n = 5;

        GraphDemo graph = new GraphDemo(5);

        String vertexValue[] = {"A","B","C","D","E"};

        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }

        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);

        graph.showGraph();

    }

    public GraphDemo(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdge = 0;
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }

    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public int getNumOfEdge(){
        return numOfEdge;
    }

    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

}
