package sjtu.cit.monitor.web.home.module.screen.rpc.setting;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.biz.SourceTreeManager;

import com.alibaba.citrus.turbine.dataresolver.Param;

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

	public void doRenameSource(@Param("sourceId") Integer sourceId,
			@Param("name") String name) {
		Source source = new Source();
		source.setId(sourceId);
		source.setName(name);
		sourceService.updateSource(source);
	}

	public Boolean doRemoveSource(@Param("sourceId") Integer sourceId,
			@Param("parentId") Integer parentId, @Param("treeId") String treeId) {
		int spaceId = sourceTreeManager.getCurrentSpaceId(treeId);
		if (spaceId != ViewSpace.RECYCLED) {
			viewSpaceService.removeEdge(sourceId, parentId, spaceId);
			viewSpaceService.addEdge(sourceId, Source.InternId.ROOT, ViewSpace.RECYCLED);
		}
		return true;
	}

	public void doAddViewSpace(@Param("name") String name) {
		viewSpaceService.addViewSpace(name);
	}

}
