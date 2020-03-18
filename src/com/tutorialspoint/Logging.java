package com.tutorialspoint;
public class Logging {

    public void beforeAdvice(){
        System.out.println("前置通知");
    }

    public void afterAdvice(){
        System.out.println("后置通知");
    }

    public void afterReturningAdvice(Object retVal){
        System.out.println("返回通知:" + retVal.toString() );
    }

    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("异常通知: " + ex.toString());
    }
}