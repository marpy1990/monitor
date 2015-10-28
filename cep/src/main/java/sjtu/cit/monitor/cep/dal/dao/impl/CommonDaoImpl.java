package sjtu.cit.monitor.cep.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.cep.dal.dao.CommonDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class CommonDaoImpl<DO> extends SqlSessionDaoSupport implements CommonDao{

	@Override
	public List<Map<String, Object>> getList(SelectQuery query) {
		return this.getSqlSession().selectList(
				this.getClass().getName() + ".select", query);
	}

	@Override
	public Map<String, Object> get(SelectQuery query) {
		return this.getSqlSession().selectOne(
				this.getClass().getName() + ".select", query);
	}

	@Override
	public boolean insert(InsertQuery query) {
		return this.getSqlSession().insert(
				this.getClass().getName() + ".insert", query) != 0;
	}

	@Override
	public int update(UpdateQuery query) {
		return this.getSqlSession().update(
				this.getClass().getName() + ".update", query);
	}

	@Override
	public int delete(DeleteQuery query) {
		return this.getSqlSession().delete(
				this.getClass().getName() + ".delete", query);
	}

}
