package com.jiayouwa.persistent.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhaoyan on 2017/2/7.
 */
@Entity
@Data
@Table(name = "provinces")
public class ProvinceEntity {

    @Id
    private long id;
    private String name;


}
