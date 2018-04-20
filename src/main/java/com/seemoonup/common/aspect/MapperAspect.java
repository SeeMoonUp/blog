package com.seemoonup.common.aspect;

import com.seemoonup.common.datasource.DataSourceHolder;
import com.seemoonup.common.datasource.DataSourceSelector;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @ClassName DataSourceAdvice
 * @author fupdatebylemon
 * @date 2013-1-8
 * @Description TODO
 */
public class MapperAspect {

	protected static Logger logger = LoggerFactory.getLogger(MapperAspect.class);

	private final static String DEFAULT_DATA_SOURCE_NAME = "master";
	
	@SuppressWarnings("all")
	public void before(JoinPoint point) throws Exception {

		DataSourceHolder.clearDataSourceName();

		//拦截的实体类
		Object target = point.getTarget();
		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截的放参数类型
		Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
		Class[] clazzs = target.getClass().getInterfaces();
		Class clazz = null;
		if (clazzs != null && clazzs.length > 0){
			clazz = clazzs[0];
		}
		
		if (clazz == null) {
			DataSourceHolder.setDataSourceName(DEFAULT_DATA_SOURCE_NAME);
			return;
		}
		
		// 拿方法级别的注解
		Method m = clazz.getMethod(methodName, parameterTypes);
		if (m != null && m.isAnnotationPresent(DataSourceSelector.class)){
			DataSourceSelector ca = m.getAnnotation(DataSourceSelector.class);
			if (StringUtils.isNotBlank(ca.value())){
				DataSourceHolder.setDataSourceName(ca.value());
				return;
			}
		}
		
		// 拿到类级别的注解
		DataSourceSelector can = (DataSourceSelector) clazz.getAnnotation(DataSourceSelector.class);
		if(can != null && StringUtils.isNotBlank(can.value())){
			DataSourceHolder.setDataSourceName(can.value());
			return;
		}
		
		// 都没有默认用master
		DataSourceHolder.setDataSourceName(DEFAULT_DATA_SOURCE_NAME);
	}

	public void after(JoinPoint point) {
		DataSourceHolder.clearDataSourceName();
	}
}
