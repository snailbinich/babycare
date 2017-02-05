package com.jiayouwa.component.dto;

import lombok.Data;

/**
 * Created by zhaoyan on 2017/2/5.
 */
@Data
public class TokenResp {
    private String access_token;
    private long expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
