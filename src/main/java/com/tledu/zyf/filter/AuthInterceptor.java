package com.tledu.zyf.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 转换为HandlerMethod对象
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 获取请求的方法
		String methodName = handlerMethod.getMethod().getName();
		System.out.println(methodName);

		// 放行
		System.out.println(55555);
		return super.preHandle(request, response, handler);
	}
}
