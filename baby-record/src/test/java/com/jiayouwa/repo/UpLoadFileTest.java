package com.jiayouwa.repo;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.StatFileRequest;
import com.qcloud.cos.request.UpdateFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhaoyan on 2017/2/8.
 */

public class UpLoadFileTest {

    long appId = 1253299307;
    String secretId = "AKIDeAUfeRdYq0yjLl5ylLix2xfASXP5bqaE";
    String secretKey = "Tr20lDTYQ5YornmgS3Rf0CD6sSp0ytdB";
    // 设置要操作的bucket
    String bucketName = "babycare";
    // 初始化秘钥信息

    COSClient cosClient = null;

    @Before
    public void init(){
        Credentials cred = new Credentials(appId, secretId, secretKey);

        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如广州(gz), 天津(tj)
        clientConfig.setRegion("tj");

        cosClient = new COSClient(clientConfig, cred);
    }

    @Test
    public void testUpload(){

        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, "/test.jpg", "/Users/zhaoyan/Workspaces/potato/babycare/assets/a.jpg");


        String uploadFileRet  = cosClient.uploadFile(uploadFileRequest);

        System.out.println(uploadFileRet);





    }

    @Test
    public void testUpdate(){
        UpdateFileRequest updateFileRequest = new UpdateFileRequest(bucketName, "/zhaoyan/test.jpg");

        updateFileRequest.setContentType("image/jpeg");
        updateFileRequest.setContentDisposition("");
        String res = cosClient.updateFile(updateFileRequest);
        System.out.println(res);
    }

    @Test
    public void testStatFile(){
        StatFileRequest statFileRequest = new StatFileRequest(bucketName, "/test.jpg");
        String statFileRet = cosClient.statFile(statFileRequest);
        System.out.println(statFileRet);
    }

    @Test
    public void testMkDir(){
        String folderPath = "/2017/02/08/";
        CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, folderPath);
        String res = cosClient.createFolder(createFolderRequest);
        System.out.println(res);
    }

    @Test
    public void testGenFolerName(){
        DateTime dateTime=new DateTime();
        System.out.println(dateTime.toString("/yyyy/MM/dd/"));

    }

}
