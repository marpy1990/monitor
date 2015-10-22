package sjtu.cit.monitor.web.home.module.control.overview;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.dal.dao.IconDao;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Sidebar {

	@Autowired
	private IconDao iconDao;

	@Autowired
	private SourceManager sourceManager;

	public void execute(Context context, @Param("sourceId") Integer sourceId) {
		if (null == sourceId) sourceId = 0;

		Source source = sourceManager.getSource(sourceId);
		if (null == source) return;
		String icon = iconDao.getSourceSideBarIcon(sourceManager.getTypedSourceId(sourceId));
		context.put("icon", icon);
		context.put("name", source.getName());
		context.put("sourceId", sourceId);
	}
	
}
