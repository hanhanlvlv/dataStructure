package com.atguigu.dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo {

    public static void main(String[] args) {
        //逆波兰表达式
//        String suffixExpression = "3 4 + 5 * 6 -";
//
//        List<String> list = getList(suffixExpression);
//
//        int cal = cal(list);
//        System.out.println("最后的结果为： " + cal);

        //中缀表达式转后缀表达式
        String expression = "(5+5)*10-8+8/2+10";
        List<String> suffixExpression = getArrayList(expression);
        System.out.println("中缀表达式：" + suffixExpression);
        List<String> parseSuffixExpression = parseSuffixExpression(suffixExpression);
        System.out.println("后缀表达式：" + parseSuffixExpression);

        int cal = cal(parseSuffixExpression);
        System.out.println("后缀表达式计算结果：" + cal);

    }


    public static List<String> parseSuffixExpression(List<String> lists){

        Stack<String> stack = new Stack<>();
        List<String> expression = new ArrayList<>();

        for (String list : lists) {

            if (list.matches("\\d+")){
                expression.add(list);
            }else if (list.equals("(")){
                stack.push(list);
            }else if (list.equals(")")){
                while (!stack.peek().equals("(")){
                    expression.add(stack.pop());
                }
                stack.pop();
            }else {
                while (stack.size() != 0 && Operation.getValue(list) <= Operation.getValue(stack.peek())){
                    expression.add(stack.pop());
                }
                stack.push(list);
            }

        }

        while (stack.size() != 0){
            expression.add(stack.pop());
        }

        return expression;
    }


    public static List<String> getArrayList(String expression){
        List<String> list = new ArrayList<>();
        int index = 0;
        String str = "";
        char c = ' ';
        do {

            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57){
                list.add(String.valueOf(c));
                index++;
            }else {
                str = "";
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57){
                    str += c;
                    index++;
                }
                list.add(str);
            }

        }while (index < expression.length());

        return list;
    }


    public static List<String> getList(String suffixExpression){

        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        for (String str : split) {
            list.add(str);
        }
        return list;
    }


    public static int cal(List<String> lists){

        Stack<String> stack = new Stack<>();

        for (String list : lists) {
            if (list.matches("\\d+")){
                stack.push(list);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (list.equals("+")){
                    res = num1 + num2;
                }else if (list.equals("-")){
                    res = num1 - num2;
                }else if (list.equals("*")){
                    res = num1 * num2;
                }else if (list.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.valueOf(stack.pop());
    }


}

class Operation{

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;


    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
        }
        return res;
    }

}
