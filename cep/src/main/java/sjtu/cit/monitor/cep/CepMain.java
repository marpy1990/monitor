package sjtu.cit.monitor.cep;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CepMain {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"service.xml");
		context.start();
		System.in.read();
		context.close();
	}
}