package com.danoth.todo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
public class ControllerLogAop {

    @Pointcut("execution(* com.danoth.todo.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.danoth.todo.aop.annotation.Timer)")
    private void enableTimer() {}

    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint){
        // 메서드 정보
        Method method = getMethod(joinPoint);
        log.info("======== method name = {} =======", method.getName());

        // 파라미터 정보
        Object[] args = joinPoint.getArgs();
        if(args.length <= 0){
            log.info("no parameter");
            return;
        }
        for (Object arg : args) {
            log.info("parameter type = {} ", arg.getClass().getSimpleName());
            log.info("parameter value = {} ", arg);
        }
        log.info("============== end =================");
    }

    // PointCut 이후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj){
        // Method 정보
        Method method = getMethod(joinPoint);
        log.info("========== method name == {} =======", method.getName());

        log.info("return type = {} ", returnObj.getClass().getSimpleName());
        log.info("return value = {} ", returnObj);
        log.info("============== end =================");
    }

    private Method getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}