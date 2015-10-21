package sjtu.cit.monitor.biz;

import sjtu.cit.monitor.dal.entity.User;

public interface UserManager {
	
	public User getCurrentUser();
	
	public boolean register(User user);
	
}
