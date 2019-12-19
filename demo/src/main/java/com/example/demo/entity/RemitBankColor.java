package com.example.demo.entity;


import lombok.Data;

import javax.persistence.Table;

/**
 * 银行卡主体信息实体
 *
 */
@Data
@Table(name="rp_remit_bank_color")
public class RemitBankColor  {


private static final long serialVersionUID = 1L;

private String id;//银行类型编码

private String bankName;//银行类型编码

private String typeCode;//银行类型code

private String bankType; // 银行行号

private String rgb; // 银行名称

private String bankLogo; // 省份

}
