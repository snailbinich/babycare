package com.jiayouwa.persistent.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhaoyan on 2017/1/24.
 */

@Entity
@Data
@Table(name = "baby_info")
public class BabyInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nickName;
    private Date birthday;
    private int birthWeight;
//    private int provinceId;
    private String province;
    private String bloodType;

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
