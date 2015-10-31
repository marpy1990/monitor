package sjtu.cit.monitor.api.cep;

import java.util.List;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;

/**
 * 资源视图是source之间在特定视图空间下的有向图，提供对该图的增删查改操作
 * 
 * @author guhua.gh
 *
 */
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
	
	/**
	 * 获取view space视图下source的所有直接指向结点<br/>
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
	 * 获取view space视图下所有指向source的结点
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
	 * @param fromSourceId 起点
	 * @param toSourceId 终点
	 * @param spaceId 域
	 * @return 当且仅当formSource与toSource都存在且存在这样一条连通分支时返回true
	 */
	public boolean existsPath(int fromSourceId, int toSourceId, int spaceId);

	/**
	 * 在view space视图下下添加fromSource到toSource的边
	 * 
	 * @param fromSourceId 起点
	 * @param toSourceId 终点
	 * @param spaceId 域
	 */
	public void addEdge(int fromSourceId, int toSourceId, int spaceId);
	
	/**
	 * 在view space视图下删除fromSource到toSource的边
	 * 
	 * @param fromSourceId
	 * @param toSourceId
	 * @param spaceId
	 */
	public void removeEdge(int fromSourceId, int toSourceId, int spaceId);

}
