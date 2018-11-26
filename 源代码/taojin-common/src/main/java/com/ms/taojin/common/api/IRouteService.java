package com.ms.taojin.common.api;

import java.util.Map;

import com.ms.taojin.common.vo.BaseRespVO;

public interface IRouteService {

	/**
	 * 各个子系统的web请求通用入口
	 * 
	 * @param mode
	 * @param service
	 * @param method
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	BaseRespVO webProcess(String mode, String service, String method, Map reqMap);

}
