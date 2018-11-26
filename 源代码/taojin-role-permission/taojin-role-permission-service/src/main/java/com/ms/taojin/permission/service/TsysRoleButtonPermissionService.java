package com.ms.taojin.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysRoleButtonPermissionBO;
import com.ms.taojin.permission.entity.TsysRoleButtonPermissionEntity;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysRoleButtonPermissionService")
public class TsysRoleButtonPermissionService extends BaseService {

	@Autowired
	private TsysRoleButtonPermissionBO tsysRoleButtonPermissionBO;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO reqVO) {
		List<TsysRoleButtonPermissionEntity> userDetaliList = tsysRoleButtonPermissionBO.queryByPage(reqVO);
		int dataCount = tsysRoleButtonPermissionBO.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(userDetaliList);
		respVO.setDataCount(dataCount);

		return respVO;
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO create(TsysRoleButtonPermissionEntity tsysRoleButtonPermission) {
		tsysRoleButtonPermissionBO.create(tsysRoleButtonPermission);
		return new BaseRespVO();
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysRoleButtonPermissionEntity tsysRoleButtonPermission) {
		tsysRoleButtonPermissionBO.update(tsysRoleButtonPermission);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(TsysRoleButtonPermissionEntity tsysRoleButtonPermission) {
		tsysRoleButtonPermissionBO.deleteById(tsysRoleButtonPermission.getId());
		return new BaseRespVO();
	}
	/**
	 * 根据角色拿到相关组件信息
	 * @param roleId
	 * @return
	 */
	public List<TsysRoleButtonPermissionEntity> queryByRoleId(String roleId,String sysId){
		return tsysRoleButtonPermissionBO.queryByRoleId(roleId,sysId);
	}

}
