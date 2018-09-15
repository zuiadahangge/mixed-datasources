package com.zh.config.datasources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DruidPropertiesConfig
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/10:47
 */
@Configuration
public class DruidPropertiesConfig {
    @Bean("primaryDruidProperties")
    @ConfigurationProperties(prefix = "druid.primary")
    public DruidProperties primaryDruidProperties(){
        return new DruidProperties();
    }


    @Bean("secondaryDruidProperties")
    @ConfigurationProperties(prefix = "druid.secondary")
    public DruidProperties secondaryDruidProperties(){
        return new DruidProperties();
    }
}
