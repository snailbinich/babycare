package com.jiayouwa.service;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.model.UserBabyRelEntity;
import com.jiayouwa.persistent.repos.BabyInfoRepo;
import com.jiayouwa.persistent.repos.UserBabyRelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaoyan on 2017/2/4.
 */
@Service
public class BabyService {

    @Autowired
    BabyInfoRepo babyInfoRepo;

    @Autowired
    UserBabyRelRepo userBabyRelRepo;



    @Transactional
    public BabyInfoEntity addBaby(BabyInfoEntity newBaby, long uid){
        babyInfoRepo.save(newBaby);
        UserBabyRelEntity newRelation = new UserBabyRelEntity();
        newRelation.setBabyId(newBaby.getId());
        newRelation.setUserId(uid);
        userBabyRelRepo.save(newRelation);
        return newBaby;
    }
}
