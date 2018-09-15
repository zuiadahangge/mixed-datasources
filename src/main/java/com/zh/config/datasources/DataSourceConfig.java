package com.zh.config.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * H2DatabaseConfig
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/7/16:39
 */
@Configuration
@Slf4j
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class DataSourceConfig {

	@Resource
	private DruidProperties primaryDruidProperties;
	@Resource
	private DruidProperties secondaryDruidProperties;


	@Bean(name = "primaryDataSource")
	@Primary
	public DataSource primaryDataSource() {
		return createDataSource(primaryDruidProperties);
	}

	@Bean(name = "secondaryDataSource")
	public DataSource secondaryDataSource() {
		return createDataSource(secondaryDruidProperties);
	}


	public DataSource createDataSource(DruidProperties properties) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		if (properties.getInitialSize() > 0) {
			dataSource.setInitialSize(properties.getInitialSize());
		}
		if (properties.getMinIdle() > 0) {
			dataSource.setMinIdle(properties.getMinIdle());
		}
		if (properties.getMaxActive() > 0) {
			dataSource.setMaxActive(properties.getMaxActive());
		}
		dataSource.setTestOnBorrow(properties.isTestOnBorrow());
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		log.info(dataSource.getUrl());
		return dataSource;
	}
}
