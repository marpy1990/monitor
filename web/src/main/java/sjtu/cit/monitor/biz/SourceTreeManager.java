package sjtu.cit.monitor.biz;

public interface SourceTreeManager {
	
	/**
	 * 判断source是否在当前用户的tree中处于打开状态
	 * @param sourceId
	 * @param treeId
	 * @return
	 */
	public boolean isNodeOpen(int sourceId, String treeId);
	
	public void openNode(int sourceId, String treeId);
	
	public void closeNode(int sourceId, String treeId);
	
}
