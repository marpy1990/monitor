package sjtu.cit.monitor.biz;

public interface SourceTreeManager {

	/**
	 * 判断source是否在当前用户的tree中处于打开状态
	 * 
	 * @return
	 */
	public boolean isNodeOpen(int sourceId, int spaceId, String treeId);

	public void openNode(int sourceId, int spaceId, String treeId);

	public void closeNode(int sourceId, int spaceId, String treeId);
	
	public int getCurrentSpaceId(String treeId);
	
	public void setCurrentSpaceId(int spaceId, String treeId);

}
