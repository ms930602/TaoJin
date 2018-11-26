package com.ms.taojin.user.api;

import java.util.List;

import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.user.entity.UserEntity;

public interface IUserService {

	/**
	 * 新增
	 * 
	 * @param user
	 * @return
	 * @throws CenterException
	 */
	UserEntity create(UserEntity user) throws CenterException;

	/**
	 * 修改详细信息
	 * 
	 * @param reqVO
	 * @return
	 * @throws CenterException
	 */
	BaseRespVO update(UserEntity user) throws CenterException;

	/**
	 * 删除
	 * 
	 * @param userId
	 * @return
	 * @throws CenterException
	 */
	BaseRespVO delete(Long[] userId) throws CenterException;

	/**
	 * 根据类型修改用户状态
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @param status
	 * @return
	 * @throws CenterException
	 */
	BaseRespVO updateStatusByType(String typeId, String typeCode, String sysId, int status) throws CenterException;

	/**
	 * 查询用户列表
	 * 
	 * @param condition
	 * @return
	 */
	List<UserEntity> queryListByEntity(UserEntity condition);

	/**
	 * 根据类型查询用户列表
	 * 
	 * @param typeId
	 * @param typeCodeList
	 * @param sysId
	 * @return
	 */
	List<UserEntity> queryListByType(String typeId, List<String> typeCodeList, String sysId);
	
	/**
	 * 查询全部用户列表,包括未启用的用户
	 * 
	 * @param condition
	 * @return
	 */
	List<UserEntity> queryAllListByEntity(UserEntity condition);

}
