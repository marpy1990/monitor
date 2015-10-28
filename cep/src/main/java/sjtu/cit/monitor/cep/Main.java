package sjtu.cit.monitor.cep;

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sjtu.cit.monitor.api.cep.entity.Source;
import sjtu.cit.monitor.cep.dal.dao.impl.SourceDaoImpl;
import sjtu.cit.monitor.cep.dal.query.SelectQuery;

public class Main {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"service.xml");
		context.start();
		SourceDaoImpl dao = (SourceDaoImpl) context.getBean("sourceDaoImpl");
		SelectQuery query = new SelectQuery().where("name", "3");
		List<Source> lst = dao.getList(query);
		System.out.println(lst.size());
		System.in.read();
	}
}
