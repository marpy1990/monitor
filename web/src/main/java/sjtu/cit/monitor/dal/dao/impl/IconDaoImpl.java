package sjtu.cit.monitor.dal.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import sjtu.cit.monitor.dal.dao.IconDao;
import sjtu.cit.monitor.dal.entity.Feature;

public class IconDaoImpl extends SqlSessionDaoSupport implements IconDao {

	private static final String NAMESPACE = "sjtu.cit.monitor.dal.dao.IconDao";

	private String querySourceTypeIcon(int sourceTypeId, int feature) {
		Map<String, Integer> query = new HashMap<String, Integer>();
		query.put("sourceTypeId", sourceTypeId);
		query.put("feature", feature);
		String url = this.getSqlSession().selectOne(
				NAMESPACE + ".getSourceTypeIcon", query);
		if (null == url) {
			query.put("sourceTypeId", -1);
			url = this.getSqlSession().selectOne(
					NAMESPACE + ".getSourceTypeIcon", query);
		}
		return url;
	}

	@Override
	public String getSourceTreeIcon(int sourceTypeId, int feature) {
		//size_flag位清0, 保证出16x16的小图
		return querySourceTypeIcon(sourceTypeId, feature&~Feature.SIZE_LARGE);
	}

	@Override
	public String getSourceTreeIcon(int sourceTypeId) {
		return querySourceTypeIcon(sourceTypeId, Feature.SIZE_NORMAL|Feature.AVAILABLE);
	}

	@Override
	public String getSourceSideBarIcon(int sourceTypeId) {
		return querySourceTypeIcon(sourceTypeId, Feature.SIZE_LARGE|Feature.AVAILABLE);
	}

}