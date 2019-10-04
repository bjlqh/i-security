package com.lqh.security.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Author: liqihua
 * Date: 2019/10/4 15:26
 */
@Slf4j
@Component
public class OrderDeferredResultInterceptor implements DeferredResultProcessingInterceptor {
    /**
     * DeferredResult之前执行
     *
     * @param request
     * @param deferredResult
     * @param <T>
     * @throws Exception
     */
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        log.info("DeferredResult:beforeConcurrentHandling ==================");
        HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (String key : parameterMap.keySet()) {
            System.out.println(key + " : " + Arrays.toString(parameterMap.get(key)));
        }

        Object result = deferredResult.getResult();
        if (result != null) {
            System.out.println(result.toString());
        }
    }

    /**
     * beforeConcurrentHandling 之后执行
     *
     * @param request
     * @param deferredResult
     * @param <T>
     * @throws Exception
     */
    @Override
    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        log.info("DeferredResult:preProcess ==================");
        Object result = deferredResult.getResult();
        if (result != null) {
            System.out.println(result.toString());
        }
    }

    /**
     * deferredResult超时之前调用，在postHandler之前调用
     *
     * @param request
     * @param deferredResult
     * @param concurrentResult
     * @param <T>
     * @throws Exception
     */
    @Override
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult) throws Exception {
        log.info("DeferredResult:postProcess ==================");
        Object result = deferredResult.getResult();
        if (result != null) {
            System.out.println(result.toString());
        }
    }

    /**
     * 超时调用
     *
     * @param request
     * @param deferredResult
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        log.info("调用超时，正在处理超时请求");
        deferredResult.setResult((T) "place order time out");
        String s = deferredResult.getResult().toString();
        log.error(s);
        return true;
    }

    /**
     * 在正常afterCompletion之后调用
     *
     * @param request
     * @param deferredResult
     * @param <T>
     * @throws Exception
     */
    @Override
    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        log.info("DeferredResult:afterCompletion ==================");
    }
}
