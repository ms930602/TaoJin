package com.ms.taojin.pre.controller;

import java.io.IOException;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.pre.service.SessionHandler;
import com.ms.taojin.pre.utils.Constants;
import com.ms.taojin.pre.utils.CookieUtils;
import com.ms.taojin.pre.utils.Utils;
import com.ms.taojin.user.api.ILoginService;
import com.ms.taojin.user.vo.LoginRespVo;

@Controller
@RequestMapping("login")
public class ControllerLogin {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ILoginService ILoginService;
	@Autowired
	SessionHandler sessionHandler;
	@Autowired
	ControllerAuthCode controllerAuthCode;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("=================收到http请求：用户登录=================" + Utils.getIpAddr(request));
		// 校验验证码
		// if (!controllerAuthCode.check(request)) {
		// Utils.writeResponesByJsonp(request, response, BaseRespVO.error("验证码错误"));
		// return;
		// }

		// 校验通过后删除验证码之前的session和Cookie
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		CookieUtils.delCookie(request, response, Constants.AUTH_CODE_SESSION_ID);

		LoginRespVo resp = null;
		try {

			Map reqMap = Utils.parameterMapString(request);
			resp = ILoginService.login(reqMap);

			// 登录成功，创建session
			if (resp.getState() == 0) {
				createSession(request, resp);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Utils.writeResponesByJsonp(request, response, BaseRespVO.error(e.getMessage()));
		}
		logger.info("=================返回http响应：{}=================", resp);
		Utils.writeResponesByJsonp(request, response, resp);
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "logout")
	@ResponseBody
	public BaseRespVO logout(HttpServletRequest request) throws JsonProcessingException {
		logger.info("=================收到http请求：用户登出=================");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			return new BaseRespVO(0, "登出成功");
		} else {
			return new BaseRespVO(0, "没有找到该用户登录信息！");
		}

	}

	private void createSession(HttpServletRequest request, LoginRespVo resp) {
		HttpSession session = request.getSession(true);
		// 将系统生成的sessionId作为token返回给前端
		resp.setToken(session.getId());
		// 在session中保存登录信息
		session.setAttribute(Constants.SESSION_AUTHS_KEY, resp.getAuthCheckVo());
		session.setAttribute(com.ms.taojin.common.Constants.SESSION_LOGIN_USER, resp.getSessionUser());
		session.setAttribute(com.ms.taojin.common.Constants.REQUEST_CLIENT_IP, Utils.getIpAddr(request));
		// 在redis中，保存userId和 sessionId 的映射关系
		sessionHandler.putSessionId(resp.getLoginUser().getUserId(), session.getId());
	}

}
