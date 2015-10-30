package sjtu.cit.monitor.cep.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.cep.dal.dao.CommonDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class CommonDaoImpl extends SqlSessionDaoSupport implements
		CommonDao {

	private static final String NAMESPACE = "sjtu.cit.monitor.cep.dal.dao.CommonDao";

	@Override
	public List<Map<String, Object>> getList(SelectQuery query) {
		return this.getSqlSession().selectList(NAMESPACE + ".select", query);
	}
	
	@Override
	public int count(SelectQuery query) {
		query.columns("count(*)").limit(1);
		Map<String, Integer>result =  this.getSqlSession().selectOne(NAMESPACE+".select", query);
		return result.get("count(*)");
	}

	@Override
	public Map<String, Object> get(SelectQuery query) {
		return this.getSqlSession().selectOne(NAMESPACE + ".select", query);
	}

	@Override
	public boolean insert(InsertQuery query) {
		return this.getSqlSession().insert(NAMESPACE + ".insert", query) != 0;
	}

	@Override
	public int update(UpdateQuery query) {
		return this.getSqlSession().update(NAMESPACE + ".update", query);
	}

	@Override
	public int delete(DeleteQuery query) {
		return this.getSqlSession().delete(NAMESPACE + ".delete", query);
	}

}
