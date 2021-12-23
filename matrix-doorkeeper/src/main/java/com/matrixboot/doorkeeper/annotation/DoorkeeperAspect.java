package com.matrixboot.doorkeeper.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
public class DoorkeeperAspect {

    private final Semaphore semaphore = new Semaphore(1);

    @Pointcut("@annotation(doorkeeper)")
    public void lockPointCut(Doorkeeper doorkeeper) {
    }

    @Around("lockPointCut(doorkeeper)")
    public Object around(@NotNull ProceedingJoinPoint joinPoint, Doorkeeper doorkeeper) throws Throwable {
        semaphore.acquire();
        Object proceed = joinPoint.proceed();
        semaphore.release();
        return proceed;
    }

}