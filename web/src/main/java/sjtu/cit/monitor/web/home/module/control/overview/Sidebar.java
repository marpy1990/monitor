package sjtu.cit.monitor.web.home.module.control.overview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.dal.dao.IconDao;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;

public class Sidebar {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IconDao iconDao;
	
	@Autowired
	private SourceManager sourceManager;

	public void execute(Context context, @Param("sourceId")Integer sourceId) {
		if(null == sourceId) return;
		context.put("sourceId", sourceId);
		
		Source source = sourceManager.getSource(sourceId);
		if(null == source) return;
		String icon = iconDao.getSourceSideBarIcon(source.getTypeId());
		context.put("icon", icon);
		context.put("name", source.getName());
		
		String target = getSecondFolderName();
		context.put("target", target);
	}
	
	/**
	 * 获取二级目录名
	 * @return
	 */
	private String getSecondFolderName(){
		String path = request.getServletPath();
		int fistSlideIndex = StringUtil.indexOf(path, "/");
		if (fistSlideIndex == -1)
			return null;
		int secondSlideIndex = StringUtil.indexOf(path, "/", fistSlideIndex + 1);
		if (secondSlideIndex == -1)
			return null;

		// target为二级目录名
		String target = StringUtil.substringBetween(path, "/", "/", secondSlideIndex);
		if (null == target)
			target = StringUtil.substringBetween(path, "/", ".", secondSlideIndex);
		if (null == target)
			target = StringUtil.substring(path, secondSlideIndex);
		return target;
	}
}
