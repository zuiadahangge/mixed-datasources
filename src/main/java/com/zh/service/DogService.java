package com.zh.service;

import com.zh.dao.primarydatasource.entity.Dog;

/**
 * DogService
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/9:42
 */
public interface DogService {
    Dog findById(int id);
    void insertOne(Dog dog);

    void insertAny(Dog dog);

    void m_insert(Dog dog);

    void mix_insert(Dog dog);
}
