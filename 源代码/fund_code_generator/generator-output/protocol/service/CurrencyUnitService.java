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

import com.ms.taojin.manage.bo.CurrencyUnitBO;
import com.ms.taojin.manage.entity.CurrencyUnitEntity;

import java.util.Date;

/**
 * 游戏货币单位 业务处理
 * @author 蒙赛
 * @Date 2018-11-28 10:40:23
 * @since 1.0
 */@Service
public class CurrencyUnitService extends BaseService {

	@Autowired
	private CurrencyUnitBO currencyUnitBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<CurrencyUnitEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return currencyUnitBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		CurrencyUnitEntity query = new CurrencyUnitEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return currencyUnitBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param currencyUnit
	 * @return
	 */
	public Object create(CurrencyUnitEntity currencyUnit) throws CenterException {
		currencyUnit.setCreatetime(new Date());
		currencyUnitBo.createForValidate(currencyUnit);
		return currencyUnit;
	}

	/**
	 * 修改
	 * 
	 * @param currencyUnit
	 * @return
	 */
	public BaseRespVO update(CurrencyUnitEntity currencyUnit) throws CenterException {
		int updateCount = currencyUnitBo.updateAuthForValidate(currencyUnit);
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
		int deleteCount = currencyUnitBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
