package sjtu.cit.monitor.cep.dal.query;

import java.util.HashMap;
import java.util.Map;

public class SelectQuery extends Query {

	protected Map<String, Object> where = new HashMap<String, Object>();

	protected Integer limit;

	protected Integer offset;

	public SelectQuery where(String key, Object value) {
		where.put(key, value);
		return this;
	}

	public SelectQuery limit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public SelectQuery offset(Integer offset) {
		this.offset = offset;
		return this;
	}
	
}
