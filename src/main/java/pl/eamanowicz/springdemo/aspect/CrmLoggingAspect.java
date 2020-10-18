package pl.eamanowicz.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CrmLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* pl.eamanowicz.springdemo.controller.*.*(..))")
    public void forControllerPackage() {
    }

    @Pointcut("execution(* pl.eamanowicz.springdemo.service.*.*(..))")
    public void forServicePackage() {
    }

    @Pointcut("execution(* pl.eamanowicz.springdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("(forControllerPackage() || forServicePackage() || forDaoPackage())")
    public void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @Before: calling method: " + method);

        Object[] objects = joinPoint.getArgs();
        for (Object tempObject : objects) {
            logger.info("Arg: " + tempObject);
        }

    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @AfterReturning: calling method: " + method);

        logger.info("result ======> " + result);
    }

}
