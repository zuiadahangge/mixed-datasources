package com.zh.dao.secondarydatasource.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cat
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/10/15:16
 */
@Entity
@Table(name = "cat")
@Data
@Accessors(chain = true)
public class Cat {
    @Id
    private int id;
    private String name;
}
