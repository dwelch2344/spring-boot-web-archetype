package co.ntier.spring.boot.archetype.web;

import java.net.UnknownHostException;

import org.apache.catalina.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import co.ntier.mongo.tomcat.MongoSessionManager;
import co.ntier.spring.boot.archetype.web.config.TomcatCustomizer;

import com.mongodb.ServerAddress;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		ctx.getApplicationName(); // noop?
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer(){
		return new TomcatCustomizer();
	}
	
	@Bean
	public Manager tomcatManager() throws UnknownHostException{
		MongoSessionManager manager = new MongoSessionManager(new ServerAddress(), "dev", "", "");
		return manager;
	}
	
}
