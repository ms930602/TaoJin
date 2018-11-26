/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-12-26 14:55:26
 * @since 1.0
 */
public class TsysSysEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private Long id;
	
	/** 系统名称. */
	private String name;
	
	/** 创建时间. */
	private java.util.Date createtime;
	
	/** 1表示接口0表示模块. */
	private Long type;
	
	

    /** set . */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get . */
	public Long getId() {
		return this.id;
	}
	

    /** set 系统名称. */
	public void setName(String name) {
		this.name = name;
	}
	
	/** get 系统名称. */
	public String getName() {
		return this.name;
	}
	

    /** set 创建时间. */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	/** get 创建时间. */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	

    /** set 1表示接口0表示模块. */
	public void setType(Long type) {
		this.type = type;
	}
	
	/** get 1表示接口0表示模块. */
	public Long getType() {
		return this.type;
	}
	
    /** constructor */
	public TsysSysEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param name			系统名称
	 * @param createtime			创建时间
	 * @param type			1表示接口0表示模块
	 */
	public TsysSysEntity(String name,java.util.Date createtime,Long type){
		this();
		this.name = name;
		this.createtime = createtime;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("TsysSysEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("Type=").append(getType())
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
		if(obj instanceof TsysSysEntity == false) return false;
		if(this == obj) return true;
		TsysSysEntity other = (TsysSysEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
