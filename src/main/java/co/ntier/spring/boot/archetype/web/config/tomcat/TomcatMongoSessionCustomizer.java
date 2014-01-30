package co.ntier.spring.boot.archetype.web.config.tomcat;


import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.util.Assert;

import co.ntier.mongo.tomcat.MongoSessionManager;
import co.ntier.mongo.tomcat.MongoSessionTrackerValve;
@Deprecated
public class TomcatMongoSessionCustomizer implements EmbeddedServletContainerCustomizer, TomcatContextCustomizer{

	private final MongoSessionManager manager;
	
	public TomcatMongoSessionCustomizer(MongoSessionManager manager) {
		Assert.notNull(manager, "Parameter 'manager' cannot be null");
		this.manager = manager;
	}

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
