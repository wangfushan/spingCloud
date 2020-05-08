package com.example.demo.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
@AllArgsConstructor
public enum payCodeEnums {
    SUCCESS(00, "成功"),
    WAITING(01, "处理中"),
    DEFEATED(02, "失败"),
    REFUNDED(04, "已退款"),
    WAITING_REFUNDED(05, "退款处理中");
    private Integer code;
    private String message;

    public static payCodeEnums getEnum(String name) {
        payCodeEnums[] arry = payCodeEnums.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

    public static String getEnum12(String name) {
        payCodeEnums[] arry = payCodeEnums.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i].code.toString();
            }
        }
        return null;
    }
}