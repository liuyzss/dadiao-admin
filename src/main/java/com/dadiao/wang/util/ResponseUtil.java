package com.dadiao.wang.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyang on 2017/5/21.
 */
public class ResponseUtil {
    public static Map<String, Object> success(Object data, String msg) {
        return response("0000", data, msg);
    }

    public static Map<String, Object> success(Object data) {
        return success(data, null);
    }

    public static Map<String, Object> success() {
        return success(null, null);
    }


    public static Map<String, Object> response(String code, Object data, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("msg", msg);
        map.put("code", code);
        return map;
    }
}
