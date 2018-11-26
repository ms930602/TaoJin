/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:51
 * @since 1.0
 */
@Repository
public interface TsysRoleUserMapper extends IBaseMapper<TsysRoleUserEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysRoleUser		
	 * @return					
	 */	
	java.util.List<TsysRoleUserEntity> queryByTsysRoleUserEntity(TsysRoleUserEntity tsysRoleUser);
	List<TsysRoleUserEntity> queryByUserId(String userId);
	List<TsysRoleUserEntity> queryByRoleId(String roleId);
	void deleteByuserId (String userId);

}
