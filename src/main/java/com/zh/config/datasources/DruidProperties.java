package com.zh.config.datasources;

import lombok.Data;

/**
 * DruidProperties
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/10:14
 */
@Data
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClass;
    private int     maxActive;
    private int     minIdle;
    private int     initialSize;
    private boolean testOnBorrow;
}
