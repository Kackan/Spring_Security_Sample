package com.myapp.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"com.myapp.repository"})
@PropertySource("classpath:database.properties")
public class PersistenceJpaConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource()
	{
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));				
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		dataSource.setInitialPoolSize(env.getProperty("connection.pool.initialPoolSize",Integer.class));
		dataSource.setMinPoolSize(env.getProperty("connection.pool.minPoolSize",Integer.class));
		dataSource.setMaxPoolSize(env.getProperty("connection.pool.maxPoolSize", Integer.class));
		dataSource.setMaxIdleTime(env.getProperty("connection.pool.maxIdleTime",Integer.class));
		return dataSource;
	}
	
	
	private Properties jpaProperties()
	{
		Properties prop=new Properties();
		prop.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
		prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		prop.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		return prop;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(new String[] {env.getProperty("hibernate.packagesToScan")});
		
		HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		
		return entityManagerFactoryBean;
	}
	
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager transactionManager=new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}


}