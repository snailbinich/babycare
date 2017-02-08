package com.jiayouwa.component;

import lombok.Data;
import lombok.Getter;

/**
 * Created by zhaoyan on 2017/2/7.
 */
@Getter
public enum Gender {

    FEMALE(0),
    MALE(1);

    private  int code;

    Gender(int code){
        this.code = code;
    }

}
