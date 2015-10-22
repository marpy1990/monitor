package sjtu.cit.monitor.dal.dao;

public interface IconDao {

	/**
	 * 获取icon的URL,一般的形式为/static/img/xxx.png 可以根据需要自行拼接<br/>
	 * 若找不到对应sourceId的icon，则会返回null<br/>
	 * 
	 * @param sourceId
	 * @return
	 */
	public String getSourceTreeIcon(int sourceId);
	
	/**
	 * 根据sourceId和feature获取icon的URL<br/>
	 * feature是约定的source状态码，默认为0<br/>
	 * 
	 * @param sourceId
	 * @param feature
	 * @return
	 * @see IconDao#getSourceTreeIcon(int)
	 */
	public String getSourceTreeIcon(int sourceId, int feature);
	
	/**
	 * 根据sourceId获得sidebar的资源代表图
	 * @param sourceId
	 * @return
	 */
	public String getSourceSideBarIcon(int sourceId);

}
