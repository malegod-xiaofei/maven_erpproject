package com.tledu.zyf.service;

import java.util.List;

import com.tledu.zyf.model.Group;
import com.tledu.zyf.util.ERPException;

public interface IGroupService {
	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	public List<Group> list();

	/**
	 * 添加部门 , 部门名不能重复
	 * 
	 * @param user
	 */
	public void add(Group group);

	/**
	 * 校验部门名是否存在
	 * 
	 * @param usernameString
	 * @return
	 */
	public boolean verifyGroupname(String groupname);

	/**
	 * 根据 id 删除部门 , 如果该部门下有员工,则不能删除该部门
	 * 
	 * @param id
	 */
	public void delete(int id) throws ERPException;

	/**
	 * 根据 id 查询部门
	 * 
	 * @param id
	 * @return
	 */
	public Group load(int id);

	/**
	 * 更新数据库中的部门信息
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
	public List<Group> vagueUsername(String groupname);
}
