package com.zh.controller;

import com.zh.dao.secondarydatasource.entity.Cat;
import com.zh.service.CatService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * CatController
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/9:38
 */
@RestController
public class CatController {
    @Resource
    private CatService catService;



    @PostMapping("/cat")
    public ModelMap insert(@RequestBody Cat cat){
        catService.insertOne(cat);
        return getSuccess();
    }

    @PostMapping("/cats")
    public ModelMap insertAny(@RequestBody Cat cat){
        catService.insertAny(cat);
        return getSuccess();
    }


    @GetMapping("/cat/{id}")
    public Cat findDog(@PathVariable int id){
        return catService.findById(id);
    }

    private ModelMap getSuccess() {
        return new ModelMap(){{
            put("code",200);
            put("msg",true);
        }};

    }

}
