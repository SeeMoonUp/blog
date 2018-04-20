package com.seemoonup.common.datasource;

import java.lang.annotation.*;

/**
 * @ClassName DataSourceSelector
 * @author fupdatebylemon
 * @date 2013-1-9
 * @Description TODO
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSourceSelector {
	
	String value() default "master";
	
}
