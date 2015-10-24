package sjtu.cit.monitor.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.biz.SourceTreeManager;
import sjtu.cit.monitor.biz.UserManager;
import sjtu.cit.monitor.dal.dao.SourceTreeStateDao;
import sjtu.cit.monitor.dal.entity.SourceNode;
import sjtu.cit.monitor.dal.entity.TreeNode;
import sjtu.cit.monitor.dal.entity.User;

public class SourceTreeManagerImpl implements SourceTreeManager {

	@Autowired
	private UserManager userManager;

	@Autowired
	private SourceTreeStateDao sourceTreeStateDao;

	private SourceNode getCurrentNode(int sourceId, int spaceId, String treeId) {
		User user = userManager.getCurrentUser();
		SourceNode node = new SourceNode();
		node.setUserId(user.getId());
		node.setSourceId(sourceId);
		node.setSpaceId(spaceId);
		node.setTreeId(treeId);
		return node;
	}

	@Override
	public boolean isNodeOpen(int sourceId, int spaceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, spaceId, treeId);
		return sourceTreeStateDao.isOpen(node);
	}

	@Override
	public void openNode(int sourceId, int spaceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, spaceId, treeId);
		node.setOpen(true);
		sourceTreeStateDao.updateNodeState(node);
	}

	@Override
	public void closeNode(int sourceId, int spaceId, String treeId) {
		SourceNode node = getCurrentNode(sourceId, spaceId, treeId);
		node.setOpen(false);
		sourceTreeStateDao.updateNodeState(node);
	}

	@Override
	public int getCurrentSpaceId(String treeId) {
		User user = userManager.getCurrentUser();
		TreeNode tree = new TreeNode();
		tree.setUserId(user.getId());
		tree.setTreeId(treeId);
		return sourceTreeStateDao.getCurrentSpaceId(tree);
	}

	@Override
	public void setCurrentSpaceId(int spaceId, String treeId) {
		User user = userManager.getCurrentUser();
		TreeNode tree = new TreeNode();
		tree.setUserId(user.getId());
		tree.setTreeId(treeId);
		tree.setCurrentSpaceId(spaceId);
		sourceTreeStateDao.setCurrentSpaceId(tree);
	}

}
