package com.ms.taojin.pre.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.taojin.common.Constants;
import com.ms.taojin.common.api.IEncryptService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.pre.utils.Utils;

@Controller
@RequestMapping("interface")
public class ControllerEncrypt {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "encryptServiceMap")
	private Map<String, IEncryptService> encryptServiceMap;

	private static ObjectMapper objectMapper = new ObjectMapper();

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
	@RequestMapping(value = "{mode}/{service}/{method}")
	@ResponseBody
	public String web(@PathVariable("mode") String mode, @PathVariable("service") String service, @PathVariable("method") String method,
	        HttpServletRequest request) throws JsonProcessingException {
		logger.info("=================ControllerEncrypt收到http请求：调用模块：{}，服务：{}，方法：{}=================", mode, service, method);

		String resp = null;
		try {
			// 根据模块名判断本地是否存在该渠道实现
			IEncryptService encryptService = encryptServiceMap.get(mode);
			if (encryptService == null) {
				logger.error("没有配置该模块路由信息，模块：【" + mode + "】");
				return objectMapper.writeValueAsString(new BaseRespVO(8, "没有配置该模块路由信息，模块：【" + mode + "】"));
			}

			Map<String, Object> reqMap = Utils.parameterMap(request);
			// 将客户端IP传入参数列表中
			reqMap.put(Constants.REQUEST_CLIENT_IP, Utils.getIpAddr(request));

			resp = encryptService.encryptProcess(mode, service, method, reqMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return objectMapper.writeValueAsString(new BaseRespVO(99, e.getMessage()));
		}
		logger.info("=================返回http响应：{}================={}", resp);
		return resp;
	}

}
