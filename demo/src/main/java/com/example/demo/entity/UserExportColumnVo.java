package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author fan.fc
 * @create 2019/03/25
 * @description TODO
 */
@Data
public class UserExportColumnVo {

    private String userNo;

    private String userName;

    private String userType;

    private String liquidationMode;

    private String status;

    private Date createTime;

    private Date activationTime;

    private String firstAgentNo;

    private String firstAgentName;

    private String secondAgentNo;

    private String secondAgentName;

    private String merchantSaleName;

    private String parentAgentNo;

    private String parentAgentName;

    private String agentSaleName;

}
