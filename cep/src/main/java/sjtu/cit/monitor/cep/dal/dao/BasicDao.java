package sjtu.cit.monitor.cep.dal.dao;

import java.util.List;

import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public interface BasicDao<DO> {
	
	public List<DO> getList(SelectQuery query);
	
	public int count(SelectQuery query);
	
	public DO get(SelectQuery query);
	
	public boolean insert(InsertQuery query);
	
	public int update(UpdateQuery query);
	
	public int delete(DeleteQuery query);
	
}
