package sjtu.cit.monitor.cep.dal.query;

import java.util.HashMap;
import java.util.Map;

public class InsertQuery extends Query {

	protected Map<String, Object> values = new HashMap<String, Object>();

	public InsertQuery value(String key, Object value) {
		values.put(key, value);
		return this;
	}

}
