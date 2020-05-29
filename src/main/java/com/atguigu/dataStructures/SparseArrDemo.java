package com.atguigu.dataStructures;

public class SparseArrDemo {

    public static void main(String[] args) {

        System.out.println("二维数组");
        int cherArr[][] = new int[11][11];

        cherArr[1][2] = 1;
        cherArr[2][3] = 2;
        cherArr[3][4] = 2;

        int sum = 0;
        for (int[] ints : cherArr) {
            for (int anInt : ints) {
                System.out.printf("\t" + anInt);
                if (anInt != 0){
                    sum++;
                }
            }
            System.out.println();
        }
        System.out.println(sum);

        System.out.println("而数组转为稀疏数组");
        int sparseArr[][] = new int[sum + 1][3];

        sparseArr[0][0] = cherArr.length;
        sparseArr[0][1] = cherArr.length;
        sparseArr[0][2] = sum;

        int count = 0;
        for (int i = 0; i < cherArr.length; i++) {
            for (int j = 0; j < cherArr.length; j++) {
                if (cherArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = cherArr[i][j];
                }
            }
        }

        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("\t" + anInt);
            }
            System.out.println();
        }

        System.out.println("稀疏数组再转回二维数组");
        int cherArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            cherArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] ints : cherArr2) {
            for (int anInt : ints) {
                System.out.printf("\t" + anInt);
            }
            System.out.println();
        }

    }

}
