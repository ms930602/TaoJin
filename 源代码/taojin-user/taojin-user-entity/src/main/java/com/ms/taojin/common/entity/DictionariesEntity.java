
package com.ms.taojin.common.entity;

import com.ms.taojin.common.entity.BaseEntity;

/**
 * 数据字典
 * @author lansongtao
 * @Date 2017-12-14 10:56:26
 * @since 1.0
 */
public class DictionariesEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 自增ID. */
	private Long id;
	
	/** 字典类型. */
	private String type;
	
	/** 键. */
	private String key;
	
	/** 值. */
	private String value;
	
	/** 扩展字段. */
	private String extend;
	
	/** 用户ID. */
	private String cteateUserId;
	
	/** 用户名. */
	private String cteateUserName;
	
	/** 创建日期. */
	private java.util.Date createDate;
	
	/** 修改日期. */
	private java.util.Date updateDate;
	
	

    /** set 自增ID. */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 自增ID. */
	public Long getId() {
		return this.id;
	}
	

    /** set 字典类型. */
	public void setType(String type) {
		this.type = type;
	}
	
	/** get 字典类型. */
	public String getType() {
		return this.type;
	}
	

    /** set 键. */
	public void setKey(String key) {
		this.key = key;
	}
	
	/** get 键. */
	public String getKey() {
		return this.key;
	}
	

    /** set 值. */
	public void setValue(String value) {
		this.value = value;
	}
	
	/** get 值. */
	public String getValue() {
		return this.value;
	}
	

    /** set 扩展字段. */
	public void setExtend(String extend) {
		this.extend = extend;
	}
	
	/** get 扩展字段. */
	public String getExtend() {
		return this.extend;
	}
	

    /** set 用户ID. */
	public void setCteateUserId(String cteateUserId) {
		this.cteateUserId = cteateUserId;
	}
	
	/** get 用户ID. */
	public String getCteateUserId() {
		return this.cteateUserId;
	}
	

    /** set 用户名. */
	public void setCteateUserName(String cteateUserName) {
		this.cteateUserName = cteateUserName;
	}
	
	/** get 用户名. */
	public String getCteateUserName() {
		return this.cteateUserName;
	}
	

    /** set 创建日期. */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/** get 创建日期. */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	

    /** set 修改日期. */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/** get 修改日期. */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	
    /** constructor */
	public DictionariesEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param type			字典类型
	 * @param key			键
	 * @param value			值
	 * @param extend			扩展字段
	 * @param cteateUserId			用户ID
	 * @param cteateUserName			用户名
	 * @param createDate			创建日期
	 * @param updateDate			修改日期
	 */
	public DictionariesEntity(String type,String key,String value,String extend,String cteateUserId,String cteateUserName,java.util.Date createDate,java.util.Date updateDate){
		this();
		this.type = type;
		this.key = key;
		this.value = value;
		this.extend = extend;
		this.cteateUserId = cteateUserId;
		this.cteateUserName = cteateUserName;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("DictionariesEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Type=").append(getType()).append(", ")
			.append("Key=").append(getKey()).append(", ")
			.append("Value=").append(getValue()).append(", ")
			.append("Extend=").append(getExtend()).append(", ")
			.append("CteateUserId=").append(getCteateUserId()).append(", ")
			.append("CteateUserName=").append(getCteateUserName()).append(", ")
			.append("CreateDate=").append(getCreateDate()).append(", ")
			.append("UpdateDate=").append(getUpdateDate())
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
		if(obj instanceof DictionariesEntity == false) return false;
		if(this == obj) return true;
		DictionariesEntity other = (DictionariesEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
