package sjtu.cit.monitor.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.biz.SourceTreeManager;
import sjtu.cit.monitor.biz.UserManager;
import sjtu.cit.monitor.dal.dao.SourceTreeStateDao;
import sjtu.cit.monitor.dal.entity.SourceNode;
import sjtu.cit.monitor.dal.entity.User;

public class SourceTreeManagerImpl implements SourceTreeManager{
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SourceTreeStateDao sourceTreeStateDao;
	
	private SourceNode getCurrentNode(int sourceId, String treeId) {
		User user = userManager.getCurrentUser();
		SourceNode node = new SourceNode();
		node.setUserId(user.getId());
		node.setSourceId(sourceId);
		node.setTreeId(treeId);
		return node;
	}
	
	@Override
	public boolean isNodeOpen(int sourceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, treeId);
		return sourceTreeStateDao.isOpen(node);
	}

	@Override
	public void openNode(int sourceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, treeId);
		node.setOpen(true);
		sourceTreeStateDao.updateState(node);
	}

	@Override
	public void closeNode(int sourceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, treeId);
		node.setOpen(false);
		sourceTreeStateDao.updateState(node);
	}

}
