package com.ms.center.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.druid.pool.DruidDataSource;
import com.ms.taojin.common.service.CountLoginsService;
import com.ms.taojin.user.api.IUserRouteService;
import com.ms.taojin.user.bo.UserBO;
import com.ms.taojin.user.entity.UserEntity;
import com.ms.taojin.user.mapper.UserMapper;
import com.ms.taojin.user.service.LoginService;
import com.ms.taojin.user.service.UserService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ContextConfiguration(locations = { "/applicationContext-service-test.xml" })
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	IUserRouteService userRouteService;

	@Autowired
	LoginService loginService;

	@Autowired
	UserBO userBO;

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	CountLoginsService countLoginsService;

	@Autowired(required = false)
	DruidDataSource dataSource;

	@Test
	public void delete() throws Exception {
//		Map reqMap = new Hashtable();

		// reqMap.put("keyWord", "宰场用");

		// reqMap.put("userId", "40");
		// reqMap.put("testStr", "啊啊啊啊");

//		System.out.println(userRouteService.webProcess("user", "user", "list", reqMap));

		while (true) {
			System.out.println("getActiveCount" + dataSource.getActiveCount());
			System.out.println("getActivePeak" + dataSource.getActivePeak());
			System.out.println("getCloseCount" + dataSource.getCloseCount());
			System.out.println("getConnectCount" + dataSource.getConnectCount());
			Thread.sleep(5000);
			System.out.println("=============================");
		}

	}

	@Test
	public void list() throws Exception {
	 System.out.println(countLoginsService.queryTodayLogins(null));
	}

	@Test
	public void listLocal() throws Exception {
		Map reqMap = new HashMap();
//		SessionUser user = new SessionUser();
		// user.setSessionSysId("6");

//		reqMap.put(Constants.SESSION_LOGIN_USER, user);

//		reqMap.put("loginFaildTimes", "0.23");
//		reqMap.put("mobile", "12312312312312312312312312312312312");

		System.out.println(userRouteService.webProcess("user", "user", "list", reqMap));
	}

	@Test
	public void updateLocal() throws Exception {
		Map reqMap = new HashMap();

		reqMap.put("userId", "0");
		reqMap.put("mobile", "12312312312312312312312312312312312");
		// reqMap.put("nickName", "");
		reqMap.put("lastLoginTime", "2017-01-02 10:32:14");

		System.out.println(userRouteService.webProcess("user", "user", "update", reqMap));
	}

	@Test
	public void batchSave() throws Exception {
		List<UserEntity> entitys = new ArrayList<UserEntity>();

		for (int i = 0; i < 10; i++) {
			UserEntity entity = new UserEntity();
			entity.setLoginName("0506test" + i);
			entity.setPassword("123456");
			// entity.setMobile("123123");
			entitys.add(entity);
		}
		userBO.batchSave(entitys);
	}

	@Test
	public void login() throws Exception {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("loginName", "admin");
		reqMap.put("password", "admin123");
		reqMap.put("systemId", "1");

		System.out.println(loginService.login(reqMap));
	}


}
