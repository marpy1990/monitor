package sjtu.cit.monitor.web.home.module.screen.rpc.setting;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.biz.SourceTreeManager;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSON;

public class Viewspace {

	@Autowired
	private SourceTreeManager sourceTreeManager;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private ViewSpaceService viewSpaceService;

	public void doAddSource(@Param("parentId") Integer parentId,
			@Param("treeId") String treeId) {
		Integer spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		Source newSource = sourceService.addSource("新建资源");
		viewSpaceService.addEdge(newSource.getId(), parentId, spaceId);
		sourceTreeManager.openNode(parentId, treeId);
	}

	public Boolean doRenameSource(@Param("sourceId") Integer sourceId,
			@Param("name") String name) {
		Source source = new Source();
		source.setId(sourceId);
		source.setName(name);
		sourceService.updateSource(source);
		return true;
	}

	public Boolean doRemoveSource(@Param("sourceId") Integer sourceId,
			@Param("parentId") Integer parentId, @Param("treeId") String treeId) {
		int spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		if (spaceId != ViewSpace.RECYCLED) {
			viewSpaceService.removeEdge(sourceId, parentId, spaceId);
			viewSpaceService.addEdge(sourceId, Source.InternId.ROOT,
					ViewSpace.RECYCLED);
		} else {
			viewSpaceService.removeEdge(sourceId, parentId, ViewSpace.RECYCLED);
		}
		return true;
	}

	public void doAddViewSpace(@Param("name") String name) {
		viewSpaceService.addViewSpace(name);
	}

	public void doMoveSources(@Param("parentId") Integer parentId,
			@Param("fromParentId") Integer fromParentId,
			@Param("sourceIds") String sourceIdsJSON,
			@Param("isCopy") Boolean isCopy,
			@Param("fromTreeId") String fromTreeId,
			@Param("treeId") String treeId) {
		List<Integer> sourceIds = JSON.parseArray(sourceIdsJSON, Integer.class);
		Integer spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		isCopy |= !StringUtils.equals(fromTreeId, treeId);
		for (Integer id : sourceIds) {
			if (!isCopy) {
				viewSpaceService.removeEdge(id, fromParentId, spaceId);
			}
			viewSpaceService.addEdge(id, parentId, spaceId);
		}
	}

}
