package com.ms.taojin.pre.interceptor;
//package com.ms.center.pre.interceptor;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ms.center.common.utils.StringUtils;
//
//public class DownloadFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		String downloadFileName = request.getParameter("downloadFileName");
//		if (!StringUtils.isEmpty(downloadFileName)) {
//			httpResponse.addHeader("Content-Disposition", "attachment;filename=\"" + downloadFileName + "\"");
//		}
//
//		chain.doFilter(request, httpResponse);
//		return;
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}
