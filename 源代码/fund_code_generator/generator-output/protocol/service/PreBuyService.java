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

import com.ms.taojin.manage.bo.PreBuyBO;
import com.ms.taojin.manage.entity.PreBuyEntity;

import java.util.Date;

/**
 * 预买入 业务处理
 * @author 蒙赛
 * @Date 2018-11-28 10:40:30
 * @since 1.0
 */@Service
public class PreBuyService extends BaseService {

	@Autowired
	private PreBuyBO preBuyBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<PreBuyEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return preBuyBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		PreBuyEntity query = new PreBuyEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return preBuyBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param preBuy
	 * @return
	 */
	public Object create(PreBuyEntity preBuy) throws CenterException {
		preBuy.setCreatetime(new Date());
		preBuyBo.createForValidate(preBuy);
		return preBuy;
	}

	/**
	 * 修改
	 * 
	 * @param preBuy
	 * @return
	 */
	public BaseRespVO update(PreBuyEntity preBuy) throws CenterException {
		int updateCount = preBuyBo.updateAuthForValidate(preBuy);
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
		int deleteCount = preBuyBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
