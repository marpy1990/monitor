package sjtu.cit.monitor.web.home.module.screen.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.SourceViewService;
import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.biz.SourceTreeManager;
import sjtu.cit.monitor.dal.dao.IconDao;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;

public class SourceTree {

	@Autowired
	private IconDao iconDao;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private SourceViewService sourceViewService;

	@Autowired
	private SourceTreeManager sourceTreeManager;

	@Autowired
	private ViewSpaceService viewSpaceService;
	
	private Integer i=0;

	public ViewSpaceState doGetViewSpaces(@Param("treeId") String treeId) {
//		List<ViewSpace> spaces = viewSpaceService.getViewSpaces();
//		Collections.sort(spaces, SpaceComparator.INSTANCE);
//		ViewSpaceState state = new ViewSpaceState();
//		state.setSpaces(spaces);
//		int spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
//		state.setCurrent(spaceId);
		ViewSpaceState state = new ViewSpaceState();
		state.setCurrent(2);
		ViewSpace v1 = new ViewSpace();v1.setId(1);v1.setName("测试1");
		ViewSpace v2 = new ViewSpace();v2.setId(2);v2.setName("测试2");
		ViewSpace v3 = new ViewSpace();v3.setId(3);v3.setName("测试3");
		state.setSpaces(Arrays.asList(v1,v2,v3));
		return state;
	}

	public List<Node> doGetNodes(@Param("id") Integer id,
			@Param("spaceId") Integer spaceId, @Param("treeId") String treeId) {
//		if (StringUtil.isBlank(treeId) || null == spaceId)
//			return null;
//		if (null == id)
//			id = Source.InternId.ROOT;
//		List<Node> nodes = getSubNodes(id, spaceId, treeId);
//		return nodes;
		List<Node> nodes = new ArrayList<Node>();
		if (null == id){
			Node node = new Node();
			node.setId(0);
			node.setName("所有");
			node.setIsParent(true);
			nodes.add(node);
		}
		else{
			Node node = new Node();
			node.setId(++i);
			node.setName(i.toString());
			node.setIsParent(i%2==0);
			nodes.add(node);
			node = new Node();
			node.setId(++i);
			node.setName(i.toString());
			node.setIsParent(i%2==0);
			nodes.add(node);
			node = new Node();
			node.setId(++i);
			node.setName(i.toString());
			node.setIsParent(i%2==0);
			nodes.add(node);
		}
		return nodes;
	}

	public void doExpandNode(@Param("id") Integer id,
			@Param("spaceId") Integer spaceId, @Param("treeId") String treeId) {
		sourceTreeManager.openNode(id, spaceId, treeId);
	}

	public void doCollapseNode(@Param("id") Integer id,
			@Param("spaceId") Integer spaceId, @Param("treeId") String treeId) {
		sourceTreeManager.closeNode(id, spaceId, treeId);
	}

	private List<Node> getSubNodes(int id, int spaceId, String treeId) {
		List<Node> nodes = new ArrayList<Node>();

		List<Source> sources = sourceViewService.getSourcesAdjacentTo(id,
				spaceId);
		if (null == sources)
			return null;
		Collections.sort(sources, SourceComparator.INSTANCE);
		for (Source source : sources) {
			Node node = new Node();
			int sourceId = source.getId();
			node.setId(sourceId);
			node.setName(source.getName());

			String icon = getSourceTreeIcon(sourceId);
			node.setIcon(icon);

			boolean hasSubsources = sourceViewService.countSourcesAdjacentTo(
					sourceId, spaceId) != 0;
			node.setIsParent(hasSubsources);
			if (hasSubsources
					&& sourceTreeManager.isNodeOpen(sourceId, sourceId, treeId)) {
				node.setOpen(true);
				node.setChildren(getSubNodes(sourceId, sourceId, treeId));
			}
			nodes.add(node);
		}

		return nodes;
	}

	private String getSourceTreeIcon(int sourceId) {
		String icon = iconDao.getSourceSideBarIcon(sourceId);
		while (null == icon) {
			List<Source> parents = sourceViewService.getAdjacentSources(
					sourceId, ViewSpace.TYPE);
			if (parents.isEmpty())
				break;
			icon = iconDao.getSourceTreeIcon(parents.get(0).getId());
		}
		return icon;
	}

}

class SourceComparator implements Comparator<Source> {

	public static final SourceComparator INSTANCE = new SourceComparator();

	@Override
	public int compare(Source s1, Source s2) {
		return Integer.valueOf(s1.getId()).compareTo(s2.getId());
	}

}

class SpaceComparator implements Comparator<ViewSpace> {

	public static final SpaceComparator INSTANCE = new SpaceComparator();

	@Override
	public int compare(ViewSpace s1, ViewSpace s2) {
		return Integer.valueOf(s1.getId()).compareTo(s2.getId());
	}

}

class ViewSpaceState {

	private int current;

	List<ViewSpace> spaces;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public List<ViewSpace> getSpaces() {
		return spaces;
	}

	public void setSpaces(List<ViewSpace> spaces) {
		this.spaces = spaces;
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