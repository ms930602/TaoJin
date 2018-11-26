/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleModlePermissionEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:52:30
 * @since 1.0
 */
@Repository
public interface TsysRoleModlePermissionMapper extends IBaseMapper<TsysRoleModlePermissionEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysRoleModlePermission		
	 * @return					
	 */	
	java.util.List<TsysRoleModlePermissionEntity> queryByTsysRoleModlePermissionEntity(TsysRoleModlePermissionEntity tsysRoleModlePermission);
	public List<TsysRoleModlePermissionEntity> queryByRoleIdAndsysId(String roleId,String sysId);
	public void insertList(List<TsysRoleModlePermissionEntity> list);
	public void deleteByRoleIdAndSysId(String roleId,String sysId);

}
