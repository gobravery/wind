package com.gobravery.wind.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogPrint {
	@Pointcut("execution(* mvc1.csscis.common..*(..))")
	private void anyMethod() {
		
		//System.out.println("log:anyMethod");
		
	}//声明一个切入点	
	@Before("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.*(..))")//定义前置通知
	public void doBefore(JoinPoint  userName) {
		System.out.println("log:before->定义前置通知"+userName);
	}
	@AfterReturning(pointcut="anyMethod()",returning="revalue")//定义后置通知
	public void doReturnCheck(String revalue) {
		System.out.println("log:before->定义后置通知");
	}
	@AfterThrowing(pointcut="anyMethod()", throwing="ex")//定义例外通知
    public void doExceptionAction(Exception ex) {
		System.out.println("log:before->定义例外通知");
	}
	@After("anyMethod()")//定义最终通知
	public void doReleaseAction() {
		System.out.println("log:before->定义最终通知");
	}
	@Around("anyMethod()")//环绕通知
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("log:before->环绕通知");
		return pjp.proceed();
	}
}
