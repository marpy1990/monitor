package sjtu.cit.monitor.cep.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.cep.dal.dao.CommonDao;
import sjtu.cit.monitor.cep.dal.dao.ViewSpaceDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class ViewSpaceDaoImpl implements ViewSpaceDao{
	
	private static final String TABLE_NAME = "VIEWSPACE";

	@Autowired
	private CommonDao commonDao;

	@Override
	public List<ViewSpace> getList(SelectQuery query) {
		List<ViewSpace> lst = new ArrayList<ViewSpace>();
		query.table(TABLE_NAME);
		for (Map<String, Object> item : commonDao.getList(query)) {
			ViewSpace space = new ViewSpace();
			space.setId((Integer) item.get("ID"));
			space.setName((String) item.get("NAME"));
			lst.add(space);
		}
		return lst;
	}

	@Override
	public ViewSpace get(SelectQuery query) {
		query.table(TABLE_NAME);
		Map<String, Object> item = commonDao.get(query);
		ViewSpace space = new ViewSpace();
		space.setId((Integer) item.get("ID"));
		space.setName((String) item.get("NAME"));
		return space;
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
