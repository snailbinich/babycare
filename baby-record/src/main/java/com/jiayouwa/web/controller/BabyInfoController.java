package com.jiayouwa.web.controller;

import com.jiayouwa.component.Gender;
import com.jiayouwa.persistent.model.BabyInfoEntity;
import com.jiayouwa.persistent.model.BabyRecordEntity;
import com.jiayouwa.persistent.model.ProvinceEntity;
import com.jiayouwa.persistent.repos.BabyInfoRepo;
import com.jiayouwa.persistent.repos.ProvinceRepo;
import com.jiayouwa.service.BabyService;
import com.jiayouwa.service.CosFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyan on 2017/1/25.
 */
@Controller
@RequestMapping("/baby")
@Slf4j
public class BabyInfoController {

    @Autowired
    private BabyInfoRepo babyInfoRepo;

    @Autowired
    private BabyService babyService;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private CosFileUploadService uploadService;


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String  addBabyForm(Map<String,Object> map){
        List<ProvinceEntity> provinces =  provinceRepo.findAll();
        map.put("provinces", provinces);

        return "/addBaby";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addBaby(@RequestParam String nickName,
                          @RequestParam int gender,
                          @RequestParam int provinceId,
                          @RequestParam(value = "headImg", required = false) MultipartFile headImg,
                          @RequestParam(required = false,defaultValue = "0") int birthHeight,
                          @RequestParam(required = false,defaultValue = "0") int birthWeight,
                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
                          @SessionAttribute("uid") Long uid,
                          HttpServletRequest request){

        List<BabyInfoEntity> existBabies = babyInfoRepo.getBabiesByUserId(uid);
        if(existBabies.size() >= 20){
            request.setAttribute("ERROR","添加的宝宝信息不能超过20个");
            return  "redirect:/baby/list";
        }

        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = headImg.getOriginalFilename();
        String suffix = "";
        if (fileName.lastIndexOf(".") != -1){
            suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        }

        headImg.getContentType();
        long fileSize = 10 * 1024 * 1024;
        if(headImg.getSize() >= fileSize){
            //TODO 上传的文件大小过大
            log.info("上传的文件过大,{}"+headImg.getSize());
        }
        //TODO 校验上传的文件格式
        String headImgPrefix="/head";
        String folder = uploadService.generateFileFolder(headImgPrefix);
        String uploadFileName = uploadService.generateFileName(suffix);
        try {
            uploadService.uploadHeadImage(folder, uploadFileName, headImg.getContentType(), headImg.getBytes());

        } catch (IOException e) {
            log.error("上传图片出错", e);
        }


        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }




        BabyInfoEntity newBabyInfo = new BabyInfoEntity();
        newBabyInfo.setNickName(nickName);
        newBabyInfo.setBirthday(birthday);
        newBabyInfo.setHeadImg(folder+uploadFileName);
        if (gender != Gender.FEMALE.getCode() && gender != Gender.MALE.getCode()){
            gender = Gender.FEMALE.getCode();
        }
        newBabyInfo.setGender(gender);
        newBabyInfo.setProvinceId(provinceId);

        babyService.addBaby(newBabyInfo, uid);
        return "redirect:/baby/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listBaby(Map<String,Object> map, @SessionAttribute("uid") Long uid){
        List<BabyInfoEntity> babies = babyInfoRepo.getBabiesByUserId(uid);
        map.put("babies", babies);
        return "/listBaby";
    }



}
