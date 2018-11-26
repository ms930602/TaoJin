package com.ms.taojin.pre.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.taojin.common.Constants;
import com.ms.taojin.common.api.IRouteService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.InterfaceRespVO;
import com.ms.taojin.pre.utils.Utils;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("api")
public class ControllerWeb {
	private  Logger  logger=LoggerFactory.getLogger(getClass());

//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "routeServiceMap")
	private Map<String, IRouteService> routeServiceMap;

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
		logger.info("========{}=========收到http请求：调用模块：{}，服务：{}，方法：{}=================", Utils.getIpAddr(request), mode, service, method);

		InterfaceRespVO resp = null;
		try {
			// 根据模块名判断本地是否存在该渠道实现
			IRouteService routeService = routeServiceMap.get(mode);
			if (routeService == null) {
				logger.error("没有配置该模块路由信息，模块：【" + mode + "】");
				return objectMapper.writeValueAsString(new BaseRespVO(8, "没有配置该模块路由信息，模块：【" + mode + "】"));
			}

			Map<String, Object> reqMap = Utils.parameterMap(request);
			// 将用户名传入参数列表中
			reqMap.put(Constants.SESSION_LOGIN_USER, request.getSession().getAttribute(Constants.SESSION_LOGIN_USER));
			// 将客户端IP传入参数列表中
			reqMap.put(Constants.REQUEST_CLIENT_IP, Utils.getIpAddr(request));
			reqMap.put(Constants.REQUEST_METHOD, request.getMethod());

			resp = (InterfaceRespVO) routeService.webProcess(mode, service, method, reqMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return objectMapper.writeValueAsString(new BaseRespVO(99, e.getMessage()));
		}
		logger.info("=================返回http响应：{}=================");
		if (!resp.isSuccess()) {
			logger.error(resp + "");
			return objectMapper.writeValueAsString(resp);
		}
		return resp.getRespData();
	}

	/**
	 * 文件上传入口
	 *
	 * @param mode
	 * @param service
	 * @param method
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{mode}/{service}/file/{method}")
	@ResponseBody
	public String upload(@PathVariable("mode") String mode, @PathVariable("service") String service, @PathVariable("method") String method,
	        HttpServletRequest request) throws JsonProcessingException {
		logger.info("======{}===========收到http文件上传请求：调用模块：{}，服务：{}，方法：{}=================", Utils.getIpAddr(request), mode, service, method);
		InterfaceRespVO resp = null;
		try {
			// 根据模块名判断本地是否存在该渠道实现
			IRouteService routeService = routeServiceMap.get(mode);
			if (routeService == null) {
				logger.error("没有配置该模块路由信息，模块：【" + mode + "】");
				return objectMapper.writeValueAsString(new BaseRespVO(8, "没有配置该模块路由信息，模块：【" + mode + "】"));
			}

			Map reqMap = Utils.parameterMap(request);
			loadFileByRequest(request, reqMap);
			resp = (InterfaceRespVO) routeService.webProcess(mode, service, method, reqMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return objectMapper.writeValueAsString(BaseRespVO.error(e.getMessage()));
		}
		logger.info("=================返回http响应：{}=================", resp.getRespData());
		if (!resp.isSuccess()) {
			logger.error(resp + "");
			return objectMapper.writeValueAsString(resp);
		}
		return resp.getRespData();
	}

	/**
	 * 提取上传文件
	 * 
	 * @param request
	 * @param map
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked" })
	private void loadFileByRequest(HttpServletRequest request, Map map) throws IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						map.put(((CommonsMultipartFile) file).getFileItem().getFieldName(), file.getInputStream());
						map.put(((CommonsMultipartFile) file).getFileItem().getFieldName() + "_FILENAME", myFileName);
						map.put(((CommonsMultipartFile) file).getFileItem().getFieldName() + "_CONTEXTTYPE", file.getContentType());
					}
				}
			}
		}
	}

}
