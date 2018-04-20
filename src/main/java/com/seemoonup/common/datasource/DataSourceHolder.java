package com.seemoonup.common.datasource;

/**
 * @ClassName DataSourceHolder
 * @author fupdatebylemon
 * @date 2013-1-8
 * @Description TODO
 */
public class DataSourceHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void setDataSourceName(String name) {
		holder.set(name);
	}

	public static String getDataSourceName() {
		return holder.get();
	}

	public static void clearDataSourceName() {
		holder.remove();
	}
}
