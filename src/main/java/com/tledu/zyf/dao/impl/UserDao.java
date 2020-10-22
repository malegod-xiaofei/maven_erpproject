package com.tledu.zyf.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tledu.zyf.dao.IUserDao;
import com.tledu.zyf.model.User;
import com.tledu.zyf.util.MyBatisUtil;
import com.tledu.zyf.util.Pager;

/**
 * Repository : 注解可以标记在任何的类上
 * 
 * 用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
 */

@Repository
public class UserDao extends SqlSessionDaoSupport implements IUserDao {

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
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void add(User user) {
		getSqlSession().getMapper(IUserDao.class).add(user);
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IUserDao.class).delete(id);
	}

	@Override
	public void update(User user) {
		getSqlSession().getMapper(IUserDao.class).update(user);
	}

	@Override
	public User loadByUsername(String username) {
		return getSqlSession().getMapper(IUserDao.class).loadByUsername(
				username);
	}

	@Override
	public List<User> list() {
		return getSqlSession().getMapper(IUserDao.class).list();
	}

	@Override
	public User load(int id) {
		return getSqlSession().getMapper(IUserDao.class).load(id);
	}

	@Override
	public List<User> vagueUsername(String username) {
		return getSqlSession().getMapper(IUserDao.class)
				.vagueUsername(username);
	}

//	public List<User> vagueUsername(String name, String username) {
//		SqlSession session = null;
//		List<User> users = null;
//		Map<String, Object> params = new HashMap<String, Object>();
//		try {
//			session = MyBatisUtil.getSession();
//			params.put("username", "%" + username + "%");
//			users = session.selectList(name, params);
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			MyBatisUtil.closeSession(session);
//		}
//		return users;
//	}

	@Override
	public int loadByGroupId(int groupId) {
		return loadByGroupId(User.class.getName() + ".loadByGroupId", groupId);
	}

	public int loadByGroupId(String name, int groupId) {
		SqlSession session = null;
		int count = 0;
		try {
			session = MyBatisUtil.getSession();
			count = session.selectOne(name, groupId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return count;

	}

	@Override
	public Pager<User> find(int page, int limit) {
		return find(User.class.getName() + ".find", page, limit);
	}

	public Pager<User> find(String name, int page, int limit) {
		Pager<User> pager = new Pager<User>();
		List<User> users = new ArrayList<User>();

		// 存放传入的两个参数
		Map<String, Object> params = new HashMap<String, Object>();

		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			// 设置传入的两个参数
			if (page == 0) {
				// 如果页码为 0 ,那么起始值就是0
				params.put("pageOffset", 0);
				params.put("pageSize", limit);
			} else {
				params.put("pageOffset", (page - 1) * limit);
				params.put("pageSize", limit);
			}
			users = session.selectList(name, params);
			session.commit();

			// 把 list 设置到 pager 中
			pager.setData(users);

			// 查询总条数 返回一个 int 类型的值 直接设置到 pager 中
			int count = session.selectOne(name + "_count");
			pager.setCount(count);
			session.commit();
			return pager;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return null;
	}

}
