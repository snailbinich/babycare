package com.jiayouwa.web.controller;

import com.jiayouwa.component.JsonData;
import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.model.BabyRecordEntity;
import com.jiayouwa.persistent.repos.BabyInfoRepo;
import com.jiayouwa.persistent.repos.BabyRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by zhaoyan on 2017/2/4.
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private BabyRecordRepo babyRecordRepo;

    @Autowired
    private BabyInfoRepo babyInfoRepo;

    @RequestMapping(value = "/{babyId}/add",method = RequestMethod.GET)
    public String addRecordForm(@PathVariable Long babyId, Map<String, Object> map){
        //TODO:校验是否是自己的孩子
        map.put("babyId", babyId);
        return "/addRecord";
    }

    @RequestMapping(value = "{babyId}/add",method = RequestMethod.POST)
    public String addRecord(@PathVariable Long babyId,
            @RequestParam int height, @RequestParam int weight,
                            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date recordDate){
        //TODO：校验是否是自己的孩子
        BabyRecordEntity newRecord = new BabyRecordEntity();
        newRecord.setBabyId(babyId);
        newRecord.setCreateTime(new Timestamp(recordDate.getTime()));
        newRecord.setHeight(height);
        newRecord.setWeight(weight);
        babyRecordRepo.save(newRecord);
        return "redirect:/baby/list";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showRecord(@RequestParam Long babyId, Map<String, Object> map){
        map.put("babyId", babyId);
        return "/showRecord";
    }

    @RequestMapping("/data")
    @ResponseBody
    public JsonData getRecordData(@RequestParam Long babyId){
        Map<String, Object> dataMap  = new HashMap();
        JsonData jsonData = new JsonData();
        jsonData.setCode(200);
        BabyInfoEntity babyInfo = babyInfoRepo.getOne(babyId);
        dataMap.put("birthday", babyInfo.getBirthday());

        List<BabyRecordEntity> recordList = babyRecordRepo.findByBabyIdOrderByCreateTime(babyId);
        dataMap.put("records", recordList);
        jsonData.setData(dataMap);
        return jsonData;
    }
}
