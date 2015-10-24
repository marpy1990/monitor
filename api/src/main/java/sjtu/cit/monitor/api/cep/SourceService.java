package sjtu.cit.monitor.api.cep;

import sjtu.cit.monitor.api.cep.entity.Source;

public interface SourceService {

	/**
	 * 通过id 获取source
	 * 
	 * @param sourceId
	 *            资源id，关于一些系统保留的id参见{@link Source.InternId}
	 * @return 若sourceId不存在返回null
	 */
	public Source getSource(int sourceId);

	/**
	 * 添加一个source，sourceId由系统自动生成
	 * 
	 * @param sourceName
	 * @return this source
	 */
	public Source addSource(String sourceName);

	/**
	 * 删除一个source，同时会将其对应的视图关系全部删掉
	 * 
	 * @param sourceId
	 */
	public void removeSource(int sourceId);

	/**
	 * 更新source
	 * @param source
	 */
	public void updateSource(Source source);

}
