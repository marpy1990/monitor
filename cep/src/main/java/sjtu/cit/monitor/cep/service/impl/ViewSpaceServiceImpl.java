package sjtu.cit.monitor.cep.service.impl;

import java.util.List;

import sjtu.cit.monitor.api.cep.ViewSpaceService;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.ViewSpace;

public class ViewSpaceServiceImpl implements ViewSpaceService{

	@Override
	public List<ViewSpace> getViewSpaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewSpace addViewSpace(String viewSpaceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeNameSpace(int spaceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNameSpace(ViewSpace space) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Source> getAdjacentSources(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAdjacentSources(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Source> getSourcesAdjacentTo(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countSourcesAdjacentTo(int sourceId, int spaceId) {
		// TODO Auto-generated method stub
		return 0;
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

}
