package sjtu.cit.monitor.dal.dao;

public interface IconDao {

	/**
	 * 获取icon的URL,一般的形式为/static/img/xxx.png 可以根据需要自行拼接<br/>
	 * 若找不到对应sourceTypeId的icon，则会返回默认的url: /static/img/sourceTree/unknow.png<br/>
	 * 
	 * @param sourceTypeId
	 * @return
	 */
	public String getSourceTreeIcon(int sourceTypeId);
	
	/**
	 * 根据sourceTypeId和feature获取icon的URL<br/>
	 * feature是约定的source状态码，默认为0<br/>
	 * 
	 * @param sourceTypeId
	 * @param feature
	 * @return
	 * @see IconDao#getSourceTreeIcon(int)
	 */
	public String getSourceTreeIcon(int sourceTypeId, int feature);
	
	/**
	 * 根据sourceTypeId获得sidebar的资源代表图
	 * @param sourceTypeId
	 * @return
	 */
	public String getSourceSideBarIcon(int sourceTypeId);

}
