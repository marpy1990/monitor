package sjtu.cit.monitor.api.cep.entity;

public class SourceCategory {

	/**
	 * 一级类目hardware的id
	 */
	public static final int HARDWARE = 1;

	/**
	 * 一级类目software的id
	 */
	public static final int SOFTWARE = 2;

	/**
	 * 二级类目machine的id
	 */
	public static final int MACHINE = 3;
	
	/**
	 * 二级类目component的id
	 */
	public static final int COMPONENT = 4;

	private int id;

	private String name;

	private int parentId;

	public SourceCategory() {
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
