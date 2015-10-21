package sjtu.cit.monitor.dal.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.dal.dao.UserDao;
import sjtu.cit.monitor.dal.entity.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
	
	private static final String NAMESPACE = "sjtu.cit.monitor.dal.dao.UserDao";

	@Override
	public User getUser(int userId) {
		User user = this.getSqlSession().selectOne(NAMESPACE+".getUser", userId);
		return user;
	}

}
