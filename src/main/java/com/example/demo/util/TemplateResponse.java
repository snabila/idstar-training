package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateResponse {
    public Map success(Object data) {
        Map map = new HashMap();
        map.put("data", data);
        map.put("message", "Success");
        map.put("status", "200");
        return map;
    }

    public Map error(String msg, String status) {
        Map map = new HashMap();
        map.put("message", msg);
        map.put("status", status);
        return map;
    }
}
