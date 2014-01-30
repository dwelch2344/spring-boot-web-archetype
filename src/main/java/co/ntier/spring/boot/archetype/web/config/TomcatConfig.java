package co.ntier.spring.boot.archetype.web.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import co.ntier.mongo.tomcat.MongoSessionManager;
import co.ntier.mongo.tomcat.MongoSessionTrackerValve;

import com.mongodb.ServerAddress;

/**
 * Provides a customization when running in EmbeddedTomcat, facilitating session storage using MongoDB.
 * This class should not be loaded when running in a traditional web container. 
 * 
 * @author dave
 */
@Slf4j
public class TomcatConfig {

	@Value("${httpPort:8080}")
	private int port;
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory(){
		return new TomcatEmbeddedServletContainerFactory(port);
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer() throws URISyntaxException, UnknownHostException {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainerFactory factory) {
				if(factory instanceof TomcatEmbeddedServletContainerFactory){
					TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
					configureTomcat(containerFactory);
				}
			}
		};
	}
	
	// TODO clean this up
	private void configureTomcat(TomcatEmbeddedServletContainerFactory cf) {
		try{
			configureMongoSessions(cf);
		}catch(Exception e){
			log.warn("Failed configuring mongo sessions", e);
		}
	}
	
	private void configureMongoSessions(TomcatEmbeddedServletContainerFactory cf) throws URISyntaxException, UnknownHostException {
		String url = System.getenv("MONGO_SESSION_URL");
		if (url != null) {
			URI uri = new URI(url);
			
			String user = uri.getUserInfo();
			String[] auth = user == null ? new String[]{null, null} : user.split(":");
			
			int port = uri.getPort();
			port = port < 0 ? 27017 : port;
			
			ServerAddress address = new ServerAddress(uri.getHost(), port);
			final MongoSessionManager manager = new MongoSessionManager(address, uri.getPath().substring(1), auth[0], auth[1]);
			log.info("Established MongoManager to " + address);
			
			cf.addContextValves(new MongoSessionTrackerValve());
			cf.addContextCustomizers(new TomcatManagerCustomizer(manager));
		}
	}
	
	@RequiredArgsConstructor
	public static class TomcatManagerCustomizer implements TomcatContextCustomizer{
		private final Manager manager;
		@Override
		public void customize(Context context) {
			context.setManager(manager);
		}
	}

}
