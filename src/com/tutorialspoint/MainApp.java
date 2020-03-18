package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("aop.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        System.out.println("--------------------");
        student.getAge();
        System.out.println("--------------------");
        student.printThrowException();
    }
}