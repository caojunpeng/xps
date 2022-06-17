package com.cao.xps.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 标准返回json格式
 */
public class ResponseUtil {
    // 请求标识
    public static final int RESULT_ERROR = 500;

    public static Logger logger=LoggerFactory.getLogger(ResponseUtil.class);


    /**
     * 成功时返回json
     * @param message
     * @param data
     * @return
     */
    public static String printJson(String message,Object data){
        Map<String, Object> printMap = new HashMap<>();
        printMap.put("code", 200);
        printMap.put("message", message);
        if(data==null){
            printMap.put("data", "");
        }else{
            printMap.put("data", data);
        }
        ObjectMapper mapper = new ObjectMapper();
        String returnValue = "";
        try {
            returnValue = mapper.writeValueAsString(printMap);
        } catch (JsonProcessingException e) {
            logger.error("json转换失败"+e.getMessage(),e);
        }
        return returnValue;
    }

    public static String printFailJson(int errorCode,String message,Object data){
        Map<String, Object> printMap = new HashMap<>();
        printMap.put("code", errorCode);
        printMap.put("message", message);
        if(data==null){
            printMap.put("data", "system no data");
        }else{
            printMap.put("data", data);
        }
        ObjectMapper mapper = new ObjectMapper();
        String returnValue = "";
        try {
            returnValue = mapper.writeValueAsString(printMap);
        } catch (JsonProcessingException e) {
            logger.error("json转换失败"+e.getMessage(),e);
        }
        return returnValue;
    }
}
