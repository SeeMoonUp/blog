package com.seemoonup.common.datasource;

import com.seemoonup.common.spring.TransactionDataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		if (TransactionDataSourceHolder.isInTransaction()) {
			return null;
		} else {
			return DataSourceHolder.getDataSourceName();
		}
	}
}
