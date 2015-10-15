package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.citrus.turbine.dataresolver.Param;

public class SourceTree {

	public List<Node> doGetNodes(@Param("id") Long id) {
		List<Node> nodes = new ArrayList<Node>();
		if (null == id) {
			Node node = new Node("1", "root", true);
			nodes.add(node);
		}
		else{
			nodes.add(new Node("1", "1", false));
			nodes.add(new Node("2", "2", false));
			nodes.add(new Node("3", "3", false));
			nodes.add(new Node("4", "4", false));
			nodes.add(new Node("5", "5", false));
			nodes.add(new Node("6", "6", false));
			nodes.add(new Node("7", "17", false));
			nodes.add(new Node("8", "1", false));
			nodes.add(new Node("9", "2", false));
			nodes.add(new Node("10", "3", false));
			nodes.add(new Node("11", "4", false));
			nodes.add(new Node("12", "5", false));
			nodes.add(new Node("13", "6", false));
			nodes.add(new Node("14", "17", false));
		}
		return nodes;
	}

}

class Node {

	private long id;

	private String name;

	private boolean isParent;

	public Node() {
		super();
	}

	public Node(String id, String name, boolean isParent) {
		super();
		this.id = Long.valueOf(id);
		this.name = name;
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
}