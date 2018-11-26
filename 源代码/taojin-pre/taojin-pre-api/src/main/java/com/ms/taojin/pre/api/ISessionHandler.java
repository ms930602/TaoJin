package com.ms.taojin.pre.api;

import java.util.List;

public interface ISessionHandler {
	/**
	 * 根据用户ID删除登录会话信息
	 * 
	 * @param userIdList
	 */
	void deleteSession(List<Long> userIdList);
}
