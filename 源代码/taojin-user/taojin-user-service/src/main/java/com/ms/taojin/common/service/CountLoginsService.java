package com.ms.taojin.common.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.utils.DateUtilSafe;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.user.mapper.UserExtendMapper;

/**
 * 用户登录次数统计
 *
 * @author lansongtao
 * @Date 2018年6月11日
 * @since 1.0
 */
@Service
public class CountLoginsService extends BaseService {

	@Autowired
	RedisTemplate<String, RedisAtomicLong> redisTemplate;
	@Autowired
	UserExtendMapper userExtendMapper;

	String REDIS_KEY = "COUNT_LOGINS_";

	/**
	 * 根据用户类型查询当日用户登录次数
	 * 
	 * @param typeIds
	 * @return
	 */
	public Map<String, Long> queryTodayLogins(@Param("typeIds") String[] typeIds) {
		if (typeIds == null || typeIds.length == 0) {
			typeIds = userExtendMapper.queryAllTypeId();
		}
		Map<String, Long> resutMap = new HashMap<>();
		for (String typeId : typeIds) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			String key = REDIS_KEY + DateUtilSafe.format(calendar.getTime(), DateUtilSafe.dtShort) + "_" + typeId;
			RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
			long logins = redisAtomicLong.get();
			if (logins == 0) {
				redisTemplate.delete(key);
				continue;
			}
			resutMap.put(typeId, logins);
		}

		return resutMap;
	}

	/**
	 * 统计用户登录次数
	 * 
	 * @param typeId
	 */
	public void countLogins(String typeId) {
		String key = REDIS_KEY + DateUtilSafe.getCurrentDate() + "_" + typeId;

		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		// 有效期2天
		redisAtomicLong.expire(2, TimeUnit.DAYS);
		// 次数加一
		redisAtomicLong.incrementAndGet();
	}

}
