package com.jiayouwa.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.UpdateFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by zhaoyan on 2017/2/8.
 */
@Service
@Slf4j
public class CosFileUploadService {

    long appId = 1253299307;
    String secretId = "AKIDeAUfeRdYq0yjLl5ylLix2xfASXP5bqaE";
    String secretKey = "Tr20lDTYQ5YornmgS3Rf0CD6sSp0ytdB";
    // 设置要操作的bucket
    String bucketName = "babycare";


    COSClient cosClient = null;

    @PostConstruct
    public void initService(){
        Credentials cred = new Credentials(appId, secretId, secretKey);

        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如广州(gz), 天津(tj)
        clientConfig.setRegion("tj");

        cosClient = new COSClient(clientConfig, cred);


    }

    public String generateFileName(String suffix){
        UUID uuid  = UUID.randomUUID();
        return uuid.toString()+suffix;

    }

    public String generateFileFolder(String prefix){
        DateTime dateTime=new DateTime();
        return  prefix+dateTime.toString("/yyyy/MM/dd/");
    }

    public void uploadHeadImage(String folder, String fileName, String contentType, byte[] imgContent){

        CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, folder);
        String res = cosClient.createFolder(createFolderRequest);
        log.info("创建文件夹:{}",res);



        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,folder+fileName, imgContent);
        String result  = cosClient.uploadFile(uploadFileRequest);
        log.info("上传头像结果{}", result);

        UpdateFileRequest updateFileRequest = new UpdateFileRequest(bucketName, folder+fileName);

        updateFileRequest.setContentType(contentType);
        updateFileRequest.setContentDisposition("");
        result = cosClient.updateFile(updateFileRequest);
        log.info("更新文件的结果:{}", result);


    }

    public String getMimeType(String fileName){
        fileName = fileName.toLowerCase();
        if(fileName.endsWith("jpg") || fileName.endsWith("jpeg")){
            return "image/jpeg";
        }
        if(fileName.endsWith("png")){
            return "image/png";
        }

        return "image/jpeg";
    }
}
