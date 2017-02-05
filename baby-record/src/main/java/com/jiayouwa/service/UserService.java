package com.jiayouwa.service;

import com.jiayouwa.component.dto.TokenResp;
import com.jiayouwa.persistent.model.UserEntity;
import com.jiayouwa.persistent.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by zhaoyan on 2017/2/5.
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity saveOrUpdateUser(TokenResp tokenResp, String nickName){
        UserEntity newUser = userRepo.findTop1ByOpenId(tokenResp.getOpenid());
        if(newUser == null){
            newUser = new UserEntity();
        }
        newUser.setNickName(nickName);
        newUser.setOpenId(tokenResp.getOpenid());
        newUser.setAccessToken(tokenResp.getAccess_token());
        newUser.setRefreshToken(tokenResp.getRefresh_token());
        long expireTime = System.currentTimeMillis() + (tokenResp.getExpires_in()*1000);
        newUser.setAccessTokenInvalidTime(new Timestamp(expireTime));
        userRepo.save(newUser);
        return newUser;
    }
}
