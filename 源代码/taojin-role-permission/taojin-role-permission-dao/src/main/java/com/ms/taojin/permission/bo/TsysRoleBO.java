/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysRoleEntity;
import com.ms.taojin.permission.mapper.TsysRoleMapper;


/**
 * 业务处理.<p>
 * @author lansongtao
 * @Date 2017-04-06 13:08:14
 * @since 1.0
 */
@Component
public class TsysRoleBO extends AbstractBaseBO<TsysRoleEntity> {
	
	@Autowired
	private TsysRoleMapper tsysRoleMapper;
	
	@Override
    protected IBaseMapper<TsysRoleEntity> getMapper() {
	    return tsysRoleMapper;
    }
	public java.util.List<TsysRoleEntity> queryByTsysRoleEntity(TsysRoleEntity tsysRole){
		return tsysRoleMapper.queryByTsysRole(tsysRole);
	}
}
