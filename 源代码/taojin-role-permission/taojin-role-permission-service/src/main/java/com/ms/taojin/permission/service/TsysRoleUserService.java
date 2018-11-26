package com.ms.taojin.permission.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysRoleBO;
import com.ms.taojin.permission.bo.TsysRoleUserBO;
import com.ms.taojin.permission.entity.TsysRoleEntity;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;
import com.ms.taojin.pre.api.ISessionHandler;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysRoleUserService")
public class TsysRoleUserService extends BaseService  {

	@Autowired
	private TsysRoleUserBO tsysRoleUserBO;
	@Autowired
	private TsysRoleBO tsysRoleBO;
	@Autowired
	private ISessionHandler sessionHandler;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO reqVO) {
		List<TsysRoleUserEntity> userDetaliList = tsysRoleUserBO.queryByPage(reqVO);
		int dataCount = tsysRoleUserBO.queryCount(reqVO);

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
	public BaseRespVO create(TsysRoleUserEntity tsysRoleUser) {
		tsysRoleUserBO.create(tsysRoleUser);
		return new BaseRespVO();
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysRoleUserEntity tsysRoleUser) {
		tsysRoleUserBO.update(tsysRoleUser);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(TsysRoleUserEntity tsysRoleUser) {
		tsysRoleUserBO.deleteById(tsysRoleUser.getId());
		return new BaseRespVO();
	}

	public List<TsysRoleUserEntity> queryByUserId(String userId) {
		// TODO Auto-generated method stub
		return tsysRoleUserBO.queryByUserId(userId);
	}
	List<TsysRoleUserEntity> queryByRoleId(String roleId){
		return tsysRoleUserBO.queryByRoleId(roleId);
	}
	/**
	 * 绑定用户和角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public BaseRespVO binDing(@Param("roleId") String[] roleId,@Param("userId") String[] userId) {
		
		List<TsysRoleUserEntity> list=new ArrayList<TsysRoleUserEntity>();
		if(userId!=null&&userId.length>0){
			for(int i=0;i<userId.length;i++){
				tsysRoleUserBO.deleteByuserId(userId[i]);
				if (roleId == null || roleId.length == 0) {
					continue;
				}
				for(int j=0;j<roleId.length;j++){
					TsysRoleUserEntity e=new TsysRoleUserEntity();
					e.setUserId(Long.valueOf(userId[i]));
					e.setRoleId(Long.valueOf(roleId[j]));
					list.add(e);
				}
			}
		}
		tsysRoleUserBO.batchSave(list);
		return new BaseRespVO();
	}
	/**
	 * 更具用户id获取所绑定角色
	 * @param userId
	 * @return
	 */
	public ListRespVO getRolesByUserId(@Param("userId") String userId){
		List<TsysRoleEntity> roleList=new ArrayList<TsysRoleEntity>();
		List<TsysRoleUserEntity> list=tsysRoleUserBO.queryByUserId(userId);
		for(TsysRoleUserEntity e:list){
			roleList.add(tsysRoleBO.queryById(e.getRoleId()));
		}
		ListRespVO res=new ListRespVO();
		res.setAaData(roleList);
		return res;
		
	}
	public BaseRespVO delBinDing(@Param("userId") String userId){
		List<Long> userIdList = new ArrayList<Long>();
		userIdList.add(Long.valueOf(userId));
		tsysRoleUserBO.deleteByuserId(userId);
		if(userIdList.size()>0){
			sessionHandler.deleteSession(userIdList);
			}
		return new BaseRespVO();
		
	}
	
	
	

}
