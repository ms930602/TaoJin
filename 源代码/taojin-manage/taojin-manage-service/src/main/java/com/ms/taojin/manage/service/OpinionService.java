package com.ms.taojin.manage.service;

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
		return opinionBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		return opinionBo.queryById(id);
	}

	/**
	 * 新增
	 * 
	 * @param opinion
	 * @return
	 */
	public Object create(OpinionEntity opinion) throws CenterException {
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
		int updateCount = opinionBo.updateForValidate(opinion);
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
		int deleteCount = opinionBo.batchDeleteById(id);
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
