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

import com.ms.taojin.manage.bo.ItemBO;
import com.ms.taojin.manage.entity.ItemEntity;

import java.util.Date;

/**
 * 物品信息 业务处理
 * @author 蒙赛
 * @Date 2018-11-28 09:30:09
 * @since 1.0
 */@Service
public class ItemService extends BaseService {

	@Autowired
	private ItemBO itemBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<ItemEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return itemBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		ItemEntity query = new ItemEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return itemBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param item
	 * @return
	 */
	public Object create(ItemEntity item) throws CenterException {
		item.setCreatetime(new Date());
		itemBo.createForValidate(item);
		return item;
	}

	/**
	 * 修改
	 * 
	 * @param item
	 * @return
	 */
	public BaseRespVO update(ItemEntity item) throws CenterException {
		int updateCount = itemBo.updateAuthForValidate(item);
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
		int deleteCount = itemBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
