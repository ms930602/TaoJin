/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-04-05 09:52:30
 * @since 1.0
 */
public class TsysRoleModlePermissionEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private Long id;
	
	/** . */
	private Long roleId;
	
	/** . */
	private Long moduleId;
	
	/** . */
	private Long permission;
	
	/** . */
	private java.util.Date createtime;
	
	/** . */
	private Long sysId;
	
	

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
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	/** get . */
	public Long getModuleId() {
		return this.moduleId;
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
	

    /** set . */
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	
	/** get . */
	public Long getSysId() {
		return this.sysId;
	}
	
    /** constructor */
	public TsysRoleModlePermissionEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param roleId			
	 * @param moduleId			
	 * @param permission			
	 * @param createtime			
	 * @param sysId			
	 */
	public TsysRoleModlePermissionEntity(Long roleId,Long moduleId,Long permission,java.util.Date createtime,Long sysId){
		this();
		this.roleId = roleId;
		this.moduleId = moduleId;
		this.permission = permission;
		this.createtime = createtime;
		this.sysId = sysId;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("TsysRoleModlePermissionEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("RoleId=").append(getRoleId()).append(", ")
			.append("ModuleId=").append(getModuleId()).append(", ")
			.append("Permission=").append(getPermission()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("SysId=").append(getSysId())
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
		if(obj instanceof TsysRoleModlePermissionEntity == false) return false;
		if(this == obj) return true;
		TsysRoleModlePermissionEntity other = (TsysRoleModlePermissionEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
