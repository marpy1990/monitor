package sjtu.cit.monitor.biz.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.cit.monitor.api.cep.SourceManager;
import sjtu.cit.monitor.api.cep.entity.MachineState;
import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.api.cep.entity.SourceState;

public class SourceManagerTestImpl implements SourceManager {

	private Map<Integer, Node> table = new HashMap<Integer, Node>();

	public SourceManagerTestImpl() {
		table.put(0, new Node());
		newSource(100, "rtm", 0, Source.InternId.SYSTEM);
		newSource(101, "软件", 100, Source.InternId.SOFTWARE);
		newSource(102, "硬件", 100, Source.InternId.HARDWARE);
		newSource(103, "日志", 100, Source.InternId.LOG);
		
		newSource(201, "127.0.0.1", 102, Source.InternId.MACHINE);
		newSource(2010, "cpu", 201, Source.InternId.COMPONENT);
		newSource(20101, "1", 2010, Source.InternId.COMPONENT);
		newSource(20102, "2", 2010, Source.InternId.COMPONENT);
		newSource(2011, "memory", 201, Source.InternId.COMPONENT);
		newSource(202, "202.120.38.3", 102, Source.InternId.MACHINE);
		newSource(203, "202.128.38.175", 102, Source.InternId.MACHINE);
		
		newSource(301, "进程", 101, Source.InternId.PROCESS);
		newSource(302, "函数", 101, Source.InternId.FUNCTION);
		newSource(303, "变量", 101, Source.InternId.VAR);
		newSource(3020, "getTotal()", 302, Source.InternId.FUNCTION);
		newSource(3021, "getAll()", 302, Source.InternId.FUNCTION);
		
		newSource(401, "access.log", 103, Source.InternId.LOG);
		newSource(402, "jetty.log", 103, Source.InternId.LOG);
		
	}

	private void newSource(int id, String name, int parentId, int type) {
		Node node = new Node();
		table.put(id, node);
		node.id = id;
		node.name = name;
		node.type = type;
		table.get(parentId).sub.add(node);
	}

	@Override
	public Source getSource(int sourceId) {
		Node node = table.get(sourceId);
		Source source = new Source();
		source.setId(node.id);
		source.setName(node.name);
		return source;
	}

	@Override
	public List<Source> getSourcesByRelation(int sourceId, int relation) {
		List<Source> list = new ArrayList<Source>();
		Node nodes = table.get(sourceId);
		for (Node node : nodes.sub) {
			list.add(getSource(node.id));
		}
		return list;
	}

	@Override
	public boolean testRelation(int sourceId1, int sourceId2, int relation) {
		Node node = table.get(sourceId1);
		return node.type == sourceId2;
	}

	@Override
	public int countSourcesByRelation(int sourceId, int relation) {
		return getSourcesByRelation(sourceId, relation).size();
	}

	@Override
	public SourceState getSourceState(int sourceId) {
		return new State(sourceId);
	}

	@Override
	public int getTypedSourceId(int sourceId) {
		Node node = table.get(sourceId);
		return node.type;
	}

}

class State implements MachineState {

	private int id;

	public State(int id) {
		this.id = id;
	}

	@Override
	public boolean isAvailable() {
		return id % 2 == 1;
	}

}

class Node {
	int id;
	String name;
	int type;
	List<Node> sub = new ArrayList<Node>();
}
