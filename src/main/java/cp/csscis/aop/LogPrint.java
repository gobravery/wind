package cp.csscis.aop;

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
		
	}//����һ�������	
	@Before("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.*(..))")//����ǰ��֪ͨ
	public void doBefore(JoinPoint  userName) {
		System.out.println("log:before->����ǰ��֪ͨ"+userName);
	}
	@AfterReturning(pointcut="anyMethod()",returning="revalue")//�������֪ͨ
	public void doReturnCheck(String revalue) {
		System.out.println("log:before->�������֪ͨ");
	}
	@AfterThrowing(pointcut="anyMethod()", throwing="ex")//��������֪ͨ
    public void doExceptionAction(Exception ex) {
		System.out.println("log:before->��������֪ͨ");
	}
	@After("anyMethod()")//��������֪ͨ
	public void doReleaseAction() {
		System.out.println("log:before->��������֪ͨ");
	}
	@Around("anyMethod()")//����֪ͨ
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("log:before->����֪ͨ");
		return pjp.proceed();
	}
}
