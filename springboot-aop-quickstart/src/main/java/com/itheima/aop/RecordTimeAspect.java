package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class RecordTimeAspect {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt(){}


    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis() ;


        Object result = pjp.proceed();
        String methodName = pjp.getSignature().getName() ;

        long endTime = System.currentTimeMillis() ;
        log.info("当前方法{}运行时间————{}ms",methodName, endTime - startTime);

        return result ;
    }

    @Before("pt()")
    public void before(JoinPoint jp) {
        log.info("before....");


    }
}
