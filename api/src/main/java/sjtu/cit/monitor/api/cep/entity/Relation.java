package sjtu.cit.monitor.api.cep.entity;

/**
 * 用于描述关系的常量
 * @author guhua.gh
 *
 */
public interface Relation {
	
	/**
	 * 组成
	 */
	public static final int PART = 1;
	
	/**
	 * 延伸
	 */
	public static final int EXTEND = 2;
	
	/**
	 * 调用
	 */
	public static final int CALL = 3;
	
	/**
	 * 同类
	 */
	public static final int TYPE = 4;
	
	/**
	 * 位置
	 */
	public static final int LOCATE = 5;
	
}
