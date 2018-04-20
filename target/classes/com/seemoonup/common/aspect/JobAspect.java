package com.seemoonup.common.aspect;

import com.seemoonup.common.utils.EnvironmentConfig;
import com.seemoonup.common.utils.UrlConfig;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.Method;

/**
 * @ClassName JobAdvice
 * @author lemon
 * @date 2017-7-26
 * @Description
 */
public class JobAspect {

	protected static Logger logger = LoggerFactory.getLogger(JobAspect.class);

	@SuppressWarnings("all")
	public void before(JoinPoint point) throws Exception {

	}

	/**
	 * 判断job是否需要执行
	 *
	 * 1. 拦截Scheduled注解的方法
	 * 2. 判断environment.properties OPEN_JOB是否开启
	 * 3. 开启执行方法体
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		//拦截的实体类
		Object target = pjp.getTarget();
		//拦截的方法名称
		String methodName = pjp.getSignature().getName();
		//拦截的放参数类型
		Class[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
		Class clazz = target.getClass();
		if (clazz == null) {
			return null;
		}

		// 拿方法级别的注解
		Method m = clazz.getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(Scheduled.class)){
			String open_job = EnvironmentConfig.get("OPEN_JOB");
			if (StringUtils.equals("Y", open_job)) {
				logger.info("[JobAspect]" + UrlConfig.get("ENVIRONMENT") + "method:" + clazz.getName() + " ready run");
				return pjp.proceed();
			}
		}

		logger.info("[JobAspect]" + UrlConfig.get("ENVIRONMENT") + "method:" + clazz.getName() + " not run");
		return null;
	}

	public void after(JoinPoint point) {

	}



}
