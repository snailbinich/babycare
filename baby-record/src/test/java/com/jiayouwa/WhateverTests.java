package com.jiayouwa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayouwa.component.dto.TokenResp;
import org.junit.Test;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by zhaoyan on 2017/2/5.
 */
public class WhateverTests {

    @Test
    public void testStringFormat(){

        String name="zhaoyan";
        String message = "Hello, {0}";
        String result  = MessageFormat.format(message, name);
        System.out.println(result);
    }

    @Test
    public void testConvert() throws IOException {
        String source = "{\"access_token\":\"4-8Lw71zXj9mSBg3ed1QcW-qXUKfTMYA3O0LUL6lLfm2F8W19XsD5Y6_jIA7sf24Nc326-YZXSO4T_BdIKQtfBlhGlGIcrikjsjyBeC1zR8\",\"expires_in\":7200,\"refresh_token\":\"kFMeK3XF1dS9uinDm9F1Ij0637Km_JLm5OoRf8ljynJdH0_LsMNS-fMRmpuY7W5KFYMhU8oTiQM0_O2wpGspf3PHzYwgsN4vMs7JaXFIt1A\",\"openid\":\"obQnTszcvlgiZfwVWpHkEpzBNaP4\",\"scope\":\"snsapi_userinfo\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        TokenResp resp = objectMapper.readValue(source, TokenResp.class);
        System.out.println(resp);
    }
}
