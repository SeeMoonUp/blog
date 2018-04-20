/**
 * 
 */
package com.seemoonup.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * 读取环境配置文件
 * Created by lemon on 2017/7/25.
 */
public class EnvironmentConfig {

	public static Properties properties = new Properties();	
	
	private static final Log QMP_LOG = LogFactory.getLog(EnvironmentConfig.class.getName());
	
	static{  
		try{
			properties.load(EnvironmentConfig.class.getResourceAsStream("/environment.properties"));
		}catch(Exception ex){
			ex.printStackTrace();
			QMP_LOG.error("Read environment.properties error!");
		}
	}
	
	public static String get(String key, String def) throws Exception {
		if (key != null) {
			return properties.getProperty(key, def);
		} else {
			return def;
		}
	}

	public static String get(String key) {
		String value = null;
		try {
			value = get(key, "");
		} catch (Exception e) {
			QMP_LOG.error("get value by key exception", e);
		}
		return value;
	}
}
