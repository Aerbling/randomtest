package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Author: fg
 * @Time: 2023-04-19 15:44
 * @function: 在txt文件中的知识点中随机挑选一题
 * @Description 运行后数据答案，回车即是下一题，输入exit可以退出
 */
@SuppressWarnings("all")
public class Test2 {

    HashMap<Integer,String[]> hashMap;


    public static void main(String[] args) throws IOException {
        String oriPath = "D:\\study\\Java\\bigdata\\qita\\大数据1\\知识点集合\\大数据一知识汇总.txt";
        Test2 test2 = new Test2();
        test2.extractCatalog(oriPath);
        Set<Map.Entry<Integer, String[]>> entries = test2.hashMap.entrySet();
        for (Map.Entry<Integer, String[]> entry : entries) {
            int i = entry.getKey();
            String[] value = entry.getValue();
            System.out.println(entry.getValue()[0]);
            System.out.println(entry.getValue()[1]);
        }


    }


    public  void extractCatalog(String oriPath) throws IOException {
        hashMap = new HashMap();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(oriPath));

        String line;            // 每行数据
        String content = "";    //答案
        int count = 0;           //上一个序号
        String preLine = "";
        int temp;               //本行数据头部的 序号

        while ((line = bufferedReader.readLine()) != null) {
            //判断  这一行是否是  标题
            if (line.matches("^[0-9]+\\..+$")) {
//            if (line.matches("^\\#{1} "))

                String[] split = line.split("\\.");
//                String[] split1 = line.split(" ");
                temp = Integer.parseInt(split[0]);

                // 如果不是小标题，再开始写入
                if (temp > count) {
                    //给上一个序号赋值
                    if (count > 0) {

                        String[] strings1 = new String[2];
                        strings1[0] = preLine;
                        strings1[1] = content;
                        hashMap.put(count, strings1);
                    }
                    content = "";
                    //上一个标题 和 序号前进
                    preLine = line;
                    count = temp;
                    continue;
                }

                //非标题
            }
            content = content + line;

        }

        // 把最后一题写入
        String[] strings1 = new String[2];
        strings1[0] = preLine;
        strings1[1] = content;
        hashMap.put(count, strings1);

        //关闭流
        bufferedReader.close();
    }


    public static void qing() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("D:\\study\\then.txt"));
        String line = null;

        while ((line = reader.readLine()) != null) {
            arrayList.add(line);
        }
        int length = arrayList.size();

        System.out.println("随机一题：");
        while (true){
            int index = (int) (Math.random() * length);
            System.out.println(arrayList.get(index));
            String s = scanner.nextLine();
            if (s.equals("exit")){
                break;
            }
        }
    }

}
