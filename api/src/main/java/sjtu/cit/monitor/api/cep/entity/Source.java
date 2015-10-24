package sjtu.cit.monitor.api.cep.entity;

/**
 * source是系统中可以监控的最小单位<br/>
 * source不仅仅对应实体，也可以是一种概念，例如对于“硬件”这一概念也可以称之为source<br/>
 * source通过id唯一确定，但允许重名<br/>
 * 系统预先保留了一些sourceId，这些id一般作为类目域下用于标识sourceType的用途，但用户也可以创建自己的关系视图来做新的解释<br/>
 * 
 * source本身已经可以通过视图的方式表达派生关系，故禁止其被继承
 * 
 * @author guhua.gh
 *
 */
public final class Source {

	/**
	 * 系统预留的保留sourceId, 一般在类目作用域下发挥作用
	 * 
	 * @author guhua.gh
	 *
	 */
	public interface InternId {
		/**
		 * 代表根目录
		 */
		public static final int ROOT = 0;

		/**
		 * 代表系统
		 */
		public static final int SYSTEM = 1;

		/**
		 * 代表硬件
		 */
		public static final int HARDWARE = 2;

		/**
		 * 代表软件
		 */
		public static final int SOFTWARE = 3;

		/**
		 * 代表机器
		 */
		public static final int MACHINE = 4;

		/**
		 * 代表部件
		 */
		public static final int COMPONENT = 5;

		/**
		 * 代表函数
		 */
		public static final int FUNCTION = 6;

		/**
		 * 代表进程
		 */
		public static final int PROCESS = 7;

		/**
		 * 代表日志
		 */
		public static final int LOG = 8;
		
		/**
		 * 代表变量
		 */
		public static final int VAR = 9;
		
		/**
		 * 代表概念
		 */
		public static final int CONCEPT = 10;
		
	}

	private int id;

	private String name;

	public Source() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
