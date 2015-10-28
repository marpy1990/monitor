package sjtu.cit.monitor.cep.dal.query;

import java.util.HashMap;
import java.util.Map;

public class DeleteQuery extends Query {
	
	protected Map<String, Object> where = new HashMap<String, Object>();

	public DeleteQuery where(String key, Object value) {
		where.put(key, value);
		return this;
	}
	
}
