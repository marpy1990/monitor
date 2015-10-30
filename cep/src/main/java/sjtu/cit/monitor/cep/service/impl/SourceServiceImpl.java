package sjtu.cit.monitor.cep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.cep.dal.dao.SourceDao;
import sjtu.cit.monitor.cep.dal.dao.SourceViewDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class SourceServiceImpl implements SourceService {

	@Autowired
	private SourceDao sourceDao;
	
	@Autowired
	private SourceViewDao sourceViewDao;

	@Override
	public Source getSource(int sourceId) {
		return sourceDao.get(new SelectQuery().where("ID", sourceId));
	}

	@Override
	@Transactional
	public Source addSource(String sourceName) {
		boolean success = sourceDao.insert(new InsertQuery().value("NAME",
				sourceName));
		if (!success)
			return null;
		Integer id = sourceDao.getMaxId();
		Source source = new Source();
		source.setId(id);
		source.setName(sourceName);
		return source;
	}

	@Override
	public void removeSource(int sourceId) {
		sourceViewDao.update(new UpdateQuery().set("EDGE", false).where("FROMID", sourceId));
		sourceViewDao.update(new UpdateQuery().set("EDGE", false).where("TOID", sourceId));
	}

	@Override
	public void updateSource(Source source) {
		int count = sourceDao.update(new UpdateQuery().set("NAME",
				source.getName()).where("ID", source.getId()));
		if (count == 0) {
			sourceDao.insert(new InsertQuery().value("ID", source.getId())
					.value("NAME", source.getName()));
		}
	}

}
