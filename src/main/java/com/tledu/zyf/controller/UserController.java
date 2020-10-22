package com.tledu.zyf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tledu.zyf.model.Group;
import com.tledu.zyf.model.User;
import com.tledu.zyf.service.IUserService;
import com.tledu.zyf.util.ERPException;

/**
 * 使用Controller注解之后,在方法上可以通过return的jsp或者html页面的名字,通过视图解析器,就能跳转到指定页面
 * 如果没有Controller注解,这个类中的方法是不会被请求过去的 所对应的层也是controller层,表现层
 * 
 * @author yingfing
 * @date 2020年10月17日 @time 下午2:45:31
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 从spring容器中,找到一个和这个属性数据类型匹配的实例化对象注入进来
	 * 
	 * 默认使用byType,根据类型匹配,如果只能找到一个这个数据类型的对象的时候,肯定没问题,但是如果找到了多个同一个类型的对象的时候,
	 * 就会自动更改为byName来进行匹配,根据set方法对应的参数列表的局部变量名来匹配
	 */
	@Autowired
	private IUserService userService;

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {

		// 获取传递的数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		Group group = null;
		// 如果为 0 ,说明中没有部门
		if (group_id != 0) {
			group = new Group();
			group.setId(group_id);
		}
		User user = new User(username, password, nickname, sex, age);
		user.setGroup(group);
		// System.out.println(user);
		userService.add(user);
		return "user/add";
	}

	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {

		// // 获取登录信息
		// User loginUser = (User) session.getAttribute("loginUser");
		// // 未登录 跳转到登录页面
		// if (loginUser == null) {
		// return "redirect:/";
		// }

		// 如果登录,获取所有的用户,传递到页面
		List<User> users = userService.list();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping("/addInput")
	public String addInput() {
		return "user/add";
	}

	@RequestMapping(value = "/addInput", method = RequestMethod.POST)
	public String addInput(@PathVariable User user) throws ERPException {
		userService.add(user);
		return "redirect:/user/list";
	}

	// @PathVariable : 接收 REST风格传参
	// Spring会对@PathVariable注解的变量进行自动赋值
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/user/list";
	}

	// @PathVariable : 接收 REST风格传参
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		User user = userService.load(id);
		model.addAttribute("user", user);
		return "user/update";
	}

	// @Validated : 用于数据校验
	@RequestMapping(value = "/update/{user}", method = RequestMethod.POST)
	public String update(@Validated User user) {
		userService.update(user);
		return "redirect:/user/list";
	}
}
