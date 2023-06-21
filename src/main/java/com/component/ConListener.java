package com.component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

/**
 * @Author: fg
 * @Time: 2023-04-19 17:50
 * @function: 监听器
 */
@SuppressWarnings("all")
@WebListener
public class ConListener implements ServletContextListener {
    HashMap<Integer, String[]> hashMap;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Properties properties = new Properties();
            properties.load(ConListener.class.getClassLoader().getResourceAsStream("init.properties"));
            String str = (String) properties.get("oriPath");
            byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
            str = new String(bytes, StandardCharsets.UTF_8);

            extractCatalog(str);  //将文件的内容写入内存
            ServletContext context = servletContextEvent.getServletContext();

            context.setAttribute("hashmap",hashMap);

        } catch (IOException e) {
            System.out.println("加载文件时出现异常....");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

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

                String[] split = line.split("\\.");
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
            content = content + line + "\n";

        }

        // 把最后一题写入
        String[] strings1 = new String[2];
        strings1[0] = preLine;
        strings1[1] = content;
        hashMap.put(count, strings1);

        //关闭流
        bufferedReader.close();
    }

}
