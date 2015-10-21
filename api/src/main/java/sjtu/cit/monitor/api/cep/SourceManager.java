package sjtu.cit.monitor.api.cep;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.Source;

public interface SourceManager {
	
	/**
	 * 通过id 获取source
	 * @param sourceId 资源id
	 * @return 若sourceId不存在返回null
	 */
	public Source getSource(int sourceId);
	
	/**
	 * 获取所有<b>直接</b>隶属于当前资源的子项<br/>
	 * 等价于选取所有source.parentId=id的资源<br/>
	 * @param sourceId 资源id
	 * @return 若sourceId不存在返回null；若子项不存在返回空List
	 */
	public List<Source> getDirectSubSources(int sourceId);
	
	/**
	 * 判断当前资源是否存在子项
	 * @param sourceId
	 * @return
	 */
	public boolean hasSubSources(int sourceId);

}
