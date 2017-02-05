package com.jiayouwa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyan on 2016/9/23.
 * <code>
 *     try {
         String message = HttpClientUtils.getObjectFromUrl("http://localhost:8080/400", String.class);
         System.out.println(message);
       }catch (HttpClientErrorException ex){
             ErrorResponse errorResponse = JsonUtils.fromJson(ex.getResponseBodyAsString(), ErrorResponse.class);
             System.out.println(errorResponse);
       }
 * </code>
 */
@Slf4j
public class HttpClientUtils {

    public static int defaultRetryTimes = 3;

    public static int defaultReadTimeOut = 6000;//默认超时时间，毫秒
    
    public static <T extends Object> T getObjectFromUrl(String url , Class<T> type){
        return getObjectFromUrl(url, type, defaultRetryTimes,defaultReadTimeOut);
    }
    
    public static <T extends Object> T getObjectFromUrl(URI  url , Class<T> type ){
        return getObjectFromUrl(url, type, defaultRetryTimes,defaultReadTimeOut);
    }
    
    public static <T extends Object> T postObjectForUrl(URI  url , Object requestBody,  Class<T> type ){
        return postObjectForUrl(url , requestBody, null, type, defaultRetryTimes,defaultReadTimeOut);
    }

    public static <T extends Object> T postObjectForUrl(String url , Object requestBody,  Class<T> type ) throws URISyntaxException {
        return postObjectForUrl(new URI(url ) , requestBody, null,  type, defaultRetryTimes,defaultReadTimeOut);
    }

    public static <T extends Object> T postObjectForUrl(String url , Object requestBody, Map<String,String> headers, Class<T> type)throws URISyntaxException{
    	return postObjectForUrl(new URI(url) , requestBody, headers,  type, defaultRetryTimes,defaultReadTimeOut);
    }
    
    
    public static <T extends Object> T getObjectFromUrl(String url , Class<T> type, int retryTimes,int readTimeOut ){
        RestTemplate restTemplate =  getRestTemplate(defaultReadTimeOut);
        boolean success = false;
        T result = null;
        while(!success && retryTimes >= 0){
            try {
                result = restTemplate.getForObject(url, type);
                success = true;
            }catch (Exception ex){
                log.error("rest template getObjectFromUrl error: ", ex);
                retryTimes--;
                if(retryTimes == 0){
                    throw ex;
                }
            }
        }
        return result;
    }

    public static <T extends Object> T getObjectFromUrl(URI  url , Class<T> type, int retryTimes,int readTimeOut ){
        RestTemplate restTemplate =  getRestTemplate(readTimeOut);
        boolean success = false;
        T result = null;
        while(!success && retryTimes >= 0){
            try {
                result = restTemplate.getForObject(url, type);
                success = true;
            }catch (Exception ex){
                log.error("rest template getObjectFromUrl error: ", ex);
                retryTimes--;
                if(retryTimes == 0){
                    throw ex;
                }
            }
        }
        return result;
    }

    public static <T extends Object> T postObjectForUrl(URI  url , Object requestBody, Map<String,String> headerMap, Class<T> type, int retryTimes,int readTimeOut ){
    	HttpHeaders httpHeaders = new HttpHeaders();
    	if(headerMap != null){
    		Set<String> keySet = headerMap.keySet();
    		for(Iterator<String> it = keySet.iterator();it.hasNext();){
    			String key = it.next();
    			String value = headerMap.get(key);
    			httpHeaders.add(key, value);
    		}
    	}
    	HttpEntity<Object> formEntity = new HttpEntity<Object>(requestBody, httpHeaders);
        RestTemplate restTemplate =  getRestTemplate(readTimeOut);
        boolean success = false;
        T result = null;
        while(!success && retryTimes >= 0){
            try {
                result = restTemplate.postForObject(url, formEntity, type);
                success = true;
            }catch (Exception ex){
                log.error("rest template postObjectForUrl error: ", ex);
                retryTimes--;
                if(retryTimes == 0){
                    throw ex;
                }
            }
        }
        return result;
    }

    public static <T extends Object> T postFormForUrl(String url , Map<String , String> formData,  Class<T> type ) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        for(String key : formData.keySet()){
            map.add(key, formData.get(key));
        }


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate =  getRestTemplate(defaultReadTimeOut);


        return  restTemplate.postForObject( url, request , type );

    }


    public static RestTemplate getRestTemplate(int timeout){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(defaultReadTimeOut);
        factory.setConnectTimeout(6000);
        return new RestTemplate(factory);
    }
   
}
