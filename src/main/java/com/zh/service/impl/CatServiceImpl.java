package com.zh.service.impl;

import com.zh.dao.secondarydatasource.repository.CatRepository;
import com.zh.dao.secondarydatasource.entity.Cat;
import com.zh.service.CatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * CatServiceImpl
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/11:08
 */
@Service
public class CatServiceImpl implements CatService {
    @Resource
    private CatRepository catRepository;


    @Override
    public Cat findById(int id) {
        return catRepository.findById(id);
    }

    @Override
    public void insertOne(Cat cat) {
        catRepository.save(cat);
    }


    @Override
    @Transactional(transactionManager = "secondaryTransactionManager")
    public void insertAny(Cat cat) {
        catRepository.save(cat);
        cat.setId(cat.getId()+1).setName(cat.getName()+1);
        catRepository.save(cat);

        if (1==1){
            throw new RuntimeException();
        }


    }
}
