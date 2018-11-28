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

import com.ms.taojin.manage.bo.PreSellBO;
import com.ms.taojin.manage.entity.PreSellEntity;

import java.util.Date;

/**
 * 预卖出 业务处理
 * @author 蒙赛
 * @Date 2018-11-28 09:30:14
 * @since 1.0
 */@Service
public class PreSellService extends BaseService {

	@Autowired
	private PreSellBO preSellBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<PreSellEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return preSellBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		PreSellEntity query = new PreSellEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return preSellBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param preSell
	 * @return
	 */
	public Object create(PreSellEntity preSell) throws CenterException {
		preSell.setCreatetime(new Date());
		preSellBo.createForValidate(preSell);
		return preSell;
	}

	/**
	 * 修改
	 * 
	 * @param preSell
	 * @return
	 */
	public BaseRespVO update(PreSellEntity preSell) throws CenterException {
		int updateCount = preSellBo.updateAuthForValidate(preSell);
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
		int deleteCount = preSellBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
