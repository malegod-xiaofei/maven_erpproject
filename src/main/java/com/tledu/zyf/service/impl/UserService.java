package com.tledu.zyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.zyf.dao.IGroupDao;
import com.tledu.zyf.dao.IUserDao;
import com.tledu.zyf.model.User;
import com.tledu.zyf.service.IUserService;
import com.tledu.zyf.util.ERPException;
import com.tledu.zyf.util.Pager;

/**
 * @ Component 最普通的组件，可以被注入到spring容器进行管理
 * 
 * @ Repository 作用于持久层
 * 
 * @ Service 作用于业务逻辑层
 * 
 * @ Controller 作用于表现层（spring-mvc的注解）
 */
@Service
public class UserService implements IUserService {

	/**
	 * 从spring容器中,找到一个和这个属性数据类型匹配的实例化对象注入进来
	 * 
	 * 默认使用byType,根据类型匹配,如果只能找到一个这个数据类型的对象的时候,肯定没问题,但是如果找到了多个同一个类型的对象的时候,
	 * 就会自动更改为byName来进行匹配,根据set方法对应的参数列表的局部变量名来匹配
	 */
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IGroupDao groupDao;
	@Override
	public User login(User user) throws ERPException {
		// 根据用户名查询,数据库是否有数据
		User oldUser = userDao.loadByUsername(user.getUsername());
		// 如果没有 说明用户名不存在
		if (oldUser == null) {
			// throw 抛异常 会终止程序生命周期执行
			throw new ERPException("用户名不存在");
		}
		// 如果有 就比较密码
		// 不一致 说明密码不正确
		if (!oldUser.getPassword().equals(user.getPassword())) {
			throw new ERPException("密码不正确");
		}

		// 一致 说明登陆成功
		return oldUser;
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public boolean verifyUsername(String username) {
		User user = userDao.loadByUsername(username);
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> vagueUsername(String username) {
		return userDao.vagueUsername(username);
	}

	@Override
	public Pager<User> find(int page, int limit) {
		return userDao.find(page, limit);
	}
}
