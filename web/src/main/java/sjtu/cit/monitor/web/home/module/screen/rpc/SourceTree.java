package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.citrus.turbine.dataresolver.Param;

public class SourceTree {

	public List<Node> doGetNodes(@Param("id") Long id) {
		List<Node> nodes = new ArrayList<Node>();
		if (null == id) {
			Node node = new Node(1, 0, "root", false, true);
			nodes.add(node);
		}
		else{
			nodes.add(new Node(11, 1, "1.1", false, true));
			nodes.add(new Node(111, 11, "1.1.1", false, false));
			nodes.add(new Node(12, 1, "1.1", false, true));
		}
		return nodes;
	}

}

class Node {

	private long id;

	private long pId;
	
	private String name;
	
	private boolean open; 

	private boolean isParent;

	public Node() {
		super();
	}

	public Node(long id, long pid, String name, boolean open, boolean isParent) {
		super();
		this.id = id;
		this.pId = pid;
		this.name = name;
		this.open = open;
		this.isParent = isParent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public long getPId() {
		return pId;
	}

	public void setPId(long pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}