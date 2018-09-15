package com.zh.dao.primarydatasource.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dog
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/10/15:16
 */
@Entity
@Table(name = "dog")
@Data
@Accessors(chain = true)
public class Dog {
    @Id
    private int id;
    private String name;
}
