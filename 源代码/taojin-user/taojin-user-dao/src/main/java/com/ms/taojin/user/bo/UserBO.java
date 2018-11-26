/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.user.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.user.entity.UserEntity;
import com.ms.taojin.user.mapper.UserExtendMapper;
import com.ms.taojin.user.mapper.UserMapper;

/**
 * 业务处理.
 * <p>
 * 
 * @author lansongtao
 * @Date 2017-04-12 14:08:41
 * @since 1.0
 */
@Component
public class UserBO extends AbstractBaseBO<UserEntity> {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserExtendMapper userExtendMapper;

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	public int updatePassword(Map<String, Object> map) {
		return userExtendMapper.updatePassword(map);
	}

	/**
	 * 修改登录失败次数
	 * 
	 * @param userId
	 * @return
	 */
	public int updateLoginTime(Long userId, Integer faildTime) {
		return userExtendMapper.updateLoginTime(userId, faildTime);
	}

	/**
	 * 根据类型修改用户状态
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @return
	 */
	public int updateStatusByType(List<Long> userIdList, int status) {
		return userExtendMapper.updateStatusByType(userIdList, status);
	}

	/**
	 * 根据类型查询用户ID
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @param status
	 * @return
	 */
	public List<Long> queryIdByType(String typeId, String typeCode, String sysId, int status) {
		return userExtendMapper.queryIdByType(typeId, typeCode, sysId, status);
	}

	/**
	 * 根据类型查询用户ID
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @param status
	 * @return
	 */
	public List<UserEntity> queryListByType(String typeId, List<String> typeCodeList, String sysId, int status) {
		return userExtendMapper.queryListByType(typeId, typeCodeList, sysId, status);
	}

	@Override
	protected IBaseMapper<UserEntity> getMapper() {
		return userMapper;
	}

}
