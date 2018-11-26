package com.ms.taojin.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.Constants;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.service.ThreadContext;
import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.pre.api.ISessionHandler;
import com.ms.taojin.user.bo.UserBO;
import com.ms.taojin.user.entity.UserEntity;
import com.ms.taojin.user.utils.UserPwdUtil;

/**
 * 业务处理.
 * <p>
 * 
 * @author lansongtao
 * @Date 2017-04-12 14:08:41
 * @since 1.0
 */
@Service
public class UserService extends BaseService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserBO userBo;
	@Autowired(required = false)
	private ISessionHandler sessionHandler;
	@Autowired
	RedisTemplate<String, List> redisTemplates;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<UserEntity> reqVO) {
		ListRespVO resVO = userBo.queryPageAutomatic(reqVO);
		if (reqVO == null || resVO.getAaData() == null) {
			return null;
		}
		return userBo.queryPageAutomatic(reqVO);
	}

	/**
	 * 校验登录名是否重复
	 * 
	 * @param loginName
	 * @return
	 */
	public BaseRespVO checkLoginName(@Param("loginName") String loginName) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();

		UserEntity entity = new UserEntity();
		entity.setLoginName(loginName);
		entity.setSysId(sessionUser.getSysId());

		entity = userBo.queryByEntity(entity);
		if (entity != null) {
			return new BaseRespVO(2, "登录名已存在：" + loginName);
		}

		return new BaseRespVO();
	}

	/**
	 * 新增
	 * 
	 * @param user
	 * @return Object
	 * @throws CenterException
	 */
	public Object create(UserEntity user) throws CenterException {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();

		if (StringUtils.isEmpty(user.getLoginName())) {
			return new BaseRespVO(9, "登录名称不合法");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			// 默认密码
			user.setPassword("123456");
		}
		// 密码MD5加密
		try {
			user.setPassword(UserPwdUtil.encoderPwd(user.getPassword()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("密码MD5加密是出错", e);
			return new BaseRespVO(99, "密码MD5加密是出错");
		}
		// 状态默认是激活
		if (user.getStatus() == null) {
			user.setStatus(1);
		}

		UserEntity entity = new UserEntity();
		entity.setLoginName(user.getLoginName());
		entity.setSysId(sessionUser.getSysId());
		if (userBo.queryByEntity(entity) != null) {
			return new BaseRespVO(2, "登录名重复");
		}

		userBo.createForValidate(user);
		return user;
	}

	/**
	 * 修改详细信息
	 * 
	 * @param user
	 * @return
	 * @throws CenterException
	 */
	public BaseRespVO update(UserEntity user) throws CenterException {
		if (user.getPassword() != null) {
			if (user.getPassword().equals("")) {
				user.setPassword(null);
			}
		}
		if (user.getStatus() != null) {
			if (user.getStatus() == 99 || user.getStatus() == 0) {
				// TODO xuwei
				if (user.getStatus() == 99)
					user.setStatus(null);
				List<Long> userIdList = new ArrayList<Long>();
				if (user.getUserId() != null) {
					userIdList.add(user.getUserId());
				}
				if (userIdList.size() > 0)
					sessionHandler.deleteSession(userIdList);
			}
		}
		if (user.getPassword() != null) {
			try {
				user.setPassword(UserPwdUtil.encoderPwd(user.getPassword()));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				logger.error("密码加密是出错", e);
				return new BaseRespVO(99, "密码加密是出错");
			}
		}
		userBo.updateForValidate(user);
		return new BaseRespVO();
	}

	/**
	 * 状态修改
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public BaseRespVO updateStatus(@Param("userId") long userId, @Param("status") int status) {
		UserEntity entity = new UserEntity();
		entity.setUserId(userId);
		entity.setStatus(status);

		int count = userBo.update(entity);

		// 状态修改为注销的时候，调用网关，删除用户登录会话
		if (status != 0 && count > 0) {
			List<Long> userIdList = new ArrayList<Long>();
			userIdList.add(userId);
			sessionHandler.deleteSession(userIdList);
		}

		return new BaseRespVO();
	}

	/**
	 * 修改详细信息
	 * 
	 * @param user
	 * @return
	 */
	public BaseRespVO updateMyDetail(UserEntity user) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();

		if (sessionUser == null) {
			return new BaseRespVO(99, "无法获取登录用户信息");
		}
		// 只能修改自己
		user.setUserId(sessionUser.getUserId());
		// 不能修改密码
		user.setPassword(null);
		userBo.update(user);
		return new BaseRespVO();
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public BaseRespVO updateMyPassword(@Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword) {
		SessionUser sessionUser = ThreadContext.getSessionloginUser();

		if (sessionUser == null) {
			return new BaseRespVO(99, "无法获取登录用户信息");
		}

		if (oldPassword.equals(newPassword)) {
			return BaseRespVO.error("原密码与新密码一致，无需修改");
		}

		// 校验密码格式
		BaseRespVO resp = UserPwdUtil.checkPwd(newPassword);
		if (resp != null) {
			return resp;
		}

		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("userId", sessionUser.getUserId());
		try {
			map.put("oldPassword", UserPwdUtil.encoderPwd(oldPassword));
			map.put("newPassword", UserPwdUtil.encoderPwd(newPassword));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("密码MD5加密是出错", e);
			return new BaseRespVO(99, "密码MD5加密是出错");
		}

		int count = userBo.updatePassword(map);

		if (count < 1) {
			return new BaseRespVO(99, "密码错误！");
		}

		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param userId
	 * @return
	 */
	public BaseRespVO delete(@Param("userId") Long[] userId) {
		int deleteCount = userBo.batchDeleteById(userId);
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}

	}

	/**
	 * @Description:重置密码
	 * @param userId
	 * @return
	 * @throws CenterException
	 */
	public BaseRespVO resetpass(@Param("userId") Long userId) throws CenterException{
		UserEntity user = new UserEntity();
		user.setUserId(userId);
		user.setPassword(Constants.RESET_PASSWORD);
		// 密码MD5加密
		try {
			user.setPassword(UserPwdUtil.encoderPwd(user.getPassword()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("密码MD5加密是出错", e);
			return new BaseRespVO(99, "密码MD5加密是出错");
		}
		userBo.updateForValidate(user);
		return new BaseRespVO();
	}

	public boolean checkMenum(@Param("longUserId") Long longUserId,@Param("menuName") String menuName) {
		List<String> redisList=redisTemplates.opsForValue().get(longUserId.toString());
		if (redisList.contains(menuName)) {
			return true;
		}
		return false;
	}
	
	public void removeRedis(@Param("longUserId") Long longUserId) {
		redisTemplates.delete(longUserId.toString());
	}
}
