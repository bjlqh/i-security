package com.lqh.security.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Author: liqihua
 * Date: 2019/9/21 17:28
 * <p>
 * spring拦截器比javax filter的优势就在于 第三个参数 handler，
 * 这个是处理你当前请求的控制器方法的声明。
 * <p>
 * notice: 拦截器不仅能拦截你写的控制器，也能拦截springmvc提供的控制器。
 */

@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 进入某个控制器方法之前 会被调用
     * 此方法并不能通过handler拿到方法参数
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return 当返回true时才会进入控制器方法，若返回false则不进入。
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("preHandler ==================");
        if (handler instanceof HandlerMethod) {
            System.out.println("bean name ：" + ((HandlerMethod) handler).getBean().getClass().getName());
            System.out.println("bean simpleName ：" + ((HandlerMethod) handler).getBean().getClass().getSimpleName());
            System.out.println("method name : " + ((HandlerMethod) handler).getMethod().getName());
        }

        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (String key : parameterMap.keySet()) {
            System.out.println(key + " : " + parameterMap.get(key));
        }


        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * 控制器方法被调用之后 会被调用，但是如果控制器方法抛出了异常则不会被调用
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler ==================");
        long start = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 不管控制器方法是否抛出异常，在控制器方法结束以后，都会被调用
     * <p>
     * tips:如果使用了springmvc的异常处理器
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        System.out.println("afterCompletion ==================");
        long start = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时：" + (System.currentTimeMillis() - start));
        System.out.println("exception message is " + e);
    }
}
