package co.ntier.spring.boot.archetype.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import co.ntier.spring.boot.archetype.web.config.TomcatConfig;

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
		// include tomcat config for embedded tomcat...
		SpringApplicationBuilder sb = new SpringApplicationBuilder(Application.class, TomcatConfig.class);
		ApplicationContext ctx = sb.run(args);
		ctx.getApplicationName(); // noop?
	}
	
}
