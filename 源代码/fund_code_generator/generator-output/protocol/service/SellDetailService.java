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

import com.ms.taojin.manage.bo.SellDetailBO;
import com.ms.taojin.manage.entity.SellDetailEntity;

import java.util.Date;

/**
 *  业务处理
 * @author 蒙赛
 * @Date 2018-11-28 10:40:41
 * @since 1.0
 */@Service
public class SellDetailService extends BaseService {

	@Autowired
	private SellDetailBO sellDetailBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<SellDetailEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return sellDetailBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		SellDetailEntity query = new SellDetailEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return sellDetailBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param sellDetail
	 * @return
	 */
	public Object create(SellDetailEntity sellDetail) throws CenterException {
		sellDetail.setCreatetime(new Date());
		sellDetailBo.createForValidate(sellDetail);
		return sellDetail;
	}

	/**
	 * 修改
	 * 
	 * @param sellDetail
	 * @return
	 */
	public BaseRespVO update(SellDetailEntity sellDetail) throws CenterException {
		int updateCount = sellDetailBo.updateAuthForValidate(sellDetail);
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
		int deleteCount = sellDetailBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
