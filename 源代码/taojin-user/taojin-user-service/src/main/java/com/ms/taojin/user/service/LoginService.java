package com.ms.taojin.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.bo.DictionariesBO;
import com.ms.taojin.common.entity.DictionariesEntity;
import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.service.CountLoginsService;
import com.ms.taojin.common.utils.ConfProperties;
import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.permission.api.IPermissionSService;
import com.ms.taojin.permission.entity.MenuItem;
import com.ms.taojin.permission.entity.PermissonInfo;
import com.ms.taojin.permission.entity.TsysButtonEntity;
import com.ms.taojin.user.api.ILoginService;
import com.ms.taojin.user.bo.UserBO;
import com.ms.taojin.user.entity.UserEntity;
import com.ms.taojin.user.utils.UserPwdUtil;
import com.ms.taojin.user.vo.AuthCheckVo;
import com.ms.taojin.user.vo.LoginRespVo;

/**
 * 业务处理.
 * <p>
 * 
 * @author lansongtao
 * @Date 2017-04-12 14:08:41
 * @since 1.0
 */
@Service
@SuppressWarnings("rawtypes")
public class LoginService extends BaseService implements ILoginService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserBO userBO;

	@Autowired(required = false)
	private IPermissionSService prmissionSService;

	@Autowired
	RedisTemplate<String, RedisAtomicLong> redisTemplate;
	@Autowired
	RedisTemplate<String, List> redisTemplates;
	@Autowired
	DictionariesBO dictionariesBO;
	@Autowired
	CountLoginsService countLoginsService;
	/**
	 * 登录接口
	 * 
	 * @param reqMap
	 * @return
	 */
	@Override
	public LoginRespVo login(Map reqMap) {

		String loginName = reqMap.get("loginName") + "";
		String password = reqMap.get("password") + "";
		String systemId = reqMap.get("systemId") + "";
		// 判断是否返回公共模块
		String flag = reqMap.get("flag") + "";

		if (StringUtils.isEmpty(loginName)) {
			return new LoginRespVo(88, "用户名不能为空！");
		}

		if (StringUtils.isEmpty(password)) {
			return new LoginRespVo(88, "密码不能为空！");
		}

		if (StringUtils.isEmpty(systemId)) {
			return new LoginRespVo(88, "systemId不能为空！");
		}

		// 默认的登录失败锁定次数和时间阀值
		long failTimes = 5;
		long lockMinute = 10;

		// 在数据字典中查询锁定次数和时间配置，有的话就使用数据字典中配置的阀值
		DictionariesEntity condition = new DictionariesEntity();
		condition.setType("login_conf");
		condition.setKey("fail_times");
		DictionariesEntity resultDict = dictionariesBO.queryByEntity(condition);
		if (resultDict != null && !StringUtils.isEmpty(resultDict.getValue())) {
			failTimes = Long.parseLong(resultDict.getValue());
		}

		condition.setKey("lock_minute");
		resultDict = dictionariesBO.queryByEntity(condition);
		if (resultDict != null && !StringUtils.isEmpty(resultDict.getValue())) {
			lockMinute = Long.parseLong(resultDict.getValue());
		}

		// 校验登录失败次数是否超过阀值
		if (!checkFailTime(loginName, systemId, failTimes)) {
			return new LoginRespVo(88, "您的账户密码错误次数已经超过" + failTimes + "次，请" + lockMinute + "分钟后再试");
		}

		// 设置查询条件
		UserEntity user = new UserEntity();
		user.setLoginName(loginName);
		if (!(loginName.equals("admin"))) {
			user.setSysId(systemId);
		}

		try {
			user.setPassword(UserPwdUtil.encoderPwd(password));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("密码MD5加密时出错", e);
			return new LoginRespVo(99, "密码加密时出错");
		}
		// 根据用户名密码查询用户记录
		try {
			//尝试使用用户数据登录
			user = userBO.queryByEntity(user);
		} catch (final MyBatisSystemException e) {
			if (e.getCause().getClass() == TooManyResultsException.class) {
				return new LoginRespVo(99, "错误,登录出现多个用户信息!");
			}
			throw e;
		}
		if (user == null) {
			long thenTime = failTimes - loginFail(loginName, systemId, lockMinute);
			if (thenTime == 0) {
				return new LoginRespVo(88, "您的账户密码错误次数已经超过" + failTimes + "次，请" + lockMinute + "分钟后再试");
			} else {
				return new LoginRespVo(99, "用户名密码错误,再错误" + thenTime + "次之后账户将被锁定");
			}

		}
		if (user.getStatus() == 0) {
			return new LoginRespVo(99, "用户没激活");
		}
		// 校验通过
		Date lastLoginTime = user.getLastLoginTime();
		user.setLastLoginTime(new Date());
		userBO.update(user);
		user.setLastLoginTime(lastLoginTime);

		// 设置超级管理员的系统ID
		if (user.getUserId() == 0) {
			user.setSysId(systemId);
		}

		// 登录成功处理
		loginSuccess(user);

		// 调用权限系统取用户权限
		PermissonInfo permissonInfo = null;
		List<MenuItem> menuItemList = null;
		try {
			permissonInfo = prmissionSService.initMenusService(user.getUserId() + "", systemId, flag);
		} catch (Exception e) {
			logger.error("调用权限系统出错", e);
			return new LoginRespVo(99, "调用权限系统出错");
		}

		logger.debug("调用权限系统返回菜单:{}", menuItemList);

		if (permissonInfo == null || permissonInfo.getListMenu() == null || permissonInfo.getListMenu().isEmpty()) {
			logger.debug("该用户没有系统权限，请联系管理员进行授权");
			return new LoginRespVo(99, "该用户没有任何系统权限，请联系管理员进行授权");
		}

		LoginRespVo resp = new LoginRespVo();
		resp.setLoginUser(user);
		resp.setSessionUser(parseSessionUser(user));
		// 格式化权限菜单内容
		if (permissonInfo.getSysEntity() == null) {
			logger.debug("权限表中没有配置该系统ID：sysId={}", systemId);
			return new LoginRespVo(99, "权限表中没有配置该系统ID：sysId=" + systemId);
		}
		formatMenuItemList(resp, permissonInfo);
		decompositionOfMenu(user,resp);
		return resp;
	}

	private SessionUser parseSessionUser(UserEntity user) {
		SessionUser sessionUser = new SessionUser();
		BeanUtils.copyProperties(user, sessionUser);
		sessionUser.setUserId(user.getUserId());
		return sessionUser;
	}

	/**
	 * 格式化权限菜单内容
	 * 
	 * @param respVo
	 * @param permissonInfo
	 * @return
	 */
	private void formatMenuItemList(LoginRespVo respVo, PermissonInfo permissonInfo) {
		respVo.setMenuItemLlist(permissonInfo.getListMenu());
		respVo.setButtonItemMap(new HashMap<String, List<TsysButtonEntity>>());
		respVo.setAuthCheckVo(new AuthCheckVo());
		respVo.getAuthCheckVo().setButtonCheck(permissonInfo.getSysEntity().getType());

		extractMenuItem(respVo.getMenuItemLlist(), respVo);
	}

	/**
	 * 递归提取菜单内容
	 * 
	 * @param menuItemList
	 * @param respVo
	 * @return
	 */
	private void extractMenuItem(List<MenuItem> menuItemList, LoginRespVo respVo) {
		AuthCheckVo authCheckVo = respVo.getAuthCheckVo();
		Map<String, List<TsysButtonEntity>> buttonItemMap = respVo.getButtonItemMap();

		for (Iterator<MenuItem> iterMenu = menuItemList.iterator(); iterMenu.hasNext();) {
			MenuItem menuItem = iterMenu.next();
			// comm模块不需要展示在菜单中
			if (0 == menuItem.getId() || StringUtils.isEmpty(menuItem.getCaption())) {
				iterMenu.remove();
			}
			// 添加菜单权限(暂时没用，前端调接口没有传菜单信息)
			if (authCheckVo.getMenus() == null) {
				authCheckVo.setMenus(new HashMap<String, String>());
			}
			authCheckVo.getMenus().put(menuItem.getCaption(), null);

			// 提取按钮信息
			List<TsysButtonEntity> butList = menuItem.getButList();
			out: if (butList != null && !butList.isEmpty()) {
				menuItem.setButList(null);
				// 该系统是否需要校验按钮权限
				if (!authCheckVo.isButtonCheck()) {
					break out;
				}
				if (!StringUtils.isEmpty(menuItem.getCaption())) {
					buttonItemMap.put(menuItem.getCaption(), butList);
				}
				// 提取按钮中的URI访问地址
				extractButton(butList, authCheckVo);
			}
			if (menuItem.getChildren() != null && !menuItem.getChildren().isEmpty()) {
				extractMenuItem(menuItem.getChildren(), respVo);
			}
		}
	}

	/**
	 * 提取菜单中的接口权限
	 * 
	 * @param butList
	 * @param authCheckVo
	 */
	private void extractButton(List<TsysButtonEntity> butList, AuthCheckVo authCheckVo) {
		if (butList == null || butList.isEmpty()) {
			return;
		}
		for (Iterator<TsysButtonEntity> iter = butList.iterator(); iter.hasNext();) {
			TsysButtonEntity button = iter.next();
			// 页面不需要创建时间
			button.setCreatetime(null);

			if (authCheckVo.isButtonCheck() && !StringUtils.isEmpty(button.getApi())) {
				if (authCheckVo.getButtons() == null) {
					authCheckVo.setButtons(new HashMap<String, String>());
				}
				authCheckVo.getButtons().put(button.getApi(), null);
			}

		}

	}

	/**
	 * 登录成功处理
	 * 
	 * @param user
	 */
	public void loginSuccess(UserEntity user) {
		String key = "loginFail-" + user.getLoginName() + "-" + user.getSysId();
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		if (redisAtomicLong.get() != 0l) {
			redisAtomicLong.set(0l);
		}

		// 统计用户登录次数
		if ("true".equals(ConfProperties.TODAY_COUNT_USER_LOGINS_FLAG)) {
			countLoginsService.countLogins(user.getTypeId());
		}

	}

	/**
	 * 登录失败处理
	 * 
	 * @param userName
	 * @param systemId
	 * @param lockMinute
	 * @return
	 */
	public long loginFail(String userName, String systemId, long lockMinute) {
		String key = "loginFail-" + userName + "-" + systemId;
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		// 有效期10分钟
		redisAtomicLong.expire(lockMinute, TimeUnit.MINUTES);
		// 次数加一
		return redisAtomicLong.incrementAndGet();
	}

	/**
	 * 到redis中检查登录错误次数是否超限
	 * 
	 * @param userName
	 * @param systemId
	 * @param failTimes
	 * @return
	 */
	public boolean checkFailTime(String userName, String systemId, long failTimes) {
		String key = "loginFail-" + userName + "-" + systemId;
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		long thenTimes = redisAtomicLong.get();
		if (thenTimes < failTimes) {
			return true;
		} else {
			return false;
		}
	}
	public void decompositionOfMenu(UserEntity users,LoginRespVo resp) {
		List<MenuItem> list=resp.getMenuItemLlist();//拿到当前角色的所有菜单
		List<String> menuNameList=new ArrayList<>();//存在角色菜单名称
		for (MenuItem menuItem : list) {
			if (menuItem.getChildren()!=null) {
				  for(int i=0;i<menuItem.getChildren().size();i++) {
					  menuNameList.add(menuItem.getChildren().get(i).getName());//具体菜单名称
				  }
			}
		}
		//校验菜单是否存在
		Object redisList=redisTemplates.opsForValue().get(users.getUserId().toString());
		if (redisList!=null) {
			return;
		}
		redisTemplates.opsForValue().set(users.getUserId().toString(), menuNameList);
	}

}
