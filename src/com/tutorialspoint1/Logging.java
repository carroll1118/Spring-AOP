package com.tutorialspoint1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;

@Aspect
public class Logging {

    @Pointcut("execution(* com.tutorialspoint1.*.*(..))")
    private void selectAll(){}

    @Before("selectAll()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }

    @After("selectAll()")
    public void afterAdvice(){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut = "selectAll()", returning="retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println("返回通知:" + retVal.toString() );
    }

    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("异常通知: " + ex.toString());
    }
}