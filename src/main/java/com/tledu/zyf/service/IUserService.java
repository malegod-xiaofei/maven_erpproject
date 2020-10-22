package com.tledu.zyf.service;

import java.util.List;

import com.tledu.zyf.model.User;
import com.tledu.zyf.util.ERPException;
import com.tledu.zyf.util.Pager;

public interface IUserService {
	/**
	 * 登录方法
	 * 
	 * @param user
	 * @return
	 * @throws ERPException
	 */
	public User login(User user) throws ERPException;

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> list();

	/**
	 * 根据 id 删除用户
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 添加用户 , 用户名不能重复
	 * 
	 * @param user
	 */
	public void add(User user);

	/**
	 * 校验用户名是否存在
	 * 
	 * @param usernameString
	 * @return
	 */
	public boolean verifyUsername(String username);

	/**
	 * 根据 id 查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User load(int id);

	/**
	 * 更新数据库中的用户信息
	 * 
	 * @param user
	 */
	public void update(User user);

	/**
	 * 模糊查询 , 根据用户名,查询用户
	 * 
	 * @param username
	 * @return
	 */
	public List<User> vagueUsername(String username);

	/**
	 * 模糊查询,将结果分页展示
	 * 
	 * @param page  要分开的页数
	 * @param limit 每页多少条数据
	 * @return
	 */
	public Pager<User> find(int page, int limit);

}
