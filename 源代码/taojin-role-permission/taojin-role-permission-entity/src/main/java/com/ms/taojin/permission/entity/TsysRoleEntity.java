/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.field;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-04-06 13:08:14
 * @since 1.0
 */
public class TsysRoleEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
    @field("")
	private Long id;
    /** 用户类型id(是市场管理员还是商家). */
	private String typeId;
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/** 角色名称. */
	@field("角色名称")
	private String name;
	/** 用户类型编码(商家就存商家编码、市场就存市场编码). */
	private String typeCode;
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/** 状态. */
	@field("状态")
	private Long statu;
	
	/** 创建时间. */
	@field("创建时间")
	private java.util.Date createtime;
	
	/** 备注. */
	@field("备注")
	private String remark;
	
	/** 系统ID. */
	@field("系统ID")
	private Long sysId;
	
	

    public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
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
	public void setName(String name) {
		this.name = name;
	}
	
	/** get . */
	public String getName() {
		return this.name;
	}
	

    /** set . */
	public void setStatu(Long statu) {
		this.statu = statu;
	}
	
	/** get . */
	public Long getStatu() {
		return this.statu;
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
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/** get . */
	public String getRemark() {
		return this.remark;
	}
	
    /** constructor */
	public TsysRoleEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param name			
	 * @param statu			
	 * @param createtime			
	 * @param remark			
	 */
	public TsysRoleEntity(String name,Long statu,java.util.Date createtime,String remark){
		this();
		this.name = name;
		this.statu = statu;
		this.createtime = createtime;
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("TsysRoleEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Statu=").append(getStatu()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("Remark=").append(getRemark())
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
		if(obj instanceof TsysRoleEntity == false) return false;
		if(this == obj) return true;
		TsysRoleEntity other = (TsysRoleEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
