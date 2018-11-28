
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 
 * @author 蒙赛
 * @Date 2018-11-28 10:40:46
 * @since 1.0
 */
@TableName("t_ms_user_account_number")
public class UserAccountNumberEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 游戏外键. */
	private Long gameId;
	
	/** 账号名称. */
	private String loginName;
	
	/** 账号密码. */
	private String password;
	
	/** 0否 1 是. */
	private String status;
	
	/** 解印时间. */
	private java.util.Date useTime;
	
	/** 备注. */
	private String remark;
	
	/** 创建时间. */
	private java.util.Date createtime;
	
	/** 创建人外键. */
	private Long createUserId;
	
	/** 创建人姓名. */
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
	

    /** set 游戏外键. */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	/** get 游戏外键. */
	public Long getGameId() {
		return this.gameId;
	}
	
	@JsonIgnore
	public Long getGameIdByLike() {
		return this.gameId;
	}
	

    /** set 账号名称. */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** get 账号名称. */
	public String getLoginName() {
		return this.loginName;
	}
	
	@JsonIgnore
	public String getLoginNameByLike() {
		return "%"+this.loginName+"%";
	}
	

    /** set 账号密码. */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** get 账号密码. */
	public String getPassword() {
		return this.password;
	}
	
	@JsonIgnore
	public String getPasswordByLike() {
		return "%"+this.password+"%";
	}
	

    /** set 0否 1 是. */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/** get 0否 1 是. */
	public String getStatus() {
		return this.status;
	}
	
	@JsonIgnore
	public String getStatusByLike() {
		return this.status;
	}
	

    /** set 解印时间. */
	public void setUseTime(java.util.Date useTime) {
		this.useTime = useTime;
	}
	
	/** get 解印时间. */
	public java.util.Date getUseTime() {
		return this.useTime;
	}
	
	@JsonIgnore
	public java.util.Date getUseTimeByLike() {
		return this.useTime;
	}
	

    /** set 备注. */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/** get 备注. */
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
	

    /** set 创建人姓名. */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	/** get 创建人姓名. */
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
	public UserAccountNumberEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param gameId			游戏外键
	 * @param loginName			账号名称
	 * @param password			账号密码
	 * @param status			0否 1 是
	 * @param useTime			解印时间
	 * @param remark			备注
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public UserAccountNumberEntity(Long gameId,String loginName,String password,String status,java.util.Date useTime,String remark,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.gameId = gameId;
		this.loginName = loginName;
		this.password = password;
		this.status = status;
		this.useTime = useTime;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("UserAccountNumberEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("GameId=").append(getGameId()).append(", ")
			.append("LoginName=").append(getLoginName()).append(", ")
			.append("Password=").append(getPassword()).append(", ")
			.append("Status=").append(getStatus()).append(", ")
			.append("UseTime=").append(getUseTime()).append(", ")
			.append("Remark=").append(getRemark()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("CreateUserId=").append(getCreateUserId()).append(", ")
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
		if(obj instanceof UserAccountNumberEntity == false)
			return false;
		if(this == obj)
			return true;
		UserAccountNumberEntity other = (UserAccountNumberEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
