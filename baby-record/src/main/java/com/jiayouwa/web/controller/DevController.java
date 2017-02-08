package com.jiayouwa.web.controller;

import com.jiayouwa.component.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by zhaoyan on 2017/2/7.
 */
@Controller
@RequestMapping("/dev")
public class DevController {

    @RequestMapping("/login")
    public String login(@RequestParam int userId, HttpSession session){

        session.setAttribute(Constants.UID, userId);

        return "redirect:/baby/list";

    }
}
