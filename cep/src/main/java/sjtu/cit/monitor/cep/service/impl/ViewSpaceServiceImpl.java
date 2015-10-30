package sjtu.cit.monitor.cep.service.impl;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.cep.dal.dao.SourceViewDao;
import sjtu.cit.monitor.cep.dal.dao.ViewSpaceDao;
import sjtu.cit.monitor.cep.dal.query.DeleteQuery;
import sjtu.cit.monitor.cep.dal.query.InsertQuery;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;
import sjtu.cit.monitor.cep.dal.query.UpdateQuery;

public class ViewSpaceServiceImpl implements ViewSpaceService {

	@Autowired
	private ViewSpaceDao viewSpaceDao;

	@Autowired
	private SourceViewDao sourceViewDao;

	@Override
	public List<ViewSpace> getViewSpaces() {
		return viewSpaceDao.getList(new SelectQuery());
	}

	@Override
	@Transactional
	public ViewSpace addViewSpace(String viewSpaceName) {
		boolean success = viewSpaceDao.insert(new InsertQuery().value("NAME",
				viewSpaceName));
		if (!success)
			return null;
		Integer id = viewSpaceDao.getMaxId();
		ViewSpace space = new ViewSpace();
		space.setId(id);
		space.setName(viewSpaceName);
		return space;
	}

	@Override
	public void removeNameSpace(int spaceId) {
		viewSpaceDao.delete(new DeleteQuery().where("ID", spaceId));

	}

	@Override
	public void updateNameSpace(ViewSpace space) {
		int count = viewSpaceDao.update(new UpdateQuery().set("NAME",
				space.getName()).where("ID", space.getId()));
		if (count == 0) {
			viewSpaceDao.insert(new InsertQuery().value("ID", space.getId())
					.value("NAME", space.getName()));
		}
	}

	@Override
	public List<Source> getAdjacentSources(int sourceId, int spaceId) {
		return sourceViewDao.getSourceList(new SelectQuery().where("FROMID",
				sourceId).where("SPACEID", spaceId));
	}

	@Override
	public int countAdjacentSources(int sourceId, int spaceId) {
		return sourceViewDao.count(new SelectQuery().where("FROMID", sourceId)
				.where("SPACEID", spaceId));
	}

	@Override
	public List<Source> getSourcesAdjacentTo(int sourceId, int spaceId) {
		return sourceViewDao.getSourceList(new SelectQuery().where("TOID",
				sourceId).where("SPACEID", spaceId));
	}

	@Override
	public int countSourcesAdjacentTo(int sourceId, int spaceId) {
		return sourceViewDao.count(new SelectQuery().where("TOID", sourceId)
				.where("SPACEID", spaceId));
	}

	@Override
	public boolean existsPath(int fromSourceId, int toSourceId, int spaceId) {
		Set<Integer> visited = new HashSet<Integer>();
		Queue<Integer> current = new ArrayDeque<Integer>();
		current.offer(fromSourceId);
		while (!current.isEmpty()) {
			Integer dest = current.poll();
			if (dest == toSourceId)
				return true;
			for (Source source : this.getAdjacentSources(dest, spaceId)) {
				Integer id = source.getId();
				if (!visited.contains(id)) {
					current.offer(id);
					visited.add(id);
				}
			}
		}
		return false;
	}

	@Override
	public void addEdge(int fromSourceId, int toSourceId, int spaceId) {
		int count = sourceViewDao.update(new UpdateQuery()
				.set("FROMID", fromSourceId).set("TOID", toSourceId)
				.set("SPACEID", spaceId).set("EDGE", true));
		if (count == 0)
			sourceViewDao.insert(new InsertQuery()
					.value("FROMID", fromSourceId).value("TOID", toSourceId)
					.value("SPACEID", spaceId).value("EDGE", true));

	}

	@Override
	public void removeEdge(int fromSourceId, int toSourceId, int spaceId) {
		sourceViewDao.update(new UpdateQuery().set("FROMID", fromSourceId)
				.set("TOID", toSourceId).set("SPACEID", spaceId)
				.set("EDGE", false));
	}

}
