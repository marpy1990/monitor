package sjtu.cit.monitor.api.cep.entity;

/**
 * 所有source之间的联系被解释成在特定view space下的有向图<br/>
 * 其中TYPE的view space为系统保留用于表示source类型<br/>
 * 通过这种方式我们可以轻松得到系统之间的拓扑关系
 * 
 * @author guhua.gh
 *
 */
public class ViewSpace {
	
	/**
	 * 系统保留的view space，用于标识资源类型
	 */
	public static final int TYPE = 1;
	
	private int id;
	
	private String name;

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
