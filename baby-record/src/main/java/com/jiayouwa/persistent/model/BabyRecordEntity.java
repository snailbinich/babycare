package com.jiayouwa.persistent.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zhaoyan on 2017/1/24.
 */
@Entity
@Data
@Table(name = "baby_record")
public class BabyRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long babyId;
    private int height;
    private int weight;
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
