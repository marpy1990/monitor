package sjtu.cit.monitor.cep;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service.xml");
        context.start();
        System.in.read();
    }
}
