package com.xmj.springbootdemo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:处理跨域
 * Author: xieMengJie
 * CreateDate: 2019/1/13 15:40
 */
//@Component
public class OriginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        if (request == null) {
            request = (HttpServletRequest) servletRequest;
        }
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,userId,token,userCode");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
