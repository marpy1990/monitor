package sjtu.cit.monitor.dal.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.dal.dao.SourceTreeStateDao;
import sjtu.cit.monitor.dal.entity.SourceNode;

public class SourceTreeStateDaoImpl extends SqlSessionDaoSupport implements
		SourceTreeStateDao {

	private static final String NAMESPACE = "sjtu.cit.monitor.dal.dao.SourceTreeStateDao";

	@Override
	public boolean isOpen(SourceNode node) {
		Boolean isOpen = this.getSqlSession().selectOne(NAMESPACE + ".isOpen",
				node);
		return null == isOpen ? false : isOpen;
	}

	@Override
	public void updateState(SourceNode node) {
		int count = this.getSqlSession().selectOne(NAMESPACE + ".count", node);
		if (count == 0)
			this.getSqlSession().insert(NAMESPACE + ".insert", node);
		else
			this.getSqlSession().update(NAMESPACE + ".update", node);
	}

}
