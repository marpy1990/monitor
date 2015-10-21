package sjtu.cit.monitor.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.biz.UserManager;
import sjtu.cit.monitor.dal.dao.UserDao;
import sjtu.cit.monitor.dal.entity.User;

public class UserManagerImpl implements UserManager{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getCurrentUser() {
		return userDao.getUser(0);
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
