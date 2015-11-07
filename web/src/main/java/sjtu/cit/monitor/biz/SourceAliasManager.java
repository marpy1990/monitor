package sjtu.cit.monitor.biz;

import sjtu.cit.monitor.dal.entity.SourceAlias;

public interface SourceAliasManager {

	public SourceAlias getAlias(int sourceId, int spaceId);

	public void setAlias(int sourceId, int spaceId, String alias);

	public void cancelAlias(int sourceId, int spaceId);
	
}
