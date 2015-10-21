package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.SourceType;
import sjtu.cit.monitor.biz.SourceTreeManager;
import sjtu.cit.monitor.dal.dao.IconDao;
import sjtu.cit.monitor.dal.entity.Feature;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;

public class SourceTree {

	@Autowired
	private IconDao iconDao;

	@Autowired
	private SourceManager sourceManager;

	@Autowired
	private SourceTreeManager sourceTreeManager;

	public List<Node> doGetNodes(@Param("id") Integer id,
			@Param("treeId") String treeId) {
		if (StringUtil.isBlank(treeId))
			return null;
		List<Node> nodes = null;
		if (null == id) {
			nodes = new ArrayList<Node>();
			Node root = getRootNode(treeId);
			nodes.add(root);
		} else {
			nodes = getSubNodes(id, treeId);
		}
		return nodes;
	}

	public void doExpandNode(@Param("id") Integer id,
			@Param("treeId") String treeId) {
		sourceTreeManager.openNode(id, treeId);
	}

	public void doCollapseNode(@Param("id") Integer id,
			@Param("treeId") String treeId) {
		sourceTreeManager.closeNode(id, treeId);
	}

	private Node getRootNode(String treeId) {
		Node root = new Node();
		root.setId(0);
		root.setIsParent(true);
		root.setName("所有资源");
		root.setIcon(iconDao.getSourceTreeIcon(0));
		boolean isOpen = sourceTreeManager.isNodeOpen(0, treeId);
		if (isOpen) {
			root.setOpen(true);
			root.setChildren(getSubNodes(0, treeId));
		}
		return root;
	}

	private List<Node> getSubNodes(int id, String treeId) {
		List<Node> nodes = new ArrayList<Node>();
		List<Source> sources = sourceManager.getDirectSubSources(id);
		if (null == sources)
			return null;
		Collections.sort(sources, SourceComparator.INSTANCE);
		for (Source source : sources) {
			Node node = new Node();
			int sourceId = source.getId();
			node.setId(sourceId);
			node.setName(source.getName());

			String icon = getSourceTreeIcon(source);
			node.setIcon(icon);

			boolean hasSubsources = sourceManager.hasSubSources(sourceId);
			node.setIsParent(hasSubsources);
			if (hasSubsources && sourceTreeManager.isNodeOpen(sourceId, treeId)) {
				node.setOpen(true);
				node.setChildren(getSubNodes(sourceId, treeId));
			}
			nodes.add(node);
		}

		return nodes;
	}

	private String getSourceTreeIcon(Source source) {
		String icon = null;
		switch (source.getTypeId()) {
		case SourceType.MACHINE:
			int feature = source.isOk() ? Feature.AVAILABLE
					: Feature.UNAVAILABLE;
			icon = iconDao.getSourceTreeIcon(source.getTypeId(), feature);
			break;
		default:
			icon = iconDao.getSourceTreeIcon(source.getTypeId());
		}
		return icon;
	}

}

class SourceComparator implements Comparator<Source> {

	public static final SourceComparator INSTANCE = new SourceComparator();

	@Override
	public int compare(Source s1, Source s2) {
		if (s1.getTypeId() != s2.getTypeId())
			return Integer.valueOf(s1.getTypeId()).compareTo(s2.getTypeId());

		return Integer.valueOf(s1.getId()).compareTo(s2.getId());
	}

}

class Node {

	private int id;

	private String name;

	private String icon;

	private boolean open;

	private boolean isParent;

	private List<Node> children;

	public Node() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}