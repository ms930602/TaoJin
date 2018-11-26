package com.ms.taojin.user.utils;

public class Constants {

	public static int LOGIN_STATE_FAIL = 1;

	public static int LOGIN_STATE_SUCCESS = 0;
	
	//public static String USER_PASSWORD_ENCRYPT_KEY = "ms_center_encrypt";
	public static String USER_PASSWORD_ENCRYPT_KEY = "";
	
	public static String USER_PASSWORD_CHECK_MATCHES = "^^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*_-]+$)(?![a-zA-z\\d]+$)(?![a-zA-z!@#$%^&*_-]+$)(?![\\d!@#$%^&*_-]+$)[a-zA-Z\\d!@#$%^&*_-]+$";

}
