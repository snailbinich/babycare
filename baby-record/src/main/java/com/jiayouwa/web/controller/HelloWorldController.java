package com.jiayouwa.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyan on 2017/1/24.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHello(){
        return  "redirect:/baby/list";
    }
}
