package com.ms.taojin.user.api.enums;

/**
 * 账单状态
 * 
 * (0:停用,1:启用)
 *
 * @author lansongtao
 * @Date 2017年5月10日
 * @since 1.0
 */
public enum UserStatus {

	/** 停用 **/
	DISABLE(0),
	/** 启用 **/
	OPERATION(1);

	private int code;

	UserStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static UserStatus getUserStatus(int code) {
		for (UserStatus value : values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}