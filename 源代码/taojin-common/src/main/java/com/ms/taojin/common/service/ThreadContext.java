package com.ms.taojin.common.service;

import com.ms.taojin.common.entity.SessionUser;

public class ThreadContext {

	/* 登录用户信息 */
	private static final ThreadLocal<SessionUser> SESSION_LOGIN_USER = new ThreadLocal<SessionUser>();

	/* 请求客户端的IP */
	private static final ThreadLocal<String> REQUEST_CLIENT_IP = new ThreadLocal<String>();

	public static SessionUser getSessionloginUser() {
		return getSessionloginUser(true);
	}

	public static SessionUser getSessionloginUser(boolean isValidate) {
		SessionUser sessionUser = SESSION_LOGIN_USER.get();
		if (isValidate && sessionUser == null) {
			throw new RuntimeException("登录信息失效，请重新登录!");
		}
		return sessionUser;
	}

	public static void setSessionloginUser(SessionUser sessionUser) {
		SESSION_LOGIN_USER.set(sessionUser);
	}

	public static String getRequestClientIp() {
		return REQUEST_CLIENT_IP.get();
	}

	public static void setRequestClientIp(String requestClientIp) {
		REQUEST_CLIENT_IP.set(requestClientIp);
	}

}
