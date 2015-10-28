package sjtu.cit.monitor.cep.dal.dao;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;

public interface SourceViewDao {
	
	public List<Source> getSourceList(SelectQuery query);
	
}
