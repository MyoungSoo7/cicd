package com.lms.lomboktest.food.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class PerformanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 전처리작업
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        //  handler - 요청하고 연결된 컨트롤러의 메서드
        HandlerMethod method = (HandlerMethod) handler;
        log.info("method.getMethod() = " + method.getMethod());
        log.info("method.getBean() = " + method.getBean());

        return HandlerInterceptor.super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 2. 후처리 작업
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("[" + request.getRequestURI() + "]");
        log.info(" time =" + (endTime - startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


}

