package com.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: fg
 * @Time: 2023-04-19 15:39
 * @function:
 */
@SuppressWarnings("all")
@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {

    HashMap<Integer, String[]> hashMap;
    int index;
    int length;



    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        hashMap = (HashMap<Integer, String[]>)context.getAttribute("hashmap");
        length = hashMap.size();
    }


    //显示答案
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] strings = hashMap.get(index);
            resp.getWriter().write(strings[1]);
        } catch (IOException e) {
            resp.getWriter().write("请先抽取题目！ 或者 没有答案");
        }

    }

    // 抽取题目
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        index = (int)(Math.random() * length);

        resp.getWriter().write(hashMap.get(index)[0]);

    }
}
