package com.jiayouwa.persistent.repos;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhaoyan on 2017/1/24.
 */
public interface BabyInfoRepo extends JpaRepository<BabyInfoEntity, Long> {

    @Query(value = "SELECT bi.* from baby_info bi, user_baby_rel ubr, user u " +
            "where ubr.user_id = u.uid and ubr.baby_id = bi.id and u.uid = ?1",
            nativeQuery = true)
    public List<BabyInfoEntity> getBabiesByUserId(String uid);
}
