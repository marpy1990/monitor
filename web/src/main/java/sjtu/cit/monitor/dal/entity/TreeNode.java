package sjtu.cit.monitor.dal.entity;

public class TreeNode {

	private String treeId;

	private int userId;
	
	private int currentSpaceId;

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCurrentSpaceId() {
		return currentSpaceId;
	}

	public void setCurrentSpaceId(int currentSpaceId) {
		this.currentSpaceId = currentSpaceId;
	}

}
