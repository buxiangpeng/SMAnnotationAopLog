package com.example.util;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvokeLogAspect {
	
	private static Logger log = LoggerFactory.getLogger(InvokeLogAspect.class);
	
	// 切点
	//@Pointcut("within(com.xys.demo2.*)")  包切面
	//@Pointcut("execution(public * com.example.controller.*.*(..))")   方法切面
    @Pointcut("@annotation(com.example.util.MyLog)")      //注解切面
    public void executePointCut() {
    	System.out.println("切面2");
    }
    
    //方法调用之前
    @Before("executePointCut()")
    public void before(JoinPoint joinPoint) {
    	 MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
         Method method = sign.getMethod();
         MyLog annotation = method.getAnnotation(MyLog.class);
         log.info("打印日志:"+annotation.value());
    	 System.out.println("before");
    }
    
    //环绕通知,优先级大于 @Before @After
    @Around("executePointCut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object result = null;
    	System.out.println("环绕通知1");
    	result = joinPoint.proceed(joinPoint.getArgs());
    	System.out.println("环绕通知2");
    	return result;
    }
    
    //方法调用之后
    @After("executePointCut()")
    public void after() {
    	System.out.println("after");
    }
    
    //方法调用成功之后   优先级最低
    @AfterReturning("executePointCut()")
    public void afterReturning() {
    	System.out.println("afterReturning");
    }
    
    //方法调用抛出异常之后  优先级最低
    @AfterThrowing("executePointCut()")
    public void afterThrowing() {
    	System.out.println("afterThrowing");
    }
}
