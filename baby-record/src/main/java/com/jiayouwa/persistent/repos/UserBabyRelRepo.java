package com.jiayouwa.persistent.repos;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.model.UserBabyRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyan on 2017/2/4.
 */
public interface UserBabyRelRepo extends JpaRepository<UserBabyRelEntity, Long> {
}
