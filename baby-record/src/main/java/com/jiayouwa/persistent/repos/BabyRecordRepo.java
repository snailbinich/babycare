package com.jiayouwa.persistent.repos;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.model.BabyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhaoyan on 2017/2/4.
 */
public interface BabyRecordRepo extends JpaRepository<BabyRecordEntity, Long> {

    public List<BabyRecordEntity> findByBabyIdOrderByCreateTime(long babyId);
}
