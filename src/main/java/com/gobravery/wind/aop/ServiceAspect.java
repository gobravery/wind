package com.gobravery.wind.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ServiceAspect {

	
	//���������,�÷����޷�����,��ҪΪ����ͬ������������ʹ�ô˴����õ������
	public void aspect(){	}
	
	/*
	 * ����ǰ��֪ͨ,ʹ���ڷ���aspect()��ע��������
	 * ͬʱ����JoinPoint��������,����û�иò���
	 */
	public void before(JoinPoint joinPoint){
			System.out.println("before " + joinPoint);
	}
	
	//���ú���֪ͨ,ʹ���ڷ���aspect()��ע��������
	public void after(JoinPoint joinPoint){
		System.out.println("after " + joinPoint);
	}
	
	//���û���֪ͨ,ʹ���ڷ���aspect()��ע��������
	public void around(JoinPoint joinPoint){
		long start = System.currentTimeMillis();
		try {
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			System.out.println("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			System.out.println("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
		}
	}
	
	//���ú��÷���֪ͨ,ʹ���ڷ���aspect()��ע��������
	public void afterReturn(JoinPoint joinPoint){
		System.out.println("afterReturn " + joinPoint);
	}
	
	//�����׳��쳣��֪ͨ,ʹ���ڷ���aspect()��ע��������
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		System.out.println("afterThrow " + joinPoint + "\t" + ex.getMessage());
	}
	
}