package sjtu.cit.monitor.web.home.module.control.overview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Detail {
	
	@Autowired
	private SourceManager sourceManager;
	
	public void execute(Context context, @Param("sourceId") Integer sourceId){
		if (null == sourceId) sourceId = 0;

		Source source = sourceManager.getSource(sourceId);
		if (null == source) return;
		
		List<Info> state = new ArrayList<Info>();
		state.add(new Info("名称", source.getName()));
		state.add(new Info("连接状态", "失去连接"));
		state.add(new Info("部署机器", "202.120.38.175"));
		state.add(new Info("最后连接ID", "ID:marpy-40137-1445213255509-1:1"));
		state.add(new Info("IP", "tcp://127.0.0.1:40138"));
		state.add(new Info("CPU", "Core(TM) i5-4200M CPU @ 2.50GHz"));
		state.add(new Info("系统状态", "正常"));
 		
		context.put("state", state);
	}
	
}

@SuppressWarnings("serial")
class Info extends HashMap<String, String>{

	public Info() {
		super();
	}

	public Info(String key, String value) {
		super();
		this.put("key", key);
		this.put("value", value);
	}

}
