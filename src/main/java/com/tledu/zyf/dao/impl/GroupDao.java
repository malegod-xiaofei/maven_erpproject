package com.tledu.zyf.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tledu.zyf.dao.IGroupDao;
import com.tledu.zyf.model.Group;
import com.tledu.zyf.util.MyBatisUtil;

/**
 * Repository : 注解可以标记在任何的类上
 * 
 * 用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
 */
@Repository
public class GroupDao extends SqlSessionDaoSupport implements IGroupDao {

	/**
	 * Resource这个注解是javaEE的,在javax包下,所以不需要导入其他jar包
	 * 
	 * @ Resource默认使用byName的方式,按照名字匹配,可以写在setter方法上也可以写在变量上
	 * 
	 * 先匹配set方法的名字,匹配不上再匹配方法参数列表的名字
	 * 
	 * 如果还是匹配不上就会转换为byType,根据类型匹配
	 * 
	 * 当然我们也可以指定名字 @Resource(name=”userDao”)
	 * 
	 * 就相当于 Autowired和Qualifier 一起使用
	 * 
	 * public void setUserDao(@Qualifier(“userDao2”)IUserDao userDao){};
	 */
	@Resource
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void add(Group group) {
		getSqlSession().getMapper(IGroupDao.class).add(group);
	}

	@Override
	public void update(Group group) {
		getSqlSession().getMapper(IGroupDao.class).update(group);
	}

	@Override
	public List<Group> list() {
		return getSqlSession().getMapper(IGroupDao.class).list();
	}

	@Override
	public Group loadByGroupname(String groupname) {
		return loadByGroupname(Group.class.getName() + ".loadByGroupname",
				groupname);
	}

	public Group loadByGroupname(String name, String groupname) {
		SqlSession session = null;
		Group group = null;
		try {
			session = MyBatisUtil.getSession();
			group = session.selectOne(name, groupname);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return group;
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IGroupDao.class).delete(id);
	}

	@Override
	public Group load(int id) {
		return getSqlSession().getMapper(IGroupDao.class).load(id);
	}

	@Override
	public List<Group> vagueGroupname(String groupname) {
		return vagueGroupname(Group.class.getName() + ".vagueGroupname",
				groupname);
	}

	public List<Group> vagueGroupname(String name, String groupname) {
		SqlSession session = null;
		List<Group> groups = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			session = MyBatisUtil.getSession();
			params.put("groupname", "%" + groupname + "%");
			groups = session.selectList(name, params);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return groups;
	}

}
