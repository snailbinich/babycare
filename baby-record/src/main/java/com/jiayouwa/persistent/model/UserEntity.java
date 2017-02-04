package com.jiayouwa.persistent.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zhaoyan on 2017/2/4.
 */
@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    private Long uid;
    private String nickName;
    private String token;
    private Timestamp createTime;
    private Timestamp modifyTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Timestamp(System.currentTimeMillis());
        modifyTime = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modifyTime = new java.sql.Timestamp(System.currentTimeMillis());

    }
}
