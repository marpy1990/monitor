package sjtu.cit.monitor.api.cep.service;

public interface SourceCategoryService {
	
	/**
	 * 获取categoryId对应的一级类目的id
	 * @param categoryId 当前source的类目id
	 * @return 若categoryId不存在返回0
	 */
	public int getFirstCategoryId(int categoryId);
	
	/**
	 * 获取categoryId对应的二级类目的id
	 * @param categoryId 当前source的类目id
	 * @return 若categoryId不存在返回0
	 */
	public int getSecondCategoryId(int categoryId);
	
}
