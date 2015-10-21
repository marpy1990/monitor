package sjtu.cit.monitor.dal.dao;

import sjtu.cit.monitor.dal.entity.SourceNode;

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
	public void updateState(SourceNode node);
	
}
