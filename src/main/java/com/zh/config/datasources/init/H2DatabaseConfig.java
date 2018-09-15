
package com.zh.config.datasources.init;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * H2DatabaseConfig
 * 初始化表
 * @author zhanghang
 * @Description
 * @date 2018/9/7/16:39
 */


@Configuration
public class H2DatabaseConfig {
    private static final Logger log = Logger.getLogger(H2DatabaseConfig.class);

    final private String PRIMARY_DATABASE=
            "drop table dog if exists ;" +
            "create table dog (ID INT , NAME VARCHAR(100))";


    final private String SECONDARY_DATABASE=
            "drop table cat if exists ;" +
                    "create table cat (ID INT , NAME VARCHAR(100))";

    @Resource
    private DataSource secondaryDataSource;

    @Resource
    private DataSource primaryDataSource;
    @PostConstruct
    public void createTable(){
        log.info("H2嵌入式数据库初始化---------开始---------");

        try {
            log.info("primaryDatasource-----------开始-------------");
            log.info(PRIMARY_DATABASE);
            PreparedStatement preparedStatement1 =primaryDataSource.getConnection().prepareStatement(PRIMARY_DATABASE);
            preparedStatement1.execute();
            log.info("primaryDatasource-----------结束-------------");



            log.info("secondaryDatasource-----------开始-------------");
            log.info(SECONDARY_DATABASE);
            PreparedStatement preparedStatement2 =secondaryDataSource.getConnection().prepareStatement(SECONDARY_DATABASE);
            preparedStatement2.execute();
            log.info("secondaryDatasource-----------结束-------------");

        } catch (SQLException e) {
            log.error("H2嵌入式数据库初始化------失败------------");
            e.printStackTrace();
        }
        log.info("H2嵌入式数据库初始化---------完成---------");
    }
}

