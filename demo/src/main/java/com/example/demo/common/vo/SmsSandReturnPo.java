package com.example.demo.common.vo;
//import com.example.demo.persitence.Enum.SmsSandStatusEnum;
import com.example.demo.persitence.Enum.SmsSandStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@AllArgsConstructor
public class SmsSandReturnPo {
    /* 返回码 */
    private SmsSandStatusEnum status;
    /* 返回信息 */
    private String message;

}
