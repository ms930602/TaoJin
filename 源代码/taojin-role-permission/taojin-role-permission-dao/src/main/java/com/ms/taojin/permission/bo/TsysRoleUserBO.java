/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;
import com.ms.taojin.permission.mapper.TsysRoleUserMapper;


/**
 * 业务处理.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:51
 * @since 1.0
 */
@Component
public class TsysRoleUserBO extends AbstractBaseBO<TsysRoleUserEntity> {
	
	@Autowired
	private TsysRoleUserMapper tsysRoleUserMapper;
	
	@Override
    protected IBaseMapper<TsysRoleUserEntity> getMapper() {
	    return tsysRoleUserMapper;
    }
	public List<TsysRoleUserEntity> queryByUserId(String userId){
		return tsysRoleUserMapper.queryByUserId(userId);
	}
	public List<TsysRoleUserEntity> queryByRoleId(String roleId){
		return tsysRoleUserMapper.queryByRoleId(roleId);
	}
	public void deleteByuserId (String userId){
		 tsysRoleUserMapper.deleteByuserId(userId);
	}
}
