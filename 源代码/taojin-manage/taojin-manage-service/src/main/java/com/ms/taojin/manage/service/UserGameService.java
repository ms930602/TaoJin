package com.ms.taojin.manage.service;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.service.ThreadContext;
import com.ms.taojin.manage.bo.GameBO;
import com.ms.taojin.manage.entity.GameEntity;
import org.apache.axis.utils.SessionUtils;
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
 * @Date 2018-11-28 09:30:25
 * @since 1.0
 */@Service
public class UserGameService extends BaseService {

	@Autowired
	private UserGameBO userGameBo;

	@Autowired
	private GameBO gameBo;

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
	 * @param id
	 * @return
	 */
	public Object create(@Param("gameId") Long id) throws CenterException {
		if(id==null)return new BaseRespVO(99,"参数不能为空！");
		SessionUser user = ThreadContext.getSessionloginUser();

		UserGameEntity query = new UserGameEntity();
		query.setGameId(id);
		query.setCreateUserId(user.getUserId());
		UserGameEntity haveObj = userGameBo.queryByEntity(query);

		if(haveObj!= null){
			return new BaseRespVO(0,"该游戏以添加哦！");
		}

		GameEntity ge = gameBo.queryById(id);
		if(ge == null)return new BaseRespVO(99,"此游戏以被删除！");
		UserGameEntity saveEntity = new UserGameEntity();
		saveEntity.setFirstCode(ge.getFirstCode());
		saveEntity.setGameId(id);
		saveEntity.setName(ge.getName());
		saveEntity.setType(ge.getType());
		saveEntity.setColumnA(ge.getColumnA());
		saveEntity.setColumnB(ge.getColumnB());
		saveEntity.setColumnC(ge.getColumnC());
		saveEntity.setColumnD(ge.getColumnD());
		saveEntity.setCreatetime(new Date());
		saveEntity.setCreateUserId(user.getUserId());
		saveEntity.setCreateUserName(user.getNickName());
		saveEntity.setSort(0L);
		userGameBo.createForValidate(saveEntity);
		return new BaseRespVO(0,"添加成功！");
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
