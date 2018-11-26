package com.ms.taojin.permission.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String label;
	private List<TreeNode> children=new ArrayList<TreeNode>();
	private boolean selected;
	private List selectId;
	
	public List getSelectId() {
		return selectId;
	}
	public void setSelectId(List selectId) {
		this.selectId = selectId;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public TreeNode(String id, String label) {
		this.id = id;
		this.label = label;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
}
