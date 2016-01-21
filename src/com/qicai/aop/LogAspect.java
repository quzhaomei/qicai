package com.qicai.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP编程，试用于日志，还有缓存
 * @author qzm
 * @since 2015-5-13
 */
//@Component
//@Aspect
public class LogAspect {
	protected Logger logger = Logger.getLogger(this.getClass());

	//切面配置
	@Pointcut("execution(* com.qicai.service.*.*(..))")
	public void methodCachePointcut() {
	}

	@Around("methodCachePointcut()")//环绕通知
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result=
			 point.proceed();//如果没有缓存拦截。则直接运行
		return result;
	}

}
