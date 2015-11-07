package sjtu.cit.monitor.dal.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.dal.dao.SourceAliasDao;
import sjtu.cit.monitor.dal.entity.SourceAlias;

public class SourceAliasDaoImpl extends SqlSessionDaoSupport implements
		SourceAliasDao {

	private static final String NAMESPACE = "sjtu.cit.monitor.dal.dao.SourceAliasDao";

	@Override
	public SourceAlias get(int sourceId, int spaceId) {
		Map<String, Integer> query = new HashMap<String, Integer>();
		query.put("sourceId", sourceId);
		query.put("spaceId", spaceId);
		SourceAlias sourceAlias = this.getSqlSession().selectOne(
				NAMESPACE + ".select", query);
		return sourceAlias;
	}

	@Override
	public int insert(SourceAlias sourceAlias) {
		return this.getSqlSession().insert(NAMESPACE + ".insert", sourceAlias);
	}

	@Override
	public int update(SourceAlias sourceAlias) {
		return this.getSqlSession().update(NAMESPACE + ".update", sourceAlias);
	}

}
