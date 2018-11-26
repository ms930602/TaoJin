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
public class TsysRoleUserEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private Long id;
	
	/** . */
	private Long userId;
	
	/** . */
	private Long roleId;
	
	

    /** set . */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get . */
	public Long getId() {
		return this.id;
	}
	

    /** set . */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/** get . */
	public Long getUserId() {
		return this.userId;
	}
	

    /** set . */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/** get . */
	public Long getRoleId() {
		return this.roleId;
	}
	
    /** constructor */
	public TsysRoleUserEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param userId			
	 * @param roleId			
	 */
	public TsysRoleUserEntity(Long userId,Long roleId){
		this();
		this.userId = userId;
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("TsysRoleUserEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("UserId=").append(getUserId()).append(", ")
			.append("RoleId=").append(getRoleId())
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
		if(obj instanceof TsysRoleUserEntity == false) return false;
		if(this == obj) return true;
		TsysRoleUserEntity other = (TsysRoleUserEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
