package com.ms.taojin.pre.interceptor;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.taojin.common.utils.DateUtilSafe;

@Component("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
		this.setDateFormat(DateUtilSafe.getSdf(DateUtilSafe.dtLong));
//		this.setSerializationInclusion(Include.NON_NULL);
//		this.setSerializationInclusion(Include.NON_EMPTY);
	}

}
