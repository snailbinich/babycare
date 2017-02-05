package com.jiayouwa.service;

import com.jiayouwa.component.Constants;
import com.jiayouwa.component.WebAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * Created by zhaoyan on 2017/2/5.
 */
@Service
public class SessionService {

    @Value("${webapp.login.callback}")
    private String loginCallback;

    @Autowired
    private WebAppConfig webAppConfig;

    public String getRedirectUrl(){
        String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid={0}&redirect_uri={1}" +
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        try {
            String encodeCallBack = URLEncoder.encode(loginCallback, Constants.ENCODE_UTF8);
            redirectUrl = MessageFormat.format(redirectUrl, webAppConfig.getAppId(),
                    encodeCallBack);
            return  redirectUrl;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
