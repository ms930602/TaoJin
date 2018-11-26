package com.ms.taojin.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.RouteService;
import com.ms.taojin.common.service.ThreadContext;
import com.ms.taojin.user.api.IUserRouteService;

@Service("userRouteService")
public class UserRouteService extends RouteService implements IUserRouteService {

	/**
	 * 填充默认的调用参数
	 * 
	 * @param reqMap
	 * @param methodName
	 * @throws CenterException
	 */
	@Override
	public void initReqMap(Map<String, Object> reqMap, String serviceName, String methodName) throws CenterException {
		// 从session中注入数据权限信息
		SessionUser sessionUser = null;
		try {
			sessionUser = ThreadContext.getSessionloginUser();
		} catch (Exception e) {
			return;
		}

		reqMap.put("sysId", sessionUser.getSysId());

		if (methodName.indexOf("update") == -1 && methodName.indexOf("delete") == -1 && 0 != sessionUser.getUserId()) {
			reqMap.put("typeId", sessionUser.getTypeId());
			reqMap.put("typeCode", sessionUser.getTypeCode());
		}
	}

}
