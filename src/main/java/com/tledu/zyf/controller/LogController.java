package com.tledu.zyf.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.zyf.model.User;
import com.tledu.zyf.service.IUserService;
import com.tledu.zyf.util.AjaxObj;
import com.tledu.zyf.util.ERPException;

/**
 * 使用Controller注解之后,在方法上可以通过return的jsp或者html页面的名字,通过视图解析器,就能跳转到指定页面
 * 如果没有Controller注解,这个类中的方法是不会被请求过去的 所对应的层也是controller层,表现层
 */
@Controller
public class LogController {

	/**
	 * 从spring容器中,找到一个和这个属性数据类型匹配的实例化对象注入进来
	 * 
	 * 默认使用byType,根据类型匹配,如果只能找到一个这个数据类型的对象的时候,肯定没问题,但是如果找到了多个同一个类型的对象的时候,
	 * 就会自动更改为byName来进行匹配,根据set方法对应的参数列表的局部变量名来匹配
	 */
	@Autowired
	private IUserService userService;

	/**
	 * 
	 * 和页面响应数据的话,可以使用原始的方式,通过 request
	 * 
	 * Model/ModelMap,专门用于向页面传递数据 如果是一个参数,会默认使用该数据的数据类型的首字母小写作为 key
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);

		try {
			// 登录成功 返回对象
			User loginUser = userService.login(user);
			// 向页面传参
			model.addAttribute("loginUser", loginUser);
			// pw.println(new AjaxObj(1, "登陆成功").toJSON());
			return new AjaxObj(1, "登陆成功").toJSON();
		} catch (ERPException e) {
			// 能到这里 说明登录失败
			String msg = e.getMessage();
			return new AjaxObj(0, msg).toJSON();
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 删除 session 中的登录信息,并返回到登录页面
		request.getSession().invalidate();
		// 登录页面
		// 重定向跳转
		return "redirect:/login.jsp";
	}

}
