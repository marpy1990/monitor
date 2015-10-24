package sjtu.cit.monitor.dal.entity;

/**
 * 准确的说是sourceTreeStateNode，记录了用户对资源树的所有行为信息
 * @author guhua.gh
 *
 */
public class SourceNode {

	private int sourceId;
	
	private int spaceId;

	private String treeId;

	private int userId;

	private boolean isOpen;

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

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

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
}
