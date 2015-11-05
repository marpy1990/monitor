package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
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
	private ViewSpaceService viewSpaceService;

	@Autowired
	private SourceTreeManager sourceTreeManager;

	public ViewSpaceState doGetViewSpaces(@Param("treeId") String treeId) {
		List<ViewSpace> spaces = viewSpaceService.getViewSpaces();
		Collections.sort(spaces, SpaceComparator.INSTANCE);
		ViewSpaceState state = new ViewSpaceState();
		state.setSpaces(spaces);
		int spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		state.setCurrent(spaceId);
		return state;
	}

	public List<Node> doGetNodes(@Param("id") Integer id,
			@Param("treeId") String treeId) {
		if (StringUtil.isBlank(treeId))
			return null;
		Integer spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		if (spaceId == null)
			return null;
		List<Node> nodes = getSubNodes(id, spaceId, treeId,
				new HashSet<Integer>());
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

	public void doSwitchSpace(@Param("spaceId") Integer spaceId,
			@Param("treeId") String treeId) {
		sourceTreeManager.setCurrentSpaceId(spaceId, treeId);
	}

	private List<Node> getSubNodes(Integer id, int spaceId, String treeId,
			Set<Integer> parent) {
		Set<Integer> set = new HashSet<Integer>(parent);
		set.add(id);
		List<Node> nodes = new ArrayList<Node>();

		List<Source> sources = new ArrayList<Source>();
		if (null == id) { // 对根结点特殊处理
			Source root = sourceService.getSource(Source.InternId.ROOT);
			sources.add(root);
		} else {
			sources = viewSpaceService.getSourcesAdjacentTo(id, spaceId);
		}
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

			boolean hasSubsources = viewSpaceService.countSourcesAdjacentTo(
					sourceId, spaceId) != 0;
			node.setIsParent(hasSubsources || sourceId == Source.InternId.ROOT);
			if (hasSubsources && sourceTreeManager.isNodeOpen(sourceId, treeId)
					&& !set.contains(sourceId)) {
				node.setOpen(true);
				node.setChildren(getSubNodes(sourceId, spaceId, treeId, set));
			}
			nodes.add(node);
		}

		return nodes;
	}

	private String getSourceTreeIcon(int sourceId) {
		String icon = iconDao.getSourceTreeIcon(sourceId);
		Set<Integer> checked = new HashSet<Integer>();
		while (null == icon) {
			if (checked.contains(sourceId))
				break; // 防止死锁
			checked.add(sourceId);
			List<Source> parents = viewSpaceService.getAdjacentSources(
					sourceId, ViewSpace.TYPE);
			if (parents.isEmpty())
				break;
			icon = iconDao.getSourceTreeIcon(parents.get(0).getId());
			sourceId = parents.get(0).getId();
		}
		return "null".equals(icon) ? null : icon;
	}

}

class SourceComparator implements Comparator<Source> {

	public static final SourceComparator INSTANCE = new SourceComparator();

	@Override
	public int compare(Source s1, Source s2) {
		return Integer.compare(s1.getId(), s2.getId());
	}

}

class SpaceComparator implements Comparator<ViewSpace> {

	public static final SpaceComparator INSTANCE = new SpaceComparator();

	@Override
	public int compare(ViewSpace s1, ViewSpace s2) {
		return Integer.compare(s1.getId(), s2.getId());
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