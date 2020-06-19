package com.atguigu.dataStructures;

import org.omg.CORBA.OBJ_ADAPTER;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class HuffmanCodeDemo {


    public static void main(String[] args) {

//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//
//        List<Node> nodes = getNodes(contentBytes);
//        //System.out.println(nodes);
//
//        Node node = createHuffmanTree(nodes);
//        //System.out.println("前序遍历");
//        //node.preOrder();
//
//        getCodes(node,"",stringBuilder);
//        //System.out.println(huffmanCodes);
//
//        byte[] bytes = getBytes(contentBytes, huffmanCodes);
//        System.out.println(Arrays.toString(bytes));
//
//        byte[] decode = decode(huffmanCodes, bytes);
//        System.out.println(new String(decode));

//        String srcFile = "d://bf.jpg";
//        String dstFile = "d://bf.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("OK");

        String zipFile = "d://bf.zip";
        String dstFile = "d://bf2.jpg";
        unZipFile(zipFile,dstFile);
        System.out.println("OK");

    }

    static Map<Byte,String> huffmanCodes = new HashMap<>();

    static StringBuilder stringBuilder = new StringBuilder();


    public static void unZipFile(String zipFile,String dstFile){

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {

            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanByte = (byte[]) ois.readObject();

            Map<Byte, String> huffmanCode = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanCode, huffmanByte);

            os = new FileOutputStream(dstFile);
            os.write(decode);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void zipFile(String srcFile,String dstFile){

        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(srcFile);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            byte[] huffmanZip = huffmanZip(bytes);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanZip);

            oos.writeObject(huffmanCodes);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);

        Node huffmanTree = createHuffmanTree(nodes);

        Map<Byte, String> huffmanCode = getCodes(huffmanTree);

        byte[] huffmanCodeBytes = getBytes(bytes, huffmanCode);

        return huffmanCodeBytes;
    }


    public static Map<Byte,String> getCodes(Node root){

        if(root == null){
            return null;
        }

        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);

        return huffmanCodes;
    }


    public static String byteToBitString(boolean flag,byte b){
        int temp = b;

        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }


    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            builder.append(byteToBitString(!flag,b));
        }

        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < builder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag){
                String key = builder.substring(i, i + count);
                b = map.get(key);
                if(b == null){
                    count++;
                }else {
                    flag = false;
                }
            }

            list.add(b);
            i +=count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;

    }


    public static byte[] getBytes(byte[] bytes,Map<Byte,String> map){

        StringBuilder builder = new StringBuilder();
        for (Byte b : bytes) {
            builder.append(map.get(b));
        }

        int len = 0;
        if (builder.length() % 8 == 0){
            len = builder.length() / 8;
        }else {
            len = builder.length() / 8 + 1;
        }

        byte[] huffmanByteCode = new byte[len];
        int index = 0;
        for (int i = 0; i < builder.length(); i += 8) {
            String strByte = null;
            if (i + 8 > builder.length()){
                strByte = builder.substring(i);
            }else {
                strByte = builder.substring(i,i + 8);
            }
            huffmanByteCode[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }

        return huffmanByteCode;

    }


    public static void getCodes(Node node,String code,StringBuilder stringBuilder){

        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);

        if (node != null){
            if (node.data == null){
                getCodes(node.left,"0",builder);
                getCodes(node.right,"1",builder);
            }else {
                huffmanCodes.put(node.data,builder.toString());
            }
        }

    }


    public static List<Node> getNodes(byte[] contextBytes){

        List<Node> list = new ArrayList<>();

        Map<Byte,Integer> counts = new HashMap<>();

        for (byte b : contextBytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            list.add(new Node(entry.getKey(),entry.getValue()));
        }

        return list;

    }

    public static Node createHuffmanTree(List<Node> nodes){

        while (nodes.size() > 1){

            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);

        }

        return nodes.get(0);

    }


}


class Node implements Comparable<Node>{

    public Byte data;

    public int weight;

    public Node left;

    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
