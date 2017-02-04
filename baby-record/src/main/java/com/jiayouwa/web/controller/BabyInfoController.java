package com.jiayouwa.web.controller;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.repos.BabyInfoRepo;
import com.jiayouwa.service.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyan on 2017/1/25.
 */
@Controller
@RequestMapping("/baby")
public class BabyInfoController {

    @Autowired
    private BabyInfoRepo babyInfoRepo;

    @Autowired
    private BabyService babyService;


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String  addBabyForm(){

        return "/addBaby";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addBaby(@RequestParam String nickName, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday){
        BabyInfoEntity newBabyInfo = new BabyInfoEntity();
        newBabyInfo.setNickName(nickName);
        newBabyInfo.setBirthday(birthday);
        babyService.addBaby(newBabyInfo,1l);
        return "redirect:/baby/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listBaby(Map<String,Object> map){
        List<BabyInfoEntity> babies = babyInfoRepo.getBabiesByUserId("1");
        map.put("babies", babies);
        return "/listBaby";
    }


}
