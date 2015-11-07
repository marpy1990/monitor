package sjtu.cit.monitor.dal.dao;

import sjtu.cit.monitor.dal.entity.SourceAlias;

public interface SourceAliasDao {

	public SourceAlias get(int sourceId, int spaceId);

	public int insert(SourceAlias sourceAlias);

	public int update(SourceAlias sourceAlias);

}
