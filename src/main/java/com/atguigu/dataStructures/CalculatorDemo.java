package com.atguigu.dataStructures;

public class CalculatorDemo {

    public static void main(String[] args) {

        String expression = "3-2*6+2";

        StackArr2 numStack = new StackArr2(10);
        StackArr2 operStack = new StackArr2(10);

        //index扫描的位置，num1和num2表示获取到的一个数，oper运算符，res结果
        int index = 0,num1 = 0,num2 = 0,oper = 0,res = 0;
        char ch = ' ';
        String keepNum = "";

        while (true){

            ch = expression.substring(index, index + 1).charAt(0);

            if (operStack.isOper(ch)){  //判断当前是不是符号
                if (!operStack.isEmply()){
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.calculation(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {

                keepNum += ch;

                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index + 1,index + 2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

                //numStack.push(ch - 48);  //因为ACSII里面1是49
            }
            index++;
            if (index >= expression.length()) break;
        }

        while (true){

            if (operStack.isEmply()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calculation(num1, num2, oper);
            numStack.push(res);

        }

        System.out.printf("表达式 %s = %d",expression,numStack.pop());
    }

}


class StackArr2{

    private int maxSize;
    private int[] stack;
    private int top;

    public StackArr2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
        top = -1;
    }


    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmply(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop(){
        if (isEmply()){
            throw new RuntimeException("栈为空");
        }

        int value = stack[top];
        top--;
        return value;
    }


    public void showStack(){

        if (isEmply()){
            System.out.println("栈为空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n" ,i, stack[i]);
        }

    }

    public int peek(){
        return stack[top];
    }

    //判断当前符号的优先级
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断当前是不是符号
    public boolean isOper(int oper){
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    //进行计算
    public int calculation(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
        }
        return res;
    }


}