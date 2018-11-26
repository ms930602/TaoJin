
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 数据字典
 * @author dwx
 * @Date 2018-07-09 18:27:45
 * @since 1.0
 */
@TableName("t_sys_dictionary")
public class DictionaryEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 自增id. */
	private Long id;
	
	/** 分类编码. */
	private String claCode;
	
	/** 分类名称. */
	private String claName;
	
	/** 字典编码. */
	private String dicCode;
	
	/** 字典名称. */
	private String dicName;
	
	/** 排序. */
	private Long order;
	
	/** 是否默认 1：是  0 ：否. */
	private Integer isDefault;
	
	/** 创建时间. */
	private java.util.Date createdDate;
	
	/** 创建人id. */
	private Long createdPersonId;
	
	/** 创建人. */
	private String createdPerson;
	
	/** 更新时间. */
	private java.util.Date updatedDate;
	
	/** 更新人id. */
	private Long updatedPersonId;
	
	/** 修改人. */
	private String updatedPerson;
	
	

    /** set 自增id. */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 自增id. */
	public Long getId() {
		return this.id;
	}
	
	@JsonIgnore
	public Long getIdByLike() {
		return this.id;
	}
	

    /** set 分类编码. */
	public void setClaCode(String claCode) {
		this.claCode = claCode;
	}
	
	/** get 分类编码. */
	public String getClaCode() {
		return this.claCode;
	}
	
	@JsonIgnore
	public String getClaCodeByLike() {
		return "%"+this.claCode+"%";
	}
	

    /** set 分类名称. */
	public void setClaName(String claName) {
		this.claName = claName;
	}
	
	/** get 分类名称. */
	public String getClaName() {
		return this.claName;
	}
	
	@JsonIgnore
	public String getClaNameByLike() {
		return "%"+this.claName+"%";
	}
	

    /** set 字典编码. */
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	
	/** get 字典编码. */
	public String getDicCode() {
		return this.dicCode;
	}
	
	@JsonIgnore
	public String getDicCodeByLike() {
		return "%"+this.dicCode+"%";
	}
	

    /** set 字典名称. */
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	
	/** get 字典名称. */
	public String getDicName() {
		return this.dicName;
	}
	
	@JsonIgnore
	public String getDicNameByLike() {
		return "%"+this.dicName+"%";
	}
	

    /** set 排序. */
	public void setOrder(Long order) {
		this.order = order;
	}
	
	/** get 排序. */
	public Long getOrder() {
		return this.order;
	}
	
	@JsonIgnore
	public Long getOrderByLike() {
		return this.order;
	}
	

    /** set 是否默认 1：是  0 ：否. */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	/** get 是否默认 1：是  0 ：否. */
	public Integer getIsDefault() {
		return this.isDefault;
	}
	
	@JsonIgnore
	public Integer getIsDefaultByLike() {
		return this.isDefault;
	}
	

    /** set 创建时间. */
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/** get 创建时间. */
	public java.util.Date getCreatedDate() {
		return this.createdDate;
	}
	
	@JsonIgnore
	public java.util.Date getCreatedDateByLike() {
		return this.createdDate;
	}
	

    /** set 创建人id. */
	public void setCreatedPersonId(Long createdPersonId) {
		this.createdPersonId = createdPersonId;
	}
	
	/** get 创建人id. */
	public Long getCreatedPersonId() {
		return this.createdPersonId;
	}
	
	@JsonIgnore
	public Long getCreatedPersonIdByLike() {
		return this.createdPersonId;
	}
	

    /** set 创建人. */
	public void setCreatedPerson(String createdPerson) {
		this.createdPerson = createdPerson;
	}
	
	/** get 创建人. */
	public String getCreatedPerson() {
		return this.createdPerson;
	}
	
	@JsonIgnore
	public String getCreatedPersonByLike() {
		return "%"+this.createdPerson+"%";
	}
	

    /** set 更新时间. */
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	/** get 更新时间. */
	public java.util.Date getUpdatedDate() {
		return this.updatedDate;
	}
	
	@JsonIgnore
	public java.util.Date getUpdatedDateByLike() {
		return this.updatedDate;
	}
	

    /** set 更新人id. */
	public void setUpdatedPersonId(Long updatedPersonId) {
		this.updatedPersonId = updatedPersonId;
	}
	
	/** get 更新人id. */
	public Long getUpdatedPersonId() {
		return this.updatedPersonId;
	}
	
	@JsonIgnore
	public Long getUpdatedPersonIdByLike() {
		return this.updatedPersonId;
	}
	

    /** set 修改人. */
	public void setUpdatedPerson(String updatedPerson) {
		this.updatedPerson = updatedPerson;
	}
	
	/** get 修改人. */
	public String getUpdatedPerson() {
		return this.updatedPerson;
	}
	
	@JsonIgnore
	public String getUpdatedPersonByLike() {
		return "%"+this.updatedPerson+"%";
	}
	
    /** constructor */
	public DictionaryEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param claCode			分类编码
	 * @param claName			分类名称
	 * @param dicCode			字典编码
	 * @param dicName			字典名称
	 * @param order			排序
	 * @param isDefault			是否默认 1：是  0 ：否
	 * @param createdDate			创建时间
	 * @param createdPersonId			创建人id
	 * @param createdPerson			创建人
	 * @param updatedDate			更新时间
	 * @param updatedPersonId			更新人id
	 * @param updatedPerson			修改人
	 */
	public DictionaryEntity(String claCode,String claName,String dicCode,String dicName,Long order,Integer isDefault,java.util.Date createdDate,Long createdPersonId,String createdPerson,java.util.Date updatedDate,Long updatedPersonId,String updatedPerson){
		this();
		this.claCode = claCode;
		this.claName = claName;
		this.dicCode = dicCode;
		this.dicName = dicName;
		this.order = order;
		this.isDefault = isDefault;
		this.createdDate = createdDate;
		this.createdPersonId = createdPersonId;
		this.createdPerson = createdPerson;
		this.updatedDate = updatedDate;
		this.updatedPersonId = updatedPersonId;
		this.updatedPerson = updatedPerson;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("DictionaryEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("ClaCode=").append(getClaCode()).append(", ")
			.append("ClaName=").append(getClaName()).append(", ")
			.append("DicCode=").append(getDicCode()).append(", ")
			.append("DicName=").append(getDicName()).append(", ")
			.append("Order=").append(getOrder()).append(", ")
			.append("IsDefault=").append(getIsDefault()).append(", ")
			.append("CreatedDate=").append(getCreatedDate()).append(", ")
			.append("CreatedPersonId=").append(getCreatedPersonId()).append(", ")
			.append("CreatedPerson=").append(getCreatedPerson()).append(", ")
			.append("UpdatedDate=").append(getUpdatedDate()).append(", ")
			.append("UpdatedPersonId=").append(getUpdatedPersonId()).append(", ")
			.append("UpdatedPerson=").append(getUpdatedPerson())
		.append("]").toString();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj instanceof DictionaryEntity == false)
			return false;
		if(this == obj)
			return true;
		DictionaryEntity other = (DictionaryEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
