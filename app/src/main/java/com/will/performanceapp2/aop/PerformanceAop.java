package com.will.performanceapp2.aop;

import com.will.performanceapp2.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * AOP的使用
 */
@Aspect
public class PerformanceAop {
    @Around("call(* com.will.performance.PerformanceApp.**(..))")
    public void getTime(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LogUtils.i(name + "cost :" + (System.currentTimeMillis() - time));
    }

    @Around("execution (* android.app.Activity.setContentView(..))")
    public void getSetContentViewTime(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LogUtils.i(name + "cost :" + (System.currentTimeMillis() - time));
    }
} 