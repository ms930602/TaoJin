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

import com.ms.taojin.manage.bo.OpinionBO;
import com.ms.taojin.manage.entity.OpinionEntity;

import java.util.Date;

/**
 *  业务处理
 * @author 蒙赛
 * @Date 2018-11-26 11:58:14
 * @since 1.0
 */@Service
public class OpinionService extends BaseService {

	@Autowired
	private OpinionBO opinionBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<OpinionEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
			reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return opinionBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		OpinionEntity query = new OpinionEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return opinionBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param opinion
	 * @return
	 */
	public Object create(OpinionEntity opinion) throws CenterException {
		opinion.setCreatetime(new Date());
		opinion.setHandleType("1");
		opinionBo.createForValidate(opinion);
		return opinion;
	}

	/**
	 * 修改
	 * 
	 * @param opinion
	 * @return
	 */
	public BaseRespVO update(OpinionEntity opinion) throws CenterException {
		int updateCount = opinionBo.updateAuthForValidate(opinion);
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
		int deleteCount = opinionBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
