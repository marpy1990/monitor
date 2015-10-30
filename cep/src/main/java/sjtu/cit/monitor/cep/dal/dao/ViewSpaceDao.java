package sjtu.cit.monitor.cep.dal.dao;

import sjtu.cit.monitor.api.cep.entity.ViewSpace;

public interface ViewSpaceDao extends BasicDao<ViewSpace>{

	public Integer getMaxId();
	
}
