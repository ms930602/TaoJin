/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ms.taojin.user.entity.UserEntity;

/**
 * Mapper.
 * <p>
 * 
 * @author lansongtao
 * @Date 2017-04-12 14:08:41
 * @since 1.0
 */
public interface UserExtendMapper {

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	int updatePassword(Map<String, Object> map);

	/**
	 * 修改登录错误次数
	 * 
	 * @param userId
	 * @return
	 */
	public int updateLoginTime(@Param("userId") Long userId, @Param("faildTime") Integer faildTime);

	/**
	 * 根据用户ID修改状态
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @return
	 */
	public int updateStatusByType(@Param("userIdList") List<Long> userIdList, @Param("status") int status);

	/**
	 * 根据类型查询用户ID
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @return
	 */
	public List<Long> queryIdByType(@Param("typeId") String typeId, @Param("typeCode") String typeCode, @Param("sysId") String sysId,
	        @Param("status") int status);

	/**
	 * 根据类型查询用户ID
	 * 
	 * @param typeId
	 * @param typeCode
	 * @param sysId
	 * @return
	 */
	public List<UserEntity> queryListByType(@Param("typeId") String typeId, @Param("typeCodeList") List<String> typeCodeList, @Param("sysId") String sysId,
	        @Param("status") int status);

	/**
	 * 查询所有用户类型
	 * 
	 * @return
	 */
	public String[] queryAllTypeId();

}
