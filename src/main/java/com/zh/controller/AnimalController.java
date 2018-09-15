package com.zh.controller;

import com.zh.service.AnimalService;
import com.zh.service.CatService;
import com.zh.service.DogService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * AnimalController
 *
 * @author zhanghang
 * @Description
 * @date 2018/9/11/13:33
 */
@RestController
public class AnimalController {
    @Resource
    private AnimalService animalService;

    @PostMapping("animal")
    public ModelMap createAnimal(@RequestBody Map map){
        animalService.saveAnimal(map);
        return getSuccess();
    }
    private ModelMap getSuccess() {
        return new ModelMap(){{
            put("code",200);
            put("msg",true);
        }};

    }

}
