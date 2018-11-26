package com.ms.taojin.user.api;

import java.util.Map;

import com.ms.taojin.user.vo.LoginRespVo;

@SuppressWarnings("rawtypes")
public interface ILoginService {

	/**
	 * 登录
	 * 
	 * @param reqMap
	 * @return
	 */

	LoginRespVo login(Map reqMap);

}
