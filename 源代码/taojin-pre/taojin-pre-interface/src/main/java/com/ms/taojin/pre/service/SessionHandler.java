package com.ms.taojin.pre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;

import com.ms.taojin.pre.api.ISessionHandler;

@Component
public class SessionHandler implements ISessionHandler {

	private static final String prefix = "session_map_";

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Autowired
	RedisOperationsSessionRepository redisOperationsSessionRepository;

	@Override
	public void deleteSession(List<Long> userIdList) {
		if (userIdList == null || userIdList.isEmpty()) {
			return;
		}
		for (Long userId : userIdList) {
			List<String> sessionIdList = getSessionId(userId);
			if (sessionIdList == null || sessionIdList.isEmpty()) {
				continue;
			}
			for (String sessionId : sessionIdList) {
				redisOperationsSessionRepository.delete(sessionId);
				redisTemplate.delete(prefix + userId);
			}
		}
	}

	public void putSessionId(long userId, String sessionId) {
		try {
			redisTemplate.opsForSet().add(prefix + userId, sessionId);
		} catch (Exception e) {
			redisTemplate.delete(prefix + userId);
			redisTemplate.opsForSet().add(prefix + userId, sessionId);
		}
		redisTemplate.expire(prefix + userId, 1800, TimeUnit.SECONDS);
	}

	public List<String> getSessionId(long userId) {
		List<String> list = null;

		int size = 0;
		try {
			size = redisTemplate.opsForSet().size(prefix + userId).intValue();
		} catch (Throwable e) {
			return null;
		}
		if (size < 1) {
			return null;
		}

		list = new ArrayList<String>(size);

		String sessionId = null;
		while ((sessionId = redisTemplate.opsForSet().pop(prefix + userId)) != null) {
			list.add(sessionId);

		}
		return list;
	}

}
