package com.zh.config.datasources;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * H2DatabaseConfig
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/7/16:39
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "primaryManagerFactory",
		transactionManagerRef = "primaryTransactionManager",
		basePackages = {"com.zh.dao.primarydatasource"})
@MapperScan(basePackages = "com.zh.dao.primarydatasource.interfaces",sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

	@Resource
	private DataSource primaryDataSource;

	@Bean(name = "primaryManageFactoryBean")
	public LocalContainerEntityManagerFactoryBean primaryManageFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(primaryDataSource)
				//.secondaryDruidProperties(getVendorProperties(primaryDataSource))
				.packages("com.zh.dao.primarydatasource.entity")
				.persistenceUnit("primaryPersistenceUnit")
				.build();
	}

	/*private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}*/

	@Bean(name = "primaryManagerFactory")
	@Primary
	public EntityManagerFactory primaryManagerFactory(EntityManagerFactoryBuilder builder) {
		return this.primaryManageFactoryBean(builder).getObject();
	}

	/**
	 * 配置事物管理器
	 * 这个是jpa的
	 * @return
	 */
/*	@Bean(name = "primaryTransactionManager")
	@Primary
	public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(primaryManagerFactory(builder));
	}*/

	/**
	 * 配置事物管理器
	 * 用这个来同时支持mybatis
	 * @return
	 */
	@Bean(name = "primaryTransactionManager")
	public DataSourceTransactionManager primaryTransactionManager() {
		return new DataSourceTransactionManager(primaryDataSource);
	}

	@Bean(name = "primarySqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory()
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(primaryDataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/primary/*.xml"));
		return sessionFactory.getObject();
	}




}
