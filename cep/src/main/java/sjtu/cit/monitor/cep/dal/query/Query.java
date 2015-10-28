package sjtu.cit.monitor.cep.dal.query;

public class Query {
	
	protected String table;
	
	@SuppressWarnings("unchecked")
	public<T extends Query>T table(String table){
		this.table = table;
		return (T) this;
	}

}
