package com.component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: fg
 * @Time: 2023-04-20 14:06
 * @function:  过滤器
 */
@WebFilter(urlPatterns = {"*"})
public class ConFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
