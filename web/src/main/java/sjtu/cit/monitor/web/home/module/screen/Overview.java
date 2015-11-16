package sjtu.cit.monitor.web.home.module.screen;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceService;
import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Overview {

	private static final String TOPOLOGY = "topology";
	private static final String DETAIL = "detail";
	private static final String CHART = "chart";
	private static final String LOG = "log";
	private static final String PROBE = "probe";

	@Autowired
	private SourceService sourceService;

	@Autowired
	private ViewSpaceService viewSpaceService;

	public void execute(Context context, @Param("sourceId") Integer sourceId,
			Navigator nav) {
		if (null == sourceId || sourceId <= 0) {
			nav.redirectTo("homeLink");
			return;
		}

		Set<String> modules = getShowModules(sourceId);
		context.put("modules", modules);

	}

	private Set<String> getShowModules(int sourceId) {
		Set<String> modules = new HashSet<String>();
		if (viewSpaceService.existsPath(sourceId, Source.InternId.COMPONENT,
				ViewSpace.TYPE)) {
			modules.add(Overview.DETAIL);
			modules.add(Overview.CHART);
			return modules;
		}
		if (viewSpaceService.existsPath(sourceId, Source.InternId.MACHINE,
				ViewSpace.TYPE)) {
			modules.add(Overview.DETAIL);
			modules.add(Overview.PROBE);
			return modules;
		}
		if (viewSpaceService.existsPath(sourceId, Source.InternId.FUNCTION,
				ViewSpace.TYPE)) {
			modules.add(Overview.DETAIL);
			modules.add(Overview.LOG);
			return modules;
		}
		if (viewSpaceService.existsPath(sourceId, Source.InternId.SYSTEM,
				ViewSpace.TYPE)) {
			modules.add(Overview.TOPOLOGY);
			modules.add(Overview.DETAIL);
			return modules;
		}
		modules.add(Overview.DETAIL);
		return modules;
	}
}
