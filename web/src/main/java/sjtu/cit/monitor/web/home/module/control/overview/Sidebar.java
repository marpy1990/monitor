package sjtu.cit.monitor.web.home.module.control.overview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.dal.dao.IconDao;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Sidebar {

	@Autowired
	private IconDao iconDao;

	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private ViewSpaceService viewSpaceService;

	public void execute(Context context, @Param("sourceId") Integer sourceId) {
		if (null == sourceId) sourceId = 0;

		Source source = sourceService.getSource(sourceId);
		if (null == source) return;
		String icon = getIcon(sourceId);
		context.put("icon", icon);
		context.put("name", source.getName());
	}

	/**
	 * 优先获取自身id关联的图，若不存在则通过类目回溯获取
	 * @return
	 */
	private String getIcon(int sourceId) {
		String icon = iconDao.getSourceSideBarIcon(sourceId);
		while(null == icon){
			List<Source> parents =  viewSpaceService.getAdjacentSources(sourceId, ViewSpace.TYPE);
			if(parents.isEmpty()) break;
			icon = iconDao.getSourceSideBarIcon(parents.get(0).getId());
		}
		return icon;
	}
	
}
