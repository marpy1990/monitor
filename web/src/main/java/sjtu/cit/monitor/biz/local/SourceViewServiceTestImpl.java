package sjtu.cit.monitor.biz.local;

import java.util.List;

import sjtu.cit.monitor.api.cep.SourceViewService;
import sjtu.cit.monitor.api.cep.entity.Source;

public class SourceViewServiceTestImpl implements SourceViewService{

	@Override
	public List<Source> getAdjacentSources(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Source> getSourcesAdjacentTo(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsPath(int formSourceId, int toSourceId, int spaceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEdge(int formSourceId, int toSourceId, int spaceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(int formSourceId, int toSourceId, int spaceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countAdjacentSources(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countSourcesAdjacentTo(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
