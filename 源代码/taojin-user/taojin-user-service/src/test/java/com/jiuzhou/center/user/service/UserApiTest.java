package com.ms.center.user.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.ms.taojin.user.api.IUserRouteService;
import com.ms.taojin.user.service.UserApiService;

@ContextConfiguration(locations = { "/applicationContext-service-test.xml" })
public class UserApiTest extends AbstractJUnit4SpringContextTests {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserApiTest.class);

	@Autowired
	IUserRouteService userRouteService;

	@Autowired
	UserApiService userApiService;

	@Test
	public void queryListByType() throws Exception {
		List<String> list = new ArrayList<String>();
//		list.add("mar221");
		list.add("111");
		list.add("001003");
		

		System.out.println(userApiService.queryListByType("market", list, "1"));

	}

}
