package com.ms.taojin.pre.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CommonFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("P3P", "CP=CAO PSA OUR");
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		httpResponse.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		httpResponse.addHeader("X-Powered-By", " 3.2.1");
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json");
		
		chain.doFilter(request, httpResponse);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
