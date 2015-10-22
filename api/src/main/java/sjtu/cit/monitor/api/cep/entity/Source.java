package sjtu.cit.monitor.api.cep.entity;

public class Source {

	/**
	 * 系统预留的保留sourceId
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
