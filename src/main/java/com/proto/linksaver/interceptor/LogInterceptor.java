package com.proto.linksaver.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        if (handler instanceof HandlerMethod handlerMethod) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : "Anonymous";
            String methodName = handlerMethod.getMethod().getName();
            String requestMethod = request.getMethod();
            String requestURI = request.getRequestURI();
            String responseType = handlerMethod.getReturnType().getParameterType().getName();
            log.info("Request Method: {} | Method: {} | Request URI: {} | Response Type: {} | Username: {} | IP Address: {}",
                    requestMethod, methodName, requestURI, responseType, username, request.getRemoteAddr());
        } else {
            log.warn("Handler is not a HandlerMethod. Logging may be incomplete.");
        }
    }
}