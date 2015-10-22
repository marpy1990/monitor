package sjtu.cit.monitor.api.cep;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.Relation;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.SourceState;

public interface SourceManager {
	
	/**
	 * 通过id 获取source
	 * @param sourceId 资源id，关于一些系统保留的id参见{@link Source.InternId}
	 * @return 若sourceId不存在返回null
	 */
	public Source getSource(int sourceId);
	
	/**
	 * 获取所有与当前source有relation关系的资源
	 * @param sourceId 资源id，关于一些系统保留的id参见{@link Source.InternId}
	 * @param relation 代表一种关系{@link Relation}
	 * @return 若sourceId不存在返回null，否则至少会返回一个空的List
	 */
	public List<Source> getSourcesByRelation(int sourceId, int relation);
	
	/**
	 * 获取所有与当前source有relation关系的资源的数目
	 * @param sourceId 资源id，关于一些系统保留的id参见{@link Source.InternId}
	 * @param relation 代表一种关系{@link Relation}
	 * @return 若sourceId不存在返回0
	 */
	public int countSourcesByRelation(int sourceId, int relation);
	
	/**
	 * 检查source1与source2是否具有relation关系
	 * @param sourceId1
	 * @param sourceId2
	 * @param relation
	 * @return 当且仅当source1与source存在且具有relation关系时才为真
	 */
	public boolean testRelation(int sourceId1, int sourceId2, int relation);
	
	/**
	 * 获取source的状态
	 * @param sourceId
	 * @return
	 */
	public SourceState getSourceState(int sourceId);
	
	/**
	 * 获取代表其类型的资源id
	 * @param sourceId
	 * @return
	 */
	public int getTypedSourceId(int sourceId);

}
