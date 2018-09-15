package com.zh.dao.secondarydatasource.repository;

import com.zh.dao.secondarydatasource.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DogRepository
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/9:39
 */
public interface CatRepository extends JpaRepository<Cat,Long> {
    Cat findById(int id);
}
