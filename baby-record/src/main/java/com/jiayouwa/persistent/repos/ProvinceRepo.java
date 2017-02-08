package com.jiayouwa.persistent.repos;

import com.jiayouwa.persistent.model.ProvinceEntity;
import com.jiayouwa.persistent.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhaoyan on 2017/2/7.
 */
public interface ProvinceRepo extends JpaRepository<ProvinceEntity, Long> {


}
