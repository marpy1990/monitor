package sjtu.cit.monitor.cep.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.cep.dal.dao.CommonDao;
import sjtu.cit.monitor.cep.dal.dao.SourceViewDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class SourceViewDaoImpl extends SqlSessionDaoSupport implements SourceViewDao {

	private static final String NAMESPACE = "sjtu.cit.monitor.cep.dal.dao.SourceViewDao";
	
	private static final String TABLE_NAME = "SOURCE_VIEW";

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public List<Source> getSourceList(SelectQuery query) {
		query.table(TABLE_NAME);
		return this.getSqlSession().selectList(NAMESPACE + ".getSourceList", query);
	}

	@Override
	public List<Map<String, Object>> getList(SelectQuery query) {
		query.table(TABLE_NAME);
		return commonDao.getList(query);
	}

	@Override
	public int count(SelectQuery query) {
		query.table(TABLE_NAME);
		return commonDao.count(query);
	}

	@Override
	public Map<String, Object> get(SelectQuery query) {
		query.table(TABLE_NAME);
		return commonDao.get(query);
	}

	@Override
	public boolean insert(InsertQuery query) {
		query.table(TABLE_NAME);
		return commonDao.insert(query);
	}

	@Override
	public int update(UpdateQuery query) {
		query.table(TABLE_NAME);
		return commonDao.update(query);
	}

	@Override
	public int delete(DeleteQuery query) {
		query.table(TABLE_NAME);
		return commonDao.delete(query);
	}
	
}
