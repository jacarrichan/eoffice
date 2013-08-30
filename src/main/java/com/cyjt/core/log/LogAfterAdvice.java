package com.cyjt.core.log;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;

public class LogAfterAdvice implements Advice {
	public void afterReturning(Object returnObj, Method method, Object[] args,
			Object targetObj) throws Throwable {
		if (method.getName().equals("saveLog"))
			return;

		System.out.println("save log is ------------>:" + method.getName());
	}
}
