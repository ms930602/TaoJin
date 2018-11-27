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

import com.ms.taojin.manage.bo.GameBO;
import com.ms.taojin.manage.entity.GameEntity;

import java.util.Date;

/**
 * 游戏 业务处理
 * @author 蒙赛
 * @Date 2018-11-27 15:29:47
 * @since 1.0
 */@Service
public class GameService extends BaseService {

	@Autowired
	private GameBO gameBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<GameEntity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return gameBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		Game query = new Game();
		query.setId(id);
		query.setCreateUserId(user.getUserId());
		return gameBo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param game
	 * @return
	 */
	public Object create(GameEntity game) throws CenterException {
		game.setCreatetime(new Date());
		gameBo.createForValidate(game);
		return game;
	}

	/**
	 * 修改
	 * 
	 * @param game
	 * @return
	 */
	public BaseRespVO update(GameEntity game) throws CenterException {
		int updateCount = gameBo.updateAuthForValidate(game);
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
		int deleteCount = gameBo.batchDeleteAuthById(id,user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
