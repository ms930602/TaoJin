/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:51
 * @since 1.0
 */
public class TsysRoleButtonPermissionEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private Long sysId;
	
	/** . */
	private Long id;
	
	/** . */
	private Long roleId;
	
	/** . */
	private Long buttonId;
	
	/** . */
	private Long permission;
	
	/** . */
	private java.util.Date createtime;
	
	

    /** set . */
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	
	/** get . */
	public Long getSysId() {
		return this.sysId;
	}
	

    /** set . */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get . */
	public Long getId() {
		return this.id;
	}
	

    /** set . */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/** get . */
	public Long getRoleId() {
		return this.roleId;
	}
	

    /** set . */
	public void setButtonId(Long buttonId) {
		this.buttonId = buttonId;
	}
	
	/** get . */
	public Long getButtonId() {
		return this.buttonId;
	}
	

    /** set . */
	public void setPermission(Long permission) {
		this.permission = permission;
	}
	
	/** get . */
	public Long getPermission() {
		return this.permission;
	}
	

    /** set . */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	/** get . */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
    /** constructor */
	public TsysRoleButtonPermissionEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param sysId			
	 * @param roleId			
	 * @param buttonId			
	 * @param permission			
	 * @param createtime			
	 */
	public TsysRoleButtonPermissionEntity(Long sysId,Long roleId,Long buttonId,Long permission,java.util.Date createtime){
		this();
		this.sysId = sysId;
		this.roleId = roleId;
		this.buttonId = buttonId;
		this.permission = permission;
		this.createtime = createtime;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("RoleButtonPermissionEntity[")
			.append("SysId=").append(getSysId()).append(", ")
			.append("Id=").append(getId()).append(", ")
			.append("RoleId=").append(getRoleId()).append(", ")
			.append("ButtonId=").append(getButtonId()).append(", ")
			.append("Permission=").append(getPermission()).append(", ")
			.append("Createtime=").append(getCreatetime())
		.append("]").toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    	result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TsysRoleButtonPermissionEntity == false) return false;
		if(this == obj) return true;
		TsysRoleButtonPermissionEntity other = (TsysRoleButtonPermissionEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}