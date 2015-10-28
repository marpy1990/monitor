package sjtu.cit.monitor.cep.dal.query;

import java.util.HashMap;
import java.util.Map;

public class UpdateQuery extends Query {

	protected Map<String, Object> where = new HashMap<String, Object>();

	protected Map<String, Object> set = new HashMap<String, Object>();

	public UpdateQuery where(String key, Object value) {
		where.put(key, value);
		return this;
	}

	public UpdateQuery set(String key, Object value) {
		set.put(key, value);
		return this;
	}
	
}
