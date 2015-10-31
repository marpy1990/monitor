package sjtu.cit.monitor.dal.dao;

import sjtu.cit.monitor.dal.entity.SourceNode;
import sjtu.cit.monitor.dal.entity.TreeNode;

public interface SourceTreeStateDao {
	
	/**
	 * 返回node开闭状态，默认为false
	 * @param node
	 * @return
	 */
	public boolean isOpen(SourceNode node);
	
	/**
	 * 更新node状态，若node不存在则会自动insert
	 * @param node
	 */
	public void updateNodeState(SourceNode node);
	
	/**
	 * 获取当前spaceId
	 * 
	 * @param spaceId
	 * @return 若未设置返回null
	 */
	public Integer getCurrentSpaceId(TreeNode tree);
	
	/**
	 * 设置当前spaceId
	 * 
	 * @param tree
	 */
	public void setCurrentSpaceId(TreeNode tree);
	
}
