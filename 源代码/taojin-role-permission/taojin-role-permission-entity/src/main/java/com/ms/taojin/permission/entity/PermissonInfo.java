package com.ms.taojin.permission.entity;

import java.io.Serializable;
import java.util.List;

public class PermissonInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	TsysSysEntity sysEntity;
	List<MenuItem> listMenu;
	public TsysSysEntity getSysEntity() {
		return sysEntity;
	}
	public void setSysEntity(TsysSysEntity sysEntity) {
		this.sysEntity = sysEntity;
	}
	public List<MenuItem> getListMenu() {
		return listMenu;
	}
	public void setListMenu(List<MenuItem> listMenu) {
		this.listMenu = listMenu;
	}
	

}
