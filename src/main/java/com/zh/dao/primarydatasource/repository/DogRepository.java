package com.zh.dao.primarydatasource.repository;

import com.zh.dao.primarydatasource.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DogRepository
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/9:39
 */
public interface DogRepository extends JpaRepository<Dog,Long> {
    Dog findById(int id);
}
