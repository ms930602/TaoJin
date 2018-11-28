package com.ms.taojin.manage.service;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.service.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;

import com.ms.taojin.manage.bo.UserAccountNumberBO;
import com.ms.taojin.manage.entity.UserAccountNumberEntity;

import java.util.Date;

/**
 *  业务处理
 * @author 蒙赛
 * @Date 2018-11-28 09:30:28
 * @since 1.0
 */@Service
public class UserAccountNumberService extends BaseService {

	@Autowired
	private UserAccountNumberBO userAccountNumberBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<UserAccountNumberEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return userAccountNumberBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		UserAccountNumberEntity query = new UserAccountNumberEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return userAccountNumberBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param userAccountNumber
	 * @return
	 */
	public Object create(UserAccountNumberEntity userAccountNumber) throws CenterException {
		userAccountNumber.setCreatetime(new Date());
		userAccountNumberBo.createForValidate(userAccountNumber);
		return userAccountNumber;
	}

	/**
	 * 修改
	 * 
	 * @param userAccountNumber
	 * @return
	 */
	public BaseRespVO update(UserAccountNumberEntity userAccountNumber) throws CenterException {
		int updateCount = userAccountNumberBo.updateAuthForValidate(userAccountNumber);
		if(updateCount > 0){
			return new BaseRespVO();
		}else{
			return new BaseRespVO(2,"没有要修改的记录！");
		}
		
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(@Param("id") Long[] id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		int deleteCount = userAccountNumberBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
