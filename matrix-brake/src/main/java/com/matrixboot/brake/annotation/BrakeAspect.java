package com.matrixboot.brake.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

/**
 * <p>
 * create in 2021/12/23 3:50 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Aspect
@Order(2)
@Component
public class BrakeAspect {

    private final Semaphore semaphore = new Semaphore(1);

    /**
     * AOP 切面
     *
     * @param joinPoint ProceedingJoinPoint
     * @param brake     Brake
     * @return Object
     * @throws Throwable 异常信息
     */
    @Around("@annotation(brake)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint, Brake brake) throws Throwable {
        semaphore.acquire();
        Object proceed = joinPoint.proceed();
        semaphore.release();
        return proceed;
    }

}