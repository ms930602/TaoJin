package com.ms.taojin.user.vo;

import java.util.Map;

import com.ms.taojin.common.vo.BaseRespVO;

/**
 * 权限校验实体
 *
 * @author lansongtao
 * @Date 2017年12月29日
 * @since 1.0
 */
public class AuthCheckVo extends BaseRespVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单权限
	 */
	private Map<String, String> menus;

	/**
	 * 按钮权限
	 */
	private Map<String, String> buttons;

	/**
	 * 是否校验按钮权限(默认不校验)
	 */
	private boolean isButtonCheck = false;

	public Map<String, String> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, String> menus) {
		this.menus = menus;
	}

	public Map<String, String> getButtons() {
		return buttons;
	}

	public void setButtons(Map<String, String> buttons) {
		this.buttons = buttons;
	}

	public boolean isButtonCheck() {
		return isButtonCheck;
	}

	public void setButtonCheck(boolean isButtonCheck) {
		this.isButtonCheck = isButtonCheck;
	}

	public void setButtonCheck(Long isButtonCheck) {
		if (isButtonCheck != null && isButtonCheck.equals(1l)) {
			this.isButtonCheck = true;
		}
	}

	@Override
	public String toString() {
		return "AuthCheckVo [menus=" + menus + ", buttons=" + buttons + ", isButtonCheck=" + isButtonCheck + "]";
	}

}
