package com.tledu.zyf.dao;

import java.util.List;

import com.tledu.zyf.model.Group;

public interface IGroupDao {
	/**
	 * 根据部门名,查询部门
	 * 
	 * @param username
	 * @return
	 */
	public Group loadByGroupname(String groupname);

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	public List<Group> list();

	/**
	 * 根据 id 删除部门
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 添加部门,部门名不能重复
	 * 
	 * @param user
	 */
	public void add(Group group);

	/**
	 * 根据 ID 查询部门
	 * 
	 * @param id
	 * @return
	 */
	public Group load(int id);

	/**
	 * 更新部门数据
	 * 
	 * @param user
	 */
	public void update(Group group);

	/**
	 * 模糊查询 , 根据部门名,查询部门
	 * 
	 * @param username
	 * @return
	 */
	public List<Group> vagueGroupname(String groupname);
}