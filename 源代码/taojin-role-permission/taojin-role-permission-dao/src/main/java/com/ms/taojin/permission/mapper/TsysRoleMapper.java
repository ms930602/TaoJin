/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-06 13:08:14
 * @since 1.0
 */
@Repository
public interface TsysRoleMapper extends IBaseMapper<TsysRoleEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysRole		
	 * @return					
	 */	
	java.util.List<TsysRoleEntity> queryByTsysRole(TsysRoleEntity tsysRole);
	

}
