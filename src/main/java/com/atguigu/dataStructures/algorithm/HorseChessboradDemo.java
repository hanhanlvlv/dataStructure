package com.atguigu.dataStructures.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorseChessboradDemo {

    private static int X;

    private static int Y;

    private static boolean visited[];

    private static boolean finished;

    //马踏棋盘算法（也叫骑士周游算法）
    public static void main(String[] args) {

        X = 6;
        Y = 6;
        visited = new boolean[X * Y];
        int row = 2;
        int column = 1;

        int[][] chessboard = new int[X][Y];

        long start = System.currentTimeMillis();
        traversalChessborad(chessboard,row - 1,column - 1,1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        for (int[] link : chessboard) {
            System.out.println(Arrays.toString(link));
        }

    }

    public static void traversalChessborad(int[][] chessboard,int row,int column,int step){

        chessboard[row][column] = step;

        visited[row * X + column] = true;

        List<Point> ps = next(new Point(column, row));

        sort(ps);

        while (!ps.isEmpty()){

            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]){
                traversalChessborad(chessboard,p.y,p.x,step + 1);
            }

        }

        if (step < X * Y && !finished){
            visited[row * X + column] = false;
            chessboard[row][column] = 0;
        } else {
            finished = true;
        }

    }

    public static List<Point> next(Point curPoint){

        List<Point> ps = new ArrayList<>();

        Point p1 = new Point();

        if ((p1.x = curPoint.x - 2 ) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1 ) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1 ) < X && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2 ) < X && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2 ) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1 ) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1 ) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 2 ) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }

        return ps;
    }

    public static void sort(List<Point> ps){

        ps.sort((o1, o2) -> {
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            if (count1 < count2){
                return -1;
            } else if (count1 == count2){
                return 0;
            } else {
                return 1;
            }
        });

    }
}
