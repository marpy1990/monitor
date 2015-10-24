package sjtu.cit.monitor.dal.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.dal.dao.SourceTreeStateDao;
import sjtu.cit.monitor.dal.entity.SourceNode;
import sjtu.cit.monitor.dal.entity.TreeNode;

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
	public void updateNodeState(SourceNode node) {
		int count = this.getSqlSession().selectOne(NAMESPACE + ".countNode", node);
		if (count == 0)
			this.getSqlSession().insert(NAMESPACE + ".insertNode", node);
		else
			this.getSqlSession().update(NAMESPACE + ".updateNode", node);
	}

	@Override
	public int getCurrentSpaceId(TreeNode tree) {
		Integer spaceId = this.getSqlSession().selectOne(NAMESPACE + ".getCurrentSpaceId", tree);
		return null == spaceId ? 0 : spaceId;
	}

	@Override
	public void setCurrentSpaceId(TreeNode tree) {
		this.getSqlSession().selectOne(NAMESPACE + ".setCurrentSpaceId", tree);
	}

}
