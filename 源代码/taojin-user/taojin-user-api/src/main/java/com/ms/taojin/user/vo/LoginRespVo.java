package com.ms.taojin.user.vo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.permission.entity.MenuItem;
import com.ms.taojin.permission.entity.TsysButtonEntity;
import com.ms.taojin.user.entity.UserEntity;

/**
 * 用户登录返回
 *
 * @author lansongtao
 * @Date 2017年4月12日
 * @since 1.0
 */
public class LoginRespVo extends BaseRespVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单列表
	 */
	private List<MenuItem> menuItemLlist;

	/**
	 * 权限校验实体(后端用来校验接口访问权限，不用返回给前端)
	 */
	@JsonIgnore
	private AuthCheckVo authCheckVo = new AuthCheckVo();

	/**
	 * 按钮信息(前端用来控制是否显示按钮)
	 */
	private Map<String, List<TsysButtonEntity>> buttonItemMap;

	/**
	 * 要保存到session中的用户信息
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	private SessionUser sessionUser;

	/**
	 * 登录用户信息
	 */
	private UserEntity loginUser;

	/**
	 * 登录TOKEN
	 */
	private String token;

	/**
	 * 是否提醒用户修改密码
	 */
	private boolean isUpdatePwd;

	public LoginRespVo() {
		super();
	}

	public LoginRespVo(int state, String msg) {
		super(state, msg);
	}

	public List<MenuItem> getMenuItemLlist() {
		return menuItemLlist;
	}

	public void setMenuItemLlist(List<MenuItem> menuItemLlist) {
		this.menuItemLlist = menuItemLlist;
	}

	public AuthCheckVo getAuthCheckVo() {
		return authCheckVo;
	}

	public void setAuthCheckVo(AuthCheckVo authCheckVo) {
		this.authCheckVo = authCheckVo;
	}

	public Map<String, List<TsysButtonEntity>> getButtonItemMap() {
		return buttonItemMap;
	}

	public void setButtonItemMap(Map<String, List<TsysButtonEntity>> buttonItemMap) {
		this.buttonItemMap = buttonItemMap;
	}

	public SessionUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(SessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}

	public UserEntity getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(UserEntity loginUser) {
		this.loginUser = loginUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isUpdatePwd() {
		return isUpdatePwd;
	}

	public void setUpdatePwd(boolean isUpdatePwd) {
		this.isUpdatePwd = isUpdatePwd;
	}

	@Override
	public String toString() {
		return "LoginRespVo [menuItemLlist=" + menuItemLlist + ", authCheckVo=" + authCheckVo + ", buttonItemMap=" + buttonItemMap + ", sessionUser="
		        + sessionUser + ", loginUser=" + loginUser + ", token=" + token + ", isUpdatePwd=" + isUpdatePwd + ", toString()=" + super.toString() + "]";
	}

}
