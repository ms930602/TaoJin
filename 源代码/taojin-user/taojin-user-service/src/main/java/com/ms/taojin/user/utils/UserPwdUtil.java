package com.ms.taojin.user.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.ms.taojin.common.encrypt.MD5Util;
import com.ms.taojin.common.vo.BaseRespVO;

public class UserPwdUtil {

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderPwd(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return MD5Util.EncoderByMd5(Constants.USER_PASSWORD_ENCRYPT_KEY + password);
	}

	/**
	 * 校验密码复杂度
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isLegalPwd(String password) {
		return password.matches(Constants.USER_PASSWORD_CHECK_MATCHES);
	}

	/**
	 * 校验密码复杂度
	 * 
	 * @param password
	 * @return
	 */
	public static BaseRespVO checkPwd(String password) {
		if (password.length() < 6) {
			return BaseRespVO.error("密码长度必须超过6位");
		}

//		if (!isLegalPwd(password)) {
//			return BaseRespVO.error("密码必须包含英文数字及特殊字符");
//		}
		return null;
	}
}
