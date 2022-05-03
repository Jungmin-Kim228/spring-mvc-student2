//package com.nhnacademy.student.controller;
//
//import java.util.Objects;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class LoginCheckInterceptor implements HandlerInterceptor {
//    String excludeUrl = "/";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//        HttpSession session = request.getSession(false);
//        if (request.getContextPath().equals("/")) {
//            return true;
//        }
//        if (Objects.isNull(session) || request.getCookies().length == 0) {
//            response.sendRedirect("/");
//            return false;
//        }
//        return true;
//    }
//}
