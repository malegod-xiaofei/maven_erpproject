package com.tledu.zyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.zyf.dao.IGroupDao;
import com.tledu.zyf.dao.IUserDao;
import com.tledu.zyf.model.Group;
import com.tledu.zyf.service.IGroupService;
import com.tledu.zyf.util.ERPException;

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
public class GroupService implements IGroupService {
	/**
	 * 从spring容器中,找到一个和这个属性数据类型匹配的实例化对象注入进来
	 * 
	 * 默认使用byType,根据类型匹配,如果只能找到一个这个数据类型的对象的时候,肯定没问题,但是如果找到了多个同一个类型的对象的时候,
	 * 就会自动更改为byName来进行匹配,根据set方法对应的参数列表的局部变量名来匹配
	 */
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public List<Group> list() {
		return groupDao.list();
	}

	@Override
	public void add(Group group) {
		groupDao.add(group);
	}

	@Override
	public boolean verifyGroupname(String groupname) {
		Group group = groupDao.loadByGroupname(groupname);
		if (group == null) {
			return false;
		}
		return false;
	}

	@Override
	public void delete(int id) throws ERPException {
		// 根据部门 ID 去 t_user 表中进行查询 count(*) 如果大于 0 ,说明该部门下还有员工
		int count = userDao.loadByGroupId(id);
		if (count > 0) {
			throw new ERPException("该部门下还有员工,不能删除哦~");
		}
		groupDao.delete(id);
	}

	@Override
	public Group load(int id) {
		return groupDao.load(id);
	}

	@Override
	public void update(Group group) {
		groupDao.update(group);
	}

	@Override
	public List<Group> vagueUsername(String groupname) {
		return groupDao.vagueGroupname(groupname);
	}

}
