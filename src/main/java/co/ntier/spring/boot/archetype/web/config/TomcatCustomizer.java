package co.ntier.spring.boot.archetype.web.config;

import javax.inject.Inject;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import co.ntier.mongo.tomcat.MongoSessionTrackerValve;

public class TomcatCustomizer implements EmbeddedServletContainerCustomizer, TomcatContextCustomizer{

	@Inject
	private Manager manager;

	@Override
	public void customize(ConfigurableEmbeddedServletContainerFactory factory) {
		if(factory instanceof TomcatEmbeddedServletContainerFactory){
			TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
			containerFactory.addContextValves(new MongoSessionTrackerValve());
			containerFactory.addContextCustomizers(this);
		}
	}

	@Override
	public void customize(Context context) {
		context.setManager(manager);
	}

}
