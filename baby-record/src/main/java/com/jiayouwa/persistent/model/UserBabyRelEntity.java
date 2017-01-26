package com.jiayouwa.persistent.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by zhaoyan on 2017/1/24.
 */
@Entity
@Data
@Table(name = "user_baby_rel")
public class UserBabyRelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long babyId;
    @NotNull
    private long userId;


    private Timestamp createTime;
    private Timestamp modifyTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Timestamp(System.currentTimeMillis());
        modifyTime = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected  void onUpdate(){
        modifyTime = new java.sql.Timestamp(System.currentTimeMillis());
    }


}
