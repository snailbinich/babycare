package com.jiayouwa.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayouwa.component.Constants;
import com.jiayouwa.component.WebAppConfig;
import com.jiayouwa.component.dto.TokenResp;
import com.jiayouwa.persistent.model.UserEntity;
import com.jiayouwa.service.UserService;
import com.jiayouwa.util.HttpClientUtils;
import groovy.util.logging.Slf4j;
import lombok.Data;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by zhaoyan on 2017/2/5.
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private WebAppConfig appConfig;

    @Autowired
    private UserService userService;

    @RequestMapping("/wxLogin")
    public String wxLogin(@RequestParam String  code, @RequestParam String state, HttpSession session){

        //TODO 校验code合法性和state的合法性
//        System.out.println(params);
        TokenResp tokenResp = getTokenByCode(code);
        System.out.println(tokenResp);

        UserEntity userEntity = userService.saveOrUpdateUser(tokenResp,"");
        session.setAttribute(Constants.UID, userEntity.getUid());

        return "redirect:/baby/list";
    }

    private TokenResp getTokenByCode(String code){
        String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
        getTokenUrl = MessageFormat.format(getTokenUrl, appConfig.getAppId(), appConfig.getAppSecret(), code);
        String respString = HttpClientUtils.getObjectFromUrl(getTokenUrl, String.class);
        System.out.println(respString);
        ObjectMapper mapper = new ObjectMapper();
        TokenResp tokenResp = null;
        try {
            tokenResp = mapper.readValue(respString, TokenResp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenResp;
    }
}
