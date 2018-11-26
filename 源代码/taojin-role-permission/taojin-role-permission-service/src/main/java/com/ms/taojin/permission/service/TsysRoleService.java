package com.ms.taojin.permission.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.service.ThreadContext;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysRoleBO;
import com.ms.taojin.permission.bo.TsysRoleUserBO;
import com.ms.taojin.permission.entity.TsysRoleEntity;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysRoleService")
public class TsysRoleService extends BaseService {

	@Autowired
	private TsysRoleBO tsysRoleBO;
	@Autowired
	private TsysRoleUserBO tsysRoleUserBO;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<TsysRoleEntity> reqVO) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();
		TsysRoleEntity sysRoleEntity = reqVO.getWhereCondition();
		if(sysRoleEntity == null) sysRoleEntity = new TsysRoleEntity();
		sysRoleEntity.setSysId(Long.parseLong(sessionUser.getSysId()));
		sysRoleEntity.setTypeCode(sessionUser.getTypeCode());
		sysRoleEntity.setTypeId(sessionUser.getTypeId());
		reqVO.setWhereCondition(sysRoleEntity);
				
		/*
		List<TsysRoleEntity> userDetaliList = tsysRoleBO.queryByPage(reqVO);
				
		int dataCount = tsysRoleBO.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(userDetaliList);
		respVO.setDataCount(dataCount);

		return respVO;*/
		
		return tsysRoleBO.queryPageAutomatic(reqVO);
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public Object create(TsysRoleEntity tsysRole) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();
		tsysRole.setTypeId(sessionUser.getTypeId());
		tsysRole.setTypeCode(sessionUser.getTypeCode());
		tsysRole.setCreatetime(new Date());
		tsysRole.setSysId(Long.parseLong(sessionUser.getSysId()));
		TsysRoleEntity entity = new TsysRoleEntity();
		entity.setName(tsysRole.getName());
		if(sessionUser!=null){
			entity.setSysId(Long.parseLong(sessionUser.getSysId()));
			tsysRole.setSysId(Long.parseLong(sessionUser.getSysId()));
		}
		//entity.setSysId(tsysRole.getSysId());
		List<TsysRoleEntity> role1 = tsysRoleBO.queryByTsysRoleEntity(entity);
		if (role1 != null) {
			if(role1.size()>0)
			return new BaseRespVO(2, "角色名重复");
		}
		tsysRoleBO.create(tsysRole);
		return tsysRole;
	}
	
	
	public Object createRole(TsysRoleEntity tsysRole) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();
		
		ListRespVO respVO = new ListRespVO();
		tsysRole.setCreatetime(new Date());
		TsysRoleEntity entity = new TsysRoleEntity();
		entity.setName(tsysRole.getName());
		if(sessionUser!=null){
			entity.setSysId(Long.parseLong(sessionUser.getSysId()));
			tsysRole.setSysId(Long.parseLong(sessionUser.getSysId()));
		}
			
		List<TsysRoleEntity> role1 = tsysRoleBO.queryByTsysRoleEntity(entity);
		if (role1 != null) {
			if(role1.size()>0){
				respVO.setMsg("角色名重复");
				respVO.setState(2);
				return respVO;
			}
		}
		tsysRole.setTypeCode(sessionUser.getTypeCode());
		tsysRole.setTypeId(sessionUser.getTypeId());
		tsysRole.setCreatetime(new Date());
		tsysRoleBO.create(tsysRole);
		return tsysRole;
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysRoleEntity tsysRole) {
		tsysRoleBO.update(tsysRole);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(@Param("roleId") long[] roleId) {
		for(int i=0;i<roleId.length;i++){
			List<TsysRoleUserEntity> entiList=tsysRoleUserBO.queryByRoleId(roleId[i]+"");
			if(entiList!=null&&entiList.size()>0){
				return new BaseRespVO(2, "请解绑相关用户角色");
			}
		}
		
		
		tsysRoleBO.batchDeleteById(roleId);
		return new BaseRespVO();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TsysRoleEntity queryById(long id){
		return tsysRoleBO.queryById(id);
	}
	

}
