package com.danoth.todo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Order(1)
@Component
public class TimerAop {
    @Pointcut("execution(* com.danoth.todo.controller.TodoController.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.danoth.todo.aop.annotation.Timer)")
    private void enableTimer() {}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        // 메서드 종료 후
        stopWatch.stop();

        log.info("Method total time : {}", stopWatch.getTotalTimeSeconds());
    }

}
