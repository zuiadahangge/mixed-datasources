package com.zh.service;
import com.zh.dao.secondarydatasource.entity.Cat;

/**
 * CatService
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/11:08
 */
public interface CatService {
    Cat findById(int id);
    void insertOne(Cat cat);

    void insertAny(Cat cat);
}
