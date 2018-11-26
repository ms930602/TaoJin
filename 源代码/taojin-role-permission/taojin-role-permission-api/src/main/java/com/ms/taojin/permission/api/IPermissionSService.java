package com.ms.taojin.permission.api;

import java.util.List;

import com.ms.taojin.permission.entity.PermissonInfo;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;



public interface IPermissionSService {
	
	public PermissonInfo initMenusService(String userId,String sysId,String flag);
	public List<TsysRoleUserEntity> getUsersByRoleId(String roleId);
	public void createTsysRoleUser(Long userId,Long roleId);
}
