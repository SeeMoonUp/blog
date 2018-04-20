package com.seemoonup.common.aspect;

import com.seemoonup.common.spring.TransactionDataSourceHolder;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @ClassName DataSourceAdvice
 * @author fupdatebylemon
 * @date 2013-1-8
 * @Description TODO
 */
public class TransactionAspect {

	protected static Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

//	private final static String DEFAULT_DATA_SOURCE_NAME = "master";
	
	@SuppressWarnings("all")
	public void before(JoinPoint point) throws Exception {


		//拦截的实体类
		Object target = point.getTarget();
		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截的放参数类型
		Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
		Class clazz = target.getClass();
		if (clazz == null) {
			return;
		}

		// 拿方法级别的注解
		Method m = clazz.getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(Transactional.class) || isThreadInTrans()){
			TransactionDataSourceHolder.setInTransaction(true);
		}
		
	}

	private boolean isThreadInTrans() {
		boolean isThreadInTrans = false;
		try {
			StackTraceElement[] callers = Thread.currentThread().getStackTrace();

			for (StackTraceElement caller : callers) {
                String className = caller.getClassName();
                if (StringUtils.contains(className, "com.seemoonup.service")) {

                    String methodName = caller.getMethodName();
					Class clazz = Class.forName(className);
					Method[] methods = clazz.getMethods();

                    for (Method method : methods) {
                        if (StringUtils.equals(method.getName(), methodName)) {
                            if (method != null && method.isAnnotationPresent(Transactional.class)){
                                return true;
                            }
                        }
                    }
                }
            }
		} catch (SecurityException e) {
			System.out.println("isThreadInTrans SecurityException");
			e.printStackTrace();
			isThreadInTrans = true;
		} catch (Exception e) {
			System.out.println("isThreadInTrans Exception");
			e.printStackTrace();
			isThreadInTrans = true;
		}

		return isThreadInTrans;
	}

	public void after(JoinPoint point) {
		TransactionDataSourceHolder.clearDataSourceName();
	}



}
