package com.ms.taojin.pre.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.taojin.common.Constants;
import com.ms.taojin.common.api.IRouteService;
import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.InterfaceRespVO;
import com.ms.taojin.pre.utils.Utils;

@Controller
@RequestMapping("message")
public class ControllerMessage {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "routeServiceMap")
	private Map<String, IRouteService> routeServiceMap;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	RedisOperationsSessionRepository redisOperationsSessionRepository;

	/**
	 * web请求通用入口
	 * 
	 * @param mode
	 * @param service
	 * @param method
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "{mode}")
	@ResponseBody
	public String message(@PathVariable("mode") String mode, HttpServletRequest request) throws JsonProcessingException {
		logger.debug("=================收到http请求：查询消息：{}，服务：{}，方法：{}=================", mode);

		InterfaceRespVO resp = null;
		try {
			// 根据模块名判断本地是否存在该渠道实现
			IRouteService routeService = routeServiceMap.get(mode);
			if (routeService == null) {
				logger.error("没有配置该模块路由信息，模块：【" + mode + "】");
				return objectMapper.writeValueAsString(new BaseRespVO(8, "没有配置该模块路由信息，模块：【" + mode + "】"));
			}

			Map<String, Object> reqMap = Utils.parameterMap(request);

			if (StringUtils.isEmpty(reqMap.get("token") + "")) {
				return objectMapper.writeValueAsString(new BaseRespVO(97, "会话超时，请重新登录！"));
			}
			Session session = redisOperationsSessionRepository.getSession(reqMap.get("token") + "");
			if (session == null) {
				return objectMapper.writeValueAsString(new BaseRespVO(97, "会话超时，请重新登录！"));
			}

			// 将用户名传入参数列表中
			reqMap.put(Constants.SESSION_LOGIN_USER, session.getAttribute(Constants.SESSION_LOGIN_USER));
			// 将客户端IP传入参数列表中
			reqMap.put(Constants.REQUEST_CLIENT_IP, Utils.getIpAddr(request));

			String service = "tsysInstanceContent";
			String method = "listNewMessage";
			resp = (InterfaceRespVO) routeService.webProcess(mode, service, method, reqMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return objectMapper.writeValueAsString(new BaseRespVO(99, e.getMessage()));
		}
		logger.info("=================返回http响应：{}=================");
		if (resp.getState() != 0) {
			logger.error(resp + "");
			return objectMapper.writeValueAsString(new BaseRespVO(99, resp.getMsg()));
		}
		return resp.getRespData();
	}

}
