package com.qicai.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP��̣���������־�����л���
 * @author qzm
 * @since 2015-5-13
 */
//@Component
//@Aspect
public class LogAspect {
	protected Logger logger = Logger.getLogger(this.getClass());

	//��������
	@Pointcut("execution(* com.qicai.service.*.*(..))")
	public void methodCachePointcut() {
	}

	@Around("methodCachePointcut()")//����֪ͨ
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result=
			 point.proceed();//���û�л������ء���ֱ������
		return result;
	}

}
