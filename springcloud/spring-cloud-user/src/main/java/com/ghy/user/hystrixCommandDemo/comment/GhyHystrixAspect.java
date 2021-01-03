package com.ghy.user.hystrixCommandDemo.comment;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;
/**
 *这里面用到的是AOP的知识点，如果不了解可以先自行补下，后面我有空把Spring的AOP原理也写下，这样回头看这个就没这么难了
 */
@Component
@Aspect  //切入
public class GhyHystrixAspect {
    //通过线程池去请求
    ExecutorService executorService= Executors.newFixedThreadPool(10);
    //定义切点针对GhyHystrix进行切入
    @Pointcut(value = "@annotation(GhyHystrix)")
    public void pointCut(){}
    //切入后执行的方法
    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, GhyHystrix hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //定义超时降级
        int timeout=hystrixCommand.timeout();
        //前置的判断逻辑
        Future future=executorService.submit(()->{
            try {
                return joinPoint.proceed(); //执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result;
        try {
            //得到开始和结束时间判断是否超时，如果超时就降级
            result=future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            //超时了就取消请求
            future.cancel(true);
            // 先判断是否为空如果空就把异常抛出去
            if(StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }
            //调用fallback
            result=invokeFallback(joinPoint,hystrixCommand.fallback());
        }
        return result;
    }
   //反射调用
    private Object invokeFallback(ProceedingJoinPoint joinPoint,String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        //拿到方法的信息
        Method method=signature.getMethod();
        //得到参数类型
        Class<?>[] parameterTypes=method.getParameterTypes();
        //以上是获取被代理的方法的参数和Method
        //得到fallback方法
        try {
            Method fallbackMethod=joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
            fallbackMethod.setAccessible(true);
            //完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
