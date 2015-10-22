package sjtu.cit.monitor.web.home.module.screen;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Overview {

	private static final String TOPOLOGY = "topology";
	private static final String DETAIL = "detail";
	private static final String LINE_CHART = "lineChart";
	private static final String LOG = "log";
	private static final String PROBE = "probe";

	@Autowired
	private SourceManager sourceManager;

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
		switch (sourceManager.getTypedSourceId(sourceId)) {
		case Source.InternId.MACHINE:
			modules.add(Overview.DETAIL);
			modules.add(Overview.PROBE);
			break;
		case Source.InternId.COMPONENT:
			modules.add(Overview.DETAIL);
			modules.add(Overview.LINE_CHART);
			break;
		case Source.InternId.SOFTWARE:
			modules.add(Overview.TOPOLOGY);
			modules.add(Overview.DETAIL);
			break;
		case Source.InternId.FUNCTION:
			modules.add(Overview.DETAIL);
			modules.add(Overview.LOG);
		default:
			modules.add(Overview.DETAIL);
		}
		return modules;
	}
}
