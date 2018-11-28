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

import com.ms.taojin.manage.bo.UserGameBO;
import com.ms.taojin.manage.entity.UserGameEntity;

import java.util.Date;

/**
 *  业务处理
 * @author 蒙赛
 * @Date 2018-11-28 10:40:43
 * @since 1.0
 */@Service
public class UserGameService extends BaseService {

	@Autowired
	private UserGameBO userGameBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<UserGameEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return userGameBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		UserGameEntity query = new UserGameEntity();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return userGameBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param userGame
	 * @return
	 */
	public Object create(UserGameEntity userGame) throws CenterException {
		userGame.setCreatetime(new Date());
		userGameBo.createForValidate(userGame);
		return userGame;
	}

	/**
	 * 修改
	 * 
	 * @param userGame
	 * @return
	 */
	public BaseRespVO update(UserGameEntity userGame) throws CenterException {
		int updateCount = userGameBo.updateAuthForValidate(userGame);
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
		int deleteCount = userGameBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
