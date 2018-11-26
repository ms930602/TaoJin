package com.ms.taojin.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.encrypt.MD5Util;
import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.pre.api.ISessionHandler;
import com.ms.taojin.user.api.IUserService;
import com.ms.taojin.user.bo.UserBO;
import com.ms.taojin.user.entity.UserEntity;

/**
 * 业务处理.
 * <p>
 * 
 * @author lansongtao
 * @Date 2017-04-12 14:08:41
 * @since 1.0
 */
@Service
public class UserApiService extends BaseService implements IUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserBO userBO;
	@Autowired
	private UserService userService;
	@Autowired(required = false)
	private ISessionHandler sessionHandler;

	@Override
	public List<UserEntity> queryListByEntity(UserEntity condition) {
		// 只查询启用状态下的用户
		condition.setStatus(1);
		return userBO.queryListByEntity(condition);
	}

	@Override
	public UserEntity create(UserEntity user) throws CenterException {
		if (StringUtils.isEmpty(user.getLoginName())) {
			logger.error("登录名称不合法");
			throw new CenterException("登录名称不合法");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			// 默认密码
			user.setPassword("123456");
		}
		// 密码MD5加密
		try {
			user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("密码MD5加密是出错", e);
			throw new CenterException("密码MD5加密是出错");
		}
		UserEntity entity = new UserEntity();
		entity.setLoginName(user.getLoginName());
		entity.setSysId(user.getSysId());
		UserEntity user1 = userBO.queryByEntity(entity);
		if (user1 != null) {
			throw new CenterException("登录名重复");
		}
		userBO.create(user);
		return user;
	}

	@Override
	public BaseRespVO update(UserEntity user) throws CenterException {
		return userService.update(user);
	}

	@Override
	public BaseRespVO delete(Long[] userId) throws CenterException {
		return userService.delete(userId);
	}

	@Override
	public BaseRespVO updateStatusByType(String typeId, String typeCode, String sysId, int status) throws CenterException {
		int oldStatus = 0;
		if (status == 0) {
			oldStatus = 1;
		}

		List<Long> userIdList = userBO.queryIdByType(typeId, typeCode, sysId, oldStatus);
		if (userIdList == null || userIdList.isEmpty()) {
			return BaseRespVO.error("没有查询到要修改的用户信息");
		}
		System.out.println(userIdList);
		int count = userBO.updateStatusByType(userIdList, status);
		// 状态修改为注销的时候，调用网关，删除用户登录会话
		if (status == 0 && count > 0) {
			try {
				sessionHandler.deleteSession(userIdList);
			} catch (Exception e) {
				logger.error("在session中弹出用户失败！", e.getMessage());
			}
		}

		return new BaseRespVO();
	}

	@Override
	public List<UserEntity> queryListByType(String typeId, List<String> typeCodeList, String sysId) {
		// 只查询启用状态下的用户
		return userBO.queryListByType(typeId, typeCodeList, sysId, 1);
	}

	@Override
	public List<UserEntity> queryAllListByEntity(UserEntity condition) {
		
		return userBO.queryListByEntity(condition);
	}

}
