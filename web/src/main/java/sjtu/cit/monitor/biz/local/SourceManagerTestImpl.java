package sjtu.cit.monitor.biz.local;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.Source;

public class SourceManagerTestImpl implements SourceManager {

	private Map<Integer, List<Source>> tree = new HashMap<Integer, List<Source>>();
	
	private Map<Integer, Source> table = new HashMap<Integer, Source>();

	public SourceManagerTestImpl() {
		tree.put(
				0,
				Arrays.asList(newSource(1, "127.0.0.1", true, 3),
						newSource(2, "192.168.0.30", false, 3),
						newSource(3, "192.168.0.31", false, 3),
						newSource(4, "rtm", false, 2)));
		tree.put(
				1,
				Arrays.asList(newSource(11, "cpu", true, 4),
						newSource(12, "memory", true, 4)));
		tree.put(
				2,
				Arrays.asList(newSource(21, "cpu", true, 4),
						newSource(22, "memory", true, 4)));
		tree.put(
				3,
				Arrays.asList(newSource(31, "cpu", true, 4),
						newSource(32, "memory", true, 4)));
		tree.put(
				4,
				Arrays.asList(newSource(41, "getTotalTime()", true, 5),
						newSource(42, "getIncreaseRate()", true, 5),
						newSource(43, "queryForOverFlowDoEllipseHaHaHa()", true, 5)));
		tree.put(
				11,
				Arrays.asList(newSource(111, "1", true, 4),
						newSource(112, "2", true, 4),
						newSource(113, "3", true, 4),
						newSource(114, "4", true, 4)));
	}

	@Override
	public Source getSource(int sourceId) {
		if(!table.containsKey(sourceId)) return null;
		else return table.get(sourceId);
	}

	@Override
	public List<Source> getDirectSubSources(int sourceId) {
		return tree.get(sourceId);
	}

	private Source newSource(int id, String name, boolean ok, int catId) {
		Source source = new Source();
		source.setId(id);
		source.setName(name);
		source.setOk(ok);
		source.setTypeId(catId);
		table.put(id, source);
		return source;
	}

	@Override
	public boolean hasSubSources(int sourceId) {
		return tree.containsKey(sourceId);
	}

}
