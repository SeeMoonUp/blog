/**
 * 
 */
package com.seemoonup.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * 读取调用URL配置
 * @author fei.bian
 *
 */
public class UrlConfig {
	public final static int PAY_SERVER_CONN_TIME_OUT = 20000; 
    public final static int PAY_SERVER_READ_TIME_OUT = 20000;
    
	public static Properties properties = new Properties();	
	
	private static final Log QMP_LOG = LogFactory.getLog(UrlConfig.class.getName());
	
	static{  
		try{
			properties.load(UrlConfig.class.getResourceAsStream("/url.properties"));
		}catch(Exception ex){
			ex.printStackTrace();
			QMP_LOG.error("Read url.properties error!");
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
