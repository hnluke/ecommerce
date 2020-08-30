package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *登录检查
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1、要从session里获取loginUser（登录对象）
        Object user=request.getSession().getAttribute("user");

        //对象为空，说明没有登录
        if(user==null){
            request.setAttribute("msg","没有权限请先登录！");
            request.getRequestDispatcher("/user").forward(request,response);
            return false;  //请求阻止
        }else{  //对象不为空，说明已登录，请求放行
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}

