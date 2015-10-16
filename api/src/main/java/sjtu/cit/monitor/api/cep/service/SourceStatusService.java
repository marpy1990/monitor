package sjtu.cit.monitor.api.cep.service;

public interface SourceStatusService {
	
	/**
	 * 不同的source有着不同的ok标准<br/>
	 * 例如对于硬件，返回是否运行；对于软件，返回是否可以访问等等
	 * @param sourceId
	 * @return
	 */
	public boolean isOk(int sourceId);
}
