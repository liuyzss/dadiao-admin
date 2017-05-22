package com.dadiao.wang.util;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/21.
 */
public class ResponseUtil {
    public static Map<String,Object> success(Object data,String msg){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data",data);
        map.put("msg",msg);
        map.put("code","0000");
        return  map;
    }
    public static Map<String,Object> success(Object data){
        return success(data,null);
    }

    public static Map<String,Object> success(){
        return success(null,null);
    }
}
