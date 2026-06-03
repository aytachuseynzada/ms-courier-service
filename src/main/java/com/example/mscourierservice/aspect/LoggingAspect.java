package com.example.mscourierservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.example.mscourierservice..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        try {
            log.info("ActionLog.{}.{}.start", className, methodName);

            Object result = joinPoint.proceed();

            log.info("ActionLog.{}.{}.end", className, methodName);

            return result;

        } catch (Throwable exception) {

            log.error(
                    "ActionLog.{}.{}.error: {}",
                    className,
                    methodName,
                    exception.getMessage()
            );

            throw exception;
        }
    }
}
