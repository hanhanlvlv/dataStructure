package com.atguigu.dataStructures;

public class MiGongDemo {

    public static void main(String[] args) {

        int[][] map = new int[8][7];

        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1; //回溯的效果
//        map[2][2] = 1;

        System.out.println("开始的地图");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        setWay(map,1,1);

        System.out.println("走到目的地");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

    }


    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                if (setWay(map, i + 1, j)){
                    return true;
                }else if (setWay(map, i, j + 1)){
                    return true;
                }else if (setWay(map, i - 1, j)){
                    return true;
                }else if (setWay(map,i,j - 1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

}
