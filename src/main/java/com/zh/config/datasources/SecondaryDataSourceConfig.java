package com.zh.config.datasources;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "secondaryManagerFactory",
		transactionManagerRef = "secondaryTransactionManager",
		basePackages = {"com.zh.dao.secondarydatasource"})
public class SecondaryDataSourceConfig {

	@Resource
	private DataSource secondaryDataSource;

	@Bean(name = "secondaryManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean secondaryManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(secondaryDataSource)
				//.secondaryDruidProperties(getVendorProperties(secondaryDataSource))
				.packages("com.zh.dao.secondaryDataSource.entity")
				.persistenceUnit("secondaryPersistenceUnit")
				.build();
	}

	/*private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}*/

	@Bean(name = "secondaryManagerFactory")
	public EntityManagerFactory secondaryManagerFactory(EntityManagerFactoryBuilder builder) {
		return this.secondaryManagerFactoryBean(builder).getObject();
	}

	/**
	 * 配置事物管理器
	 *
	 * @return
	 */
	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(secondaryManagerFactory(builder));
	}

}
