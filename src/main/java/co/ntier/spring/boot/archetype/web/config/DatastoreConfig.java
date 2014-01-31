package co.ntier.spring.boot.archetype.web.config;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import co.ntier.spring.boot.archetype.web.Application;

@Configuration
public class DatastoreConfig {
	
	@Value("${DATABASE_URL:postgres://localhost:5432/ns-dev}")
	private String connectionUrl;

	@Bean
	public DataSource dataSource() {
		URI uri = URI.create(connectionUrl);
	    String url = "jdbc:postgresql://" + uri.getHost() + ':' + uri.getPort() + uri.getPath();

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(url);
		
		String info = uri.getUserInfo();
		if( info != null){
			String[] auth = info.split(":");
			if( auth.length > 0) ds.setUsername(auth[0]);
			if( auth.length > 1) ds.setPassword(info.split(":")[1]);
		}
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabasePlatform(PostgreSQL9Dialect.class.getName());
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan( Application.class.getPackage().getName() + ".model");
		factory.setDataSource(dataSource);
//		factory.setJpaProperties(getJpaProperties());
		return factory;
	}
	
	@Bean
	public EntityManager entityManager(LocalContainerEntityManagerFactoryBean bean){
		return bean.getObject().createEntityManager();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean bean){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(bean.getObject());
		return transactionManager;
	}
	
}
