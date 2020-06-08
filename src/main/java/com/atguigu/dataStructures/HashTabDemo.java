package com.atguigu.dataStructures;


import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        Scanner scanner = new Scanner(System.in);
        String next = "";
        while (true){
            System.out.println("add \t 添加数据");
            System.out.println("show \t 查看哈希表数据");
            System.out.println("del \t 删除数据");
            System.out.println("update \t 编辑数据");
            System.out.println("find \t 查找一条数据");
            System.out.println("exit \t 退出Scanner");
            next = scanner.next();
            switch (next){
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入name");
                    String name = scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case "show":
                    hashTab.show();
                    break;
                case "find":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    Emp emp = hashTab.findById(id);
                    System.out.println("emp=" + emp);
                    break;
                case "del":
                    System.out.println("请输入需要删除的id");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "update":
                    System.out.println("请输入需要更新的id");
                    id = scanner.nextInt();
                    System.out.println("请输入需要更新的name");
                    name = scanner.next();
                    hashTab.update(new Emp(id,name));
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }
        }

    }

}


class HashTab{

    private EmpLinkedListArray[] empLinkedListArrays;

    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArrays = new EmpLinkedListArray[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArrays[i] = new EmpLinkedListArray();
        }
    }

    public int hashFun(int id){
        return id % size;
    }


    public void add(Emp emp){

        int i = hashFun(emp.id);
        empLinkedListArrays[i].add(emp);

    }


    public void show(){
        for (int i = 0; i < size; i++) {
            empLinkedListArrays[i].show();
        }
    }


    public Emp findById(int id){
        int i = hashFun(id);
        Emp emp = empLinkedListArrays[i].findById(id);
        if (emp != null){
            return emp;
        }else {
            System.out.println("哈希表中没有要找的数据");
            return null;
        }
    }


    public void del(int id){
        int i = hashFun(id);
        empLinkedListArrays[i].del(id);
    }


    public void update(Emp emp){
        int i = hashFun(emp.id);
        empLinkedListArrays[i].update(emp);
    }


}


class EmpLinkedListArray{

    private Emp head;

    public void add(Emp emp){

        if (head == null){
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (true){

            if (curEmp.next == null){
                curEmp.next = emp;
                break;
            }

            curEmp = curEmp.next;
        }
    }


    public void show(){

        if (head == null){
            System.out.println("链表为空");
            return;
        }

        Emp curEmp = head;

        while (true){
            System.out.printf("链表数据为 id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();

    }


    public Emp findById(int id){

        if (head == null){
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }

        return curEmp;
    }


    public void del(int id){

        if (head == null){
            System.out.println("链表为空");
            return;
        }

        Emp curEmp = head;
        while (true){
            if (head.id == id){
                head = curEmp.next;
                System.out.println("删除成功");
                break;
            }
            if (curEmp.next.id == id){
                curEmp.next = curEmp.next.next;
                System.out.println("删除成功");
                break;
            }
            if (curEmp.next == null){
                System.out.println("没有此数据");
                break;
            }
            curEmp = curEmp.next;
        }

    }


    public void update(Emp emp){

        if (head == null){
            System.out.println("链表为空");
            return;
        }

        Emp curEmp = head;
        while (true){
            if (curEmp.id == emp.id){
                curEmp.name = emp.name;
                System.out.println("编辑成功");
                break;
            }
            if (curEmp.next == null){
                System.out.println("链表中没有此数据");
                break;
            }
            curEmp = curEmp.next;
        }

    }


}


class Emp{

    public int id;

    public String name;

    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
