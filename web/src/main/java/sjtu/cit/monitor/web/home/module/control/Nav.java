package sjtu.cit.monitor.web.home.module.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.util.StringUtil;

public class Nav {
	
	@Autowired
	private HttpServletRequest request;
	
	public void execute(Context context) {
		String path = request.getServletPath();
		//target为一级目录名
		String target = StringUtil.substringBetween(path, "/", "/");
		if(null == target) target = StringUtil.substringBetween(path, "/", ".");
		if(null == target) target = StringUtil.substringAfter(path, "/");
		context.put("target", target);
	}
	
}
