package com.seemoonup.common.spring;

/**
 * @ClassName DataSourceHolder
 * @author fupdatebylemon
 * @date 2013-1-8
 * @Description TODO
 */
public class TransactionDataSourceHolder {
	private static final ThreadLocal<Boolean> holder = new ThreadLocal<Boolean>();


	public static boolean isInTransaction() {
		if (holder.get()!=null && holder.get()) {
			return true;
		}

		return false;
	}

	public static void setInTransaction(boolean inTransaction) {
		holder.set(inTransaction);
	}

	public static void clearDataSourceName() {
		holder.remove();
	}
}
