package com.jiayouwa.repo;

import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.repos.BabyInfoRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BabyInfoRepoTests {

	@Autowired
    BabyInfoRepo babyInfoRepo;

	@Test
	public void addBabyInfo() {

        BabyInfoEntity entity = new BabyInfoEntity();
        entity.setBloodType("B");
        entity.setBirthWeight(66);
        entity.setNickName("楠楠");
        babyInfoRepo.save(entity);
        System.out.print(entity);

    }

    @Test
    public void testGetByUserId(){
        List<BabyInfoEntity> babies = babyInfoRepo.getBabiesByUserId("1");
        for (BabyInfoEntity baby:babies){
            System.out.println(baby);
        }
    }

}
