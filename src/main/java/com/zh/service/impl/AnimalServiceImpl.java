package com.zh.service.impl;

import com.zh.dao.primarydatasource.entity.Dog;
import com.zh.dao.primarydatasource.repository.DogRepository;
import com.zh.dao.secondarydatasource.entity.Cat;
import com.zh.dao.secondarydatasource.repository.CatRepository;
import com.zh.service.AnimalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * AnimalServiceImpl
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/13:36
 */
@Service
public class AnimalServiceImpl implements AnimalService {
    @Resource
    private CatRepository catRepository;
    @Resource
    private DogRepository dogRepository;


    @Override
    @Transactional(transactionManager = "secondaryTransactionManager")//多数据源，事务要指定事务管理器
    public void saveAnimal(Map map) {
        int id=(Integer) map.get("id");
        String name=(String)map.get("name");
        Dog dog=new Dog().setId(id).setName(name);
        Cat cat=new Cat().setId(id).setName(name);
        dogRepository.save(dog);
        catRepository.save(cat);
        Integer error=(Integer)map.get("error");
        if (error==1){
            throw new RuntimeException();
        }
        //cat 在secondaryTransactionManager中，所以cat被回滚了，dog插入成功
        //换成primaryManageFactoryBean  结果相反，
        // 如果不指定transactionManager，默认使用第一个
    }
}
