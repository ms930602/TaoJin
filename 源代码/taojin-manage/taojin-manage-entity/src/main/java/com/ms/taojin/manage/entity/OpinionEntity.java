
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 
 * @author 蒙赛
 * @Date 2018-11-26 14:55:47
 * @since 1.0
 */
@TableName("t_ms_opinion")
public class OpinionEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 类型 1-意见 2-bug. */
	private String type;
	
	/** 标题. */
	private String title;
	
	/** 内容. */
	private String remark;
	
	/** 创建时间. */
	private java.util.Date createtime;
	
	/** 创建人外键. */
	private Long createUserId;
	
	/** 处理状态 1-处理中 2-已处理. */
	private String handleType;
	
	/** 反馈内容. */
	private String reText;
	
	/** 反馈时间. */
	private java.util.Date reTime;
	
	/** 奖励内容. */
	private String reRewardText;
	
	/** 创建用户名称. */
	private String createUserName;
	
	/** 最后修改人外键. */
	private Long lastModifyPersonId;
	
	/** 最后修改人姓名. */
	private String lastModifyPersonName;
	
	

    /** set 主键. */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 主键. */
	public Long getId() {
		return this.id;
	}
	
	@JsonIgnore
	public Long getIdByLike() {
		return this.id;
	}
	

    /** set 类型 1-意见 2-bug. */
	public void setType(String type) {
		this.type = type;
	}
	
	/** get 类型 1-意见 2-bug. */
	public String getType() {
		return this.type;
	}
	
	@JsonIgnore
	public String getTypeByLike() {
		return this.type;
	}
	

    /** set 标题. */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** get 标题. */
	public String getTitle() {
		return this.title;
	}
	
	@JsonIgnore
	public String getTitleByLike() {
		return "%"+this.title+"%";
	}
	

    /** set 内容. */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/** get 内容. */
	public String getRemark() {
		return this.remark;
	}
	
	@JsonIgnore
	public String getRemarkByLike() {
		return "%"+this.remark+"%";
	}
	

    /** set 创建时间. */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	/** get 创建时间. */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	@JsonIgnore
	public java.util.Date getCreatetimeByLike() {
		return this.createtime;
	}
	

    /** set 创建人外键. */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	/** get 创建人外键. */
	public Long getCreateUserId() {
		return this.createUserId;
	}
	
	@JsonIgnore
	public Long getCreateUserIdByLike() {
		return this.createUserId;
	}
	

    /** set 处理状态 1-处理中 2-已处理. */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	
	/** get 处理状态 1-处理中 2-已处理. */
	public String getHandleType() {
		return this.handleType;
	}
	
	@JsonIgnore
	public String getHandleTypeByLike() {
		return this.handleType;
	}
	

    /** set 反馈内容. */
	public void setReText(String reText) {
		this.reText = reText;
	}
	
	/** get 反馈内容. */
	public String getReText() {
		return this.reText;
	}
	
	@JsonIgnore
	public String getReTextByLike() {
		return "%"+this.reText+"%";
	}
	

    /** set 反馈时间. */
	public void setReTime(java.util.Date reTime) {
		this.reTime = reTime;
	}
	
	/** get 反馈时间. */
	public java.util.Date getReTime() {
		return this.reTime;
	}
	
	@JsonIgnore
	public java.util.Date getReTimeByLike() {
		return this.reTime;
	}
	

    /** set 奖励内容. */
	public void setReRewardText(String reRewardText) {
		this.reRewardText = reRewardText;
	}
	
	/** get 奖励内容. */
	public String getReRewardText() {
		return this.reRewardText;
	}
	
	@JsonIgnore
	public String getReRewardTextByLike() {
		return "%"+this.reRewardText+"%";
	}
	

    /** set 创建用户名称. */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	/** get 创建用户名称. */
	public String getCreateUserName() {
		return this.createUserName;
	}
	
	@JsonIgnore
	public String getCreateUserNameByLike() {
		return "%"+this.createUserName+"%";
	}
	

    /** set 最后修改人外键. */
	public void setLastModifyPersonId(Long lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}
	
	/** get 最后修改人外键. */
	public Long getLastModifyPersonId() {
		return this.lastModifyPersonId;
	}
	
	@JsonIgnore
	public Long getLastModifyPersonIdByLike() {
		return this.lastModifyPersonId;
	}
	

    /** set 最后修改人姓名. */
	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	/** get 最后修改人姓名. */
	public String getLastModifyPersonName() {
		return this.lastModifyPersonName;
	}
	
	@JsonIgnore
	public String getLastModifyPersonNameByLike() {
		return "%"+this.lastModifyPersonName+"%";
	}
	
    /** constructor */
	public OpinionEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param type			类型 1-意见 2-bug
	 * @param title			标题
	 * @param remark			内容
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param handleType			处理状态 1-处理中 2-已处理
	 * @param reText			反馈内容
	 * @param reTime			反馈时间
	 * @param reRewardText			奖励内容
	 * @param createUserName			创建用户名称
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public OpinionEntity(String type,String title,String remark,java.util.Date createtime,Long createUserId,String handleType,String reText,java.util.Date reTime,String reRewardText,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.type = type;
		this.title = title;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.handleType = handleType;
		this.reText = reText;
		this.reTime = reTime;
		this.reRewardText = reRewardText;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("OpinionEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Type=").append(getType()).append(", ")
			.append("Title=").append(getTitle()).append(", ")
			.append("Remark=").append(getRemark()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("CreateUserId=").append(getCreateUserId()).append(", ")
			.append("HandleType=").append(getHandleType()).append(", ")
			.append("ReText=").append(getReText()).append(", ")
			.append("ReTime=").append(getReTime()).append(", ")
			.append("ReRewardText=").append(getReRewardText()).append(", ")
			.append("CreateUserName=").append(getCreateUserName()).append(", ")
			.append("LastModifyPersonId=").append(getLastModifyPersonId()).append(", ")
			.append("LastModifyPersonName=").append(getLastModifyPersonName())
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
		if(obj instanceof OpinionEntity == false)
			return false;
		if(this == obj)
			return true;
		OpinionEntity other = (OpinionEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
