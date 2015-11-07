package sjtu.cit.monitor.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sjtu.cit.monitor.biz.SourceAliasManager;
import sjtu.cit.monitor.dal.dao.SourceAliasDao;
import sjtu.cit.monitor.dal.entity.SourceAlias;

public class SourceAliasManagerImpl implements SourceAliasManager {

	@Autowired
	private SourceAliasDao sourceAliasDao;

	@Override
	public SourceAlias getAlias(int sourceId, int spaceId) {
		SourceAlias alias = sourceAliasDao.get(sourceId, spaceId);
		if (null == alias) {
			alias = new SourceAlias();
			alias.setEnable(false);
		}
		return alias;
	}

	@Override
	@Transactional
	public void setAlias(int sourceId, int spaceId, String alias) {
		SourceAlias sourceAlias = new SourceAlias();
		sourceAlias.setSourceId(sourceId);
		sourceAlias.setSpaceId(spaceId);
		sourceAlias.setAlias(alias);
		sourceAlias.setEnable(true);
		int success = sourceAliasDao.update(sourceAlias);
		if (success == 0)
			sourceAliasDao.insert(sourceAlias);
	}

	@Override
	public void cancelAlias(int sourceId, int spaceId) {
		SourceAlias sourceAlias = new SourceAlias();
		sourceAlias.setSourceId(sourceId);
		sourceAlias.setSpaceId(spaceId);
		sourceAlias.setEnable(false);
		sourceAliasDao.update(sourceAlias);
	}

}
