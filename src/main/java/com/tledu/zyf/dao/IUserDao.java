package com.tledu.zyf.dao;

import java.util.List;

import com.tledu.zyf.model.User;
import com.tledu.zyf.util.Pager;

public interface IUserDao {
	/**
	 * 根据用户名,查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);

	/**
	 * 模糊查询 根据用户名,查询用户
	 * 
	 * @param username
	 * @return
	 */
	public List<User> vagueUsername(String username);

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
	 * 添加用户,用户名不能重复
	 * 
	 * @param user
	 */
	public void add(User user);

	/**
	 * 根据 ID 查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User load(int id);

	/**
	 * 更新用户数据
	 * 
	 * @param user
	 */
	public void update(User user);

	/**
	 * 根据部门 ID 查询,该部门下有多少人
	 * 
	 * @param groupId
	 * @return
	 */
	public int loadByGroupId(int groupId);

	/**
	 * 模糊查询,将结果分页展示
	 * 
	 * @param page  要分开的页数
	 * @param limit 每页多少条数据
	 * @return
	 */
	public Pager<User> find(int page, int limit);
}
