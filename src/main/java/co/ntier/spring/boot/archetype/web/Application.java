package co.ntier.spring.boot.archetype.web;

import org.springframework.boot.actuate.autoconfigure.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import co.ntier.spring.boot.archetype.web.config.TomcatConfig;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplicationBuilder sb = new SpringApplicationBuilder(
				Application.class, 
				TomcatConfig.class // include for embedded tomcat...
			);
		sb.run(args);
	}
	
}
