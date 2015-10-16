package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.SourceCategory;
import sjtu.cit.monitor.api.cep.service.SourceCategoryService;
import sjtu.cit.monitor.api.cep.service.SourceService;
import sjtu.cit.monitor.api.cep.service.SourceStatusService;

import com.alibaba.citrus.turbine.dataresolver.Param;

public class SourceTree {

	private static final String ICON_ROOT = "/static/img/sourceTree/root.png";
	private static final String ICON_MACHINE_OK = "/static/img/sourceTree/machine_green.png";
	private static final String ICON_MACHINE_ERR = "/static/img/sourceTree/machine_red.png";
	private static final String ICON_COMPONENT = "/static/img/sourceTree/component.png";
	private static final String ICON_UNKNOW = "/static/img/sourceTree/unknow.png";

	@Autowired
	private SourceService sourceService;

	@Autowired
	private SourceStatusService sourceStatusService;

	@Autowired
	private SourceCategoryService sourceCategoryService;

	public List<Node> doGetNodes(@Param("id") Integer id) {
		List<Node> nodes;
		if (null == id) {
			nodes = new ArrayList<Node>();
			nodes.add(new Node(0, "所有资源", ICON_ROOT, false, true, null));
		} else {
			nodes = getSubNodes(id);
		}
		return nodes;
	}

	private List<Node> getSubNodes(int id) {
		List<Node> nodes = new ArrayList<Node>();
		List<Source> sources = sourceService.getDirectSubSources(id);
		if (null == sources)
			return null;
		Collections.sort(sources, SourceComparator.INSTANCE);
		for (Source source : sources) {
			Node node = new Node();
			int sourceId = source.getId();
			node.setId(sourceId);
			node.setName(source.getName());
			node.setIsParent(sourceService.hasSubSources(sourceId));
			int secCate = sourceCategoryService.getSecondCategoryId(source
					.getCategoryId());
			boolean isOk = sourceStatusService.isOk(sourceId);

			switch (secCate) {
			case SourceCategory.MACHINE:
				node.setIcon(isOk ? ICON_MACHINE_OK : ICON_MACHINE_ERR);
				break;
			case SourceCategory.COMPONENT:
				node.setIcon(ICON_COMPONENT);
				break;
			default:
				node.setIcon(ICON_UNKNOW);
				break;
			}
			nodes.add(node);
		}
		return nodes;
	}

}

class SourceComparator implements Comparator<Source> {

	public static final SourceComparator INSTANCE = new SourceComparator();

	@Override
	public int compare(Source s1, Source s2) {
		if (s1.getCategoryId() != s2.getCategoryId())
			return Integer.valueOf(s1.getCategoryId()).compareTo(
					s2.getCategoryId());

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

	public Node(int id, String name, String icon, boolean open,
			boolean isParent, List<Node> children) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.open = open;
		this.isParent = isParent;
		this.children = children;
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