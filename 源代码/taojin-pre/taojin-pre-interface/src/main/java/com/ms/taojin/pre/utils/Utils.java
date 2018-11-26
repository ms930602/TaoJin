package com.ms.taojin.pre.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.taojin.common.utils.DateUtilSafe;
import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.common.vo.BaseRespVO;

@SuppressWarnings("rawtypes")
public class Utils {

	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
	}

	static {
		objectMapper.setDateFormat(DateUtilSafe.getSdf(DateUtilSafe.simple));
		// objectMapper.setSerializationInclusion(Include.NON_NULL);
		// objectMapper.setSerializationInclusion(Include.NON_EMPTY);
	}

	public static Map parameterMap(HttpServletRequest request) {
		Map<String, String[]> reqMap = request.getParameterMap();
		Map<String, Object> resultMap = new HashMap<String, Object>(reqMap.size());

		if (reqMap == null || reqMap.isEmpty()) {
			return resultMap;
		}
	    // 检测是否为上传请求
	    String contentType = request.getContentType();
	    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
	        MultipartHttpServletRequest multipartRequest =
	                WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
	        MultipartFile file = multipartRequest.getFile("file");   
	        try {
				resultMap.put("file", file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		for (Iterator<String> iter = reqMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			String[] value = reqMap.get(key);
			if (value != null && value.length == 1) {
				resultMap.put(key, value[0]);
			} else {
				resultMap.put(key, value);
			}
		}

		return resultMap;
	}

	public static Map parameterMapString(HttpServletRequest request) {
		final Map<String, String> returnMap = new HashMap<String, String>();
		String key = "";
		String value = "";
		Enumeration<?> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			key = names.nextElement() + "";
			value = request.getParameter(key);
			returnMap.put(key, value);
		}
		return returnMap;
	}

	public static void writeRespones(HttpServletResponse respones, BaseRespVO respVo) throws IOException {
		PrintWriter out = respones.getWriter();
		out.write(objectMapper.writeValueAsString(respVo));
	}

	public static void writeResponesByJsonp(HttpServletRequest request, HttpServletResponse respones, BaseRespVO respVo) throws IOException {
		PrintWriter out = respones.getWriter();
		String callback = request.getParameter("callback");
		String respJson = objectMapper.writeValueAsString(respVo);
		if (StringUtils.isEmpty(callback)) {
			out.write(respJson);
		} else {
			out.write(callback + "(" + respJson + ")");
		}

	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)， 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

}
