package com.tledu.zyf.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tledu.zyf.model.User;
import com.tledu.zyf.util.HttpFilter;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {
	// 保存每一个不需要拦截的请求
	private List<String> paths = null;

	@Override
	public void init() throws ServletException {
		// 获取不需要拦截的请求
		String filterPath = getServletContext().getInitParameter("filterPath");
		// 如果不为空 就以逗号分割,得到字符串数组(数组中是每一个不需要进行登录校验的路由)
		if (filterPath != null) {
			// 数组
			String[] pathS = filterPath.split(",");
			// 转换为 list
			paths = Arrays.asList(pathS);
		}
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * 获取当前请求的路由,判断是否在不需要拦截的集合中,如果在,直接放行
		 * 
		 * 如果不在,就判断 session 中是否已经登录,如果未登录,返回登录页面,如果已登录,放行
		 */
//		System.out.println(request.getServletPath());
		// 获取当前请求的路由
		String path = request.getServletPath();
		// 判断集合中是否包含当前请求的路由,不包含就需要进行登录认证
		// path 中如果是以 /resources 打头 说明我们要 请求的是 css 或者是 js 直接放行
		// startsWith 判断一个字符串中是否以指定的字符串打头
		if (!paths.contains(path) && !path.startsWith("/resources")) {
			// /login /login.jsp /resources
			// 到这里说明需要进行登录验证
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");
			if (user == null) {
				// getContextPath : /项目名
				// 因为重定向的绝对定位默认到 8080 (webapps)
				// response.sendRedirect(request.getContextPath()+"/");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return;
			}
		}

		// 能执行到这里,说明,要么不拦截就可以访问,要么就是登录了
		chain.doFilter(request, response);
	}
}
