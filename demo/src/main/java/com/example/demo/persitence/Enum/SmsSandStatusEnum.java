package com.example.demo.persitence.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public enum SmsSandStatusEnum {
    SUCCESS("0000成功"),
    PATAMETER_EMPTY("0001参数为空"),
    SYSTEM_FAILED("0002系统异常"),
    DOT_SUPPORT("0004不支持的手机号段"),
    NETWORK_FAILED("0099网络异常");

    /**
     * 描述
     */
    private String desc;


    public static Map<String, Map<String, Object>> toMap() {
        SmsSandStatusEnum[] ary = SmsSandStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = ary[num].name();
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        SmsSandStatusEnum[] ary = SmsSandStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }
}
