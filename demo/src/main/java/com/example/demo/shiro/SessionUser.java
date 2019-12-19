package com.example.demo.shiro;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SessionUser implements Serializable {
	private static final long serialVersionUID = -4743073631282233791L;
	private String account;
	private Integer accountId;
	private Integer roleId;
	private Integer supplierId;
	private String application;
	private String nickName;
	private String cityId;
	private String bankId;
	private Integer regionId;
	private String cityCodes;
	private String companyCodes;
}
