package com.ms.taojin.permission.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String caption;
	private String name;
	private String iconCls;
	private int hasChildren = 0;
	private String index;
	private String defaultUrl;
	private Long sysId;
	private int sort;
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	private List<TsysButtonEntity> butList = new ArrayList<TsysButtonEntity>();
	private List<MenuItem> children;

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public int getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(int hasChildren) {
		this.hasChildren = hasChildren;
	}

	public MenuItem() {
	}

	public MenuItem(String name, String caption, String iconCls, String index,Long sysId,int sort) {
		this.name = name;
		this.caption = caption;
		this.iconCls = iconCls;
		this.index = index;
		this.sysId=sysId;
		this.sort=sort;
	}

	public List<TsysButtonEntity> getButList() {
		return butList;
	}

	public String getCaption() {
		return caption;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setButList(List<TsysButtonEntity> butList) {
		this.butList = butList;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", caption=" + caption + ", name=" + name + ", iconCls=" + iconCls + ", hasChildren=" + hasChildren + ", index=" + index
		        + ", defaultUrl=" + defaultUrl + ", butList=" + butList + ", children=" + children + "]";
	}

}
