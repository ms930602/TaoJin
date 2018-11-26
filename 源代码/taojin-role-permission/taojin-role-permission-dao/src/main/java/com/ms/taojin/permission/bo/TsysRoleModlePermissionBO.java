/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleModlePermissionEntity;
import com.ms.taojin.permission.mapper.TsysRoleModlePermissionMapper;


/**
 * 业务处理.<p>s
 * @author lansongtao
 * @Date 2017-04-05 09:52:30
 * @since 1.0
 */
@Component
public class TsysRoleModlePermissionBO extends AbstractBaseBO<TsysRoleModlePermissionEntity> {
	
	@Autowired
	private TsysRoleModlePermissionMapper tsysRoleModlePermissionMapper;
	
	@Override
    protected IBaseMapper<TsysRoleModlePermissionEntity> getMapper() {
	    return tsysRoleModlePermissionMapper;
    }
	public List<TsysRoleModlePermissionEntity> queryByRoleIdAndsysId(String roleId,String sysId){
		return tsysRoleModlePermissionMapper.queryByRoleIdAndsysId(roleId, sysId);
	}
	public void insertList(List<TsysRoleModlePermissionEntity> list){
		tsysRoleModlePermissionMapper.insertList(list);
	}
	
	public void deleteByRoleIdAndSysId(String roleId,String sysId){
		tsysRoleModlePermissionMapper.deleteByRoleIdAndSysId(roleId, sysId);
	}
}
