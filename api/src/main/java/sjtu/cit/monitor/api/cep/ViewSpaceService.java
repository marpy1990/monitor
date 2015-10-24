package sjtu.cit.monitor.api.cep;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.ViewSpace;

public interface ViewSpaceService {
	/**
	 * 获取所有的view space
	 * 
	 * @return 至少返回空List
	 */
	public List<ViewSpace> getViewSpaces();

	/**
	 * 添加域
	 * 
	 * @param viewSpaceName
	 * @return
	 */
	public ViewSpace addViewSpace(String viewSpaceName);

	/**
	 * 删除域
	 * 
	 * @param spaceId
	 */
	public void removeNameSpace(int spaceId);

	/**
	 * 更新域
	 * 
	 * @param space
	 */
	public void updateNameSpace(ViewSpace space);

}
