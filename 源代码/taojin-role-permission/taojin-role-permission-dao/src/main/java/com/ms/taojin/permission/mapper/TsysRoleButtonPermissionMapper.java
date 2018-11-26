/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleButtonPermissionEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:51
 * @since 1.0
 */
@Repository
public interface TsysRoleButtonPermissionMapper extends IBaseMapper<TsysRoleButtonPermissionEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysRoleButtonPermission		
	 * @return					
	 */	
	java.util.List<TsysRoleButtonPermissionEntity> queryByTsysRoleButtonPermissionEntity(TsysRoleButtonPermissionEntity tsysRoleButtonPermission);
	public List<TsysRoleButtonPermissionEntity> queryByRoleId(String roleId);
	public void deleteByRoleIdAndSysId(String roleId,String sysId);
	public void insertList(List<TsysRoleButtonPermissionEntity> list);
	public List<TsysRoleButtonPermissionEntity> queryByRoleId(String roleId,String sysId);
}
