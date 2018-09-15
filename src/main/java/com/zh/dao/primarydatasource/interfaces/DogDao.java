package com.zh.dao.primarydatasource.interfaces;

import com.zh.dao.primarydatasource.entity.Dog;

/**
 * DogDao
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/15:43
 */
public interface DogDao {
    void insertOne(Dog dog);
    Dog findOne(int id);
}
