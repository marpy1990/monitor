package sjtu.cit.monitor.cep.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.cep.dal.dao.CommonDao;
import sjtu.cit.monitor.cep.dal.dao.SourceDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class SourceDaoImpl implements SourceDao {

	private static final String TABLE_NAME = "SOURCE";

	@Autowired
	private CommonDao commonDao;

	@Override
	public List<Source> getList(SelectQuery query) {
		List<Source> lst = new ArrayList<Source>();
		query.table(TABLE_NAME);
		for (Map<String, Object> item : commonDao.getList(query)) {
			Source source = new Source();
			source.setId((Integer) item.get("ID"));
			source.setName((String) item.get("NAME"));
			lst.add(source);
		}
		return lst;
	}

	@Override
	public Source get(SelectQuery query) {
		query.table(TABLE_NAME);
		Map<String, Object> item = commonDao.get(query);
		Source source = new Source();
		source.setId((Integer) item.get("ID"));
		source.setName((String) item.get("NAME"));
		return source;
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

	@Override
	public Integer getMaxId() {
		SelectQuery query = new SelectQuery().columns("max(ID)").limit(1).table(TABLE_NAME);
		return (Integer) commonDao.get(query).get("max(ID)");
	}

	@Override
	public int count(SelectQuery query) {
		query.table(TABLE_NAME);
		return commonDao.count(query);
	}

}
