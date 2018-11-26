package com.ms.taojin.common.vo;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 *
 * @author lansongtao
 * @Date 2017年4月5日
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
	String value();
}