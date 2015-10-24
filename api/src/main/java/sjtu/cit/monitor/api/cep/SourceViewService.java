package sjtu.cit.monitor.api.cep;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.ViewSpace;
import sjtu.cit.monitor.api.cep.entity.Source;

/**
 * 资源视图是source之间在特定视图空间下的有向图，提供对该图的增删查改操作
 * 
 * @author guhua.gh
 *
 */
public interface SourceViewService {
	/**
	 * 获取view space视图下source的所有相邻结点<br/>
	 * 
	 * @param sourceId
	 * @param spaceId
	 * @return 无论sourceId是否存在，至少返回空List
	 */
	public List<Source> getAdjacentSources(int sourceId, int spaceId);
	
	/**
	 * 获取source的出度
	 * @param sourceId
	 * @param spaceId
	 * @return
	 */
	public int countAdjacentSources(int sourceId, int spaceId);

	/**
	 * 获取view space视图下所有相邻于source的结点
	 * 
	 * @param sourceId
	 * @param spaceId
	 * @return 无论sourceId是否存在，至少返回空List
	 */
	public List<Source> getSourcesAdjacentTo(int sourceId, int spaceId);
	
	/**
	 * 获取source的入度
	 * @param sourceId
	 * @param spaceId
	 * @return
	 */
	public int countSourcesAdjacentTo(int sourceId, int spaceId);

	/**
	 * 测试是否存在一条fromSource到toSource的连通分支
	 * 
	 * @param formSourceId 起点
	 * @param toSourceId 终点
	 * @param spaceId 域
	 * @return 当且仅当formSource与toSource都存在且存在这样一条连通分支时返回true
	 */
	public boolean existsPath(int formSourceId, int toSourceId, int spaceId);

	/**
	 * 在view space视图下下添加fromSource到toSource的边
	 * 
	 * @param formSourceId 起点
	 * @param toSourceId 终点
	 * @param spaceId 域
	 */
	public void addEdge(int formSourceId, int toSourceId, int spaceId);
	
	/**
	 * 在view space视图下删除fromSource到toSource的边
	 * 
	 * @param formSourceId
	 * @param toSourceId
	 * @param spaceId
	 */
	public void removeEdge(int formSourceId, int toSourceId, int spaceId);
	
}
