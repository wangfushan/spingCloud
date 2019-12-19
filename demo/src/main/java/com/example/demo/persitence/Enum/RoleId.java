/**
 * Project Name:pe-admin
 * File Name:RoleLevel.java
 * Package Name:com.nio.enums
 * Date:2017年7月20日下午9:01:16
 * Copyright (c) 2017, NEXTEV.
 *
 */

package com.example.demo.persitence.Enum;

/**
 * ClassName: RoleLevel <br/>
 * Date: 2017年7月20日 下午9:01:16 <br/>
 * Description: TODO
 *
 * @author dongshun.wang
 * @version
 * @see
 */
public enum RoleId {

	SuperAdmin(0), Admin(1), FinanceAgency(2), CommonUser(3);

	private RoleId(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static String roleName(int id) {

		for (RoleId rId : RoleId.values()) {
			if (rId.getId() == id) {
				return rId.name();
			}
		}
		throw new IllegalArgumentException("无此角色级别");
	}

}
