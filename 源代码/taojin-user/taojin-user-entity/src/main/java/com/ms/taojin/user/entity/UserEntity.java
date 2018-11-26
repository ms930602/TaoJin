
package com.ms.taojin.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 用户
 * @author lansongtao
 * @Date 2018-05-02 10:05:15
 * @since 1.0
 */
@TableName("t_center_user")
public class UserEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 用户ID. */
	private Long userId;
	
	/** 登录名. */
	private String loginName;
	
	/** 登录密码. */
	@JsonIgnore
	private String password;
	
	/** 昵称. */
	private String nickName;
	
	/** 用户手机. */
	private String mobile;
	
	/** 身份证号. */
	private String identityCard;
	
	/** 状态. */
	private Integer status;
	
	/** 系统id. */
	private String sysId;
	
	/** 用户类型id(是市场管理员还是商家). */
	private String typeId;
	
	/** 用户类型编码(商家就存商家编码、市场就存市场编码). */
	private String typeCode;
	
	/** 最后一次登录时间. */
	private java.util.Date lastLoginTime;
	
	/** 登录失败次数. */
	private Long loginFaildTimes;
	
	/** 前几次使用过的密码. */
	@JsonIgnore
	private String oldPasswords;
	
	/** 上一次修改密码的时间. */
	private java.util.Date editPwdTime;
	
	/** 用户头像. */
	private String filePath;
	
	/** 创建人(经营户备案Id). */
	private Long createdPersonId;
	
	/** 创建人名称. */
	private String createdPersonName;
	
	/** 创建时间. */
	private java.util.Date createdDate;
	
	

    /** set 用户ID. */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/** get 用户ID. */
	public Long getUserId() {
		return this.userId;
	}
	
	@JsonIgnore
	public Long getUserIdByLike() {
		return this.userId;
	}
	

    /** set 登录名. */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** get 登录名. */
	public String getLoginName() {
		return this.loginName;
	}
	
	@JsonIgnore
	public String getLoginNameByLike() {
		return "%"+this.loginName+"%";
	}
	

    /** set 登录密码. */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** get 登录密码. */
	public String getPassword() {
		return this.password;
	}
	
	@JsonIgnore
	public String getPasswordByLike() {
		return "%"+this.password+"%";
	}
	

    /** set 昵称. */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	/** get 昵称. */
	public String getNickName() {
		return this.nickName;
	}
	
	@JsonIgnore
	public String getNickNameByLike() {
		return "%"+this.nickName+"%";
	}
	

    /** set 用户手机. */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/** get 用户手机. */
	public String getMobile() {
		return this.mobile;
	}
	
	@JsonIgnore
	public String getMobileByLike() {
		return this.mobile;
	}
	

    /** set 身份证号. */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	/** get 身份证号. */
	public String getIdentityCard() {
		return this.identityCard;
	}
	
	@JsonIgnore
	public String getIdentityCardByLike() {
		return this.identityCard;
	}
	

    /** set 状态. */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/** get 状态. */
	public Integer getStatus() {
		return this.status;
	}
	
	@JsonIgnore
	public Integer getStatusByLike() {
		return this.status;
	}
	

    /** set 系统id. */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	
	/** get 系统id. */
	public String getSysId() {
		return this.sysId;
	}
	
	@JsonIgnore
	public String getSysIdByLike() {
		return "%"+this.sysId+"%";
	}
	

    /** set 用户类型id(是市场管理员还是商家). */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	/** get 用户类型id(是市场管理员还是商家). */
	public String getTypeId() {
		return this.typeId;
	}
	
	@JsonIgnore
	public String getTypeIdByLike() {
		return "%"+this.typeId+"%";
	}
	

    /** set 用户类型编码(商家就存商家编码、市场就存市场编码). */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	/** get 用户类型编码(商家就存商家编码、市场就存市场编码). */
	public String getTypeCode() {
		return this.typeCode;
	}
	
	@JsonIgnore
	public String getTypeCodeByLike() {
		return "%"+this.typeCode+"%";
	}
	

    /** set 最后一次登录时间. */
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	/** get 最后一次登录时间. */
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	@JsonIgnore
	public java.util.Date getLastLoginTimeByLike() {
		return this.lastLoginTime;
	}
	

    /** set 登录失败次数. */
	public void setLoginFaildTimes(Long loginFaildTimes) {
		this.loginFaildTimes = loginFaildTimes;
	}
	
	/** get 登录失败次数. */
	public Long getLoginFaildTimes() {
		return this.loginFaildTimes;
	}
	
	@JsonIgnore
	public Long getLoginFaildTimesByLike() {
		return this.loginFaildTimes;
	}
	

    /** set 前几次使用过的密码. */
	public void setOldPasswords(String oldPasswords) {
		this.oldPasswords = oldPasswords;
	}
	
	/** get 前几次使用过的密码. */
	public String getOldPasswords() {
		return this.oldPasswords;
	}
	
	@JsonIgnore
	public String getOldPasswordsByLike() {
		return "%"+this.oldPasswords+"%";
	}
	

    /** set 上一次修改密码的时间. */
	public void setEditPwdTime(java.util.Date editPwdTime) {
		this.editPwdTime = editPwdTime;
	}
	
	/** get 上一次修改密码的时间. */
	public java.util.Date getEditPwdTime() {
		return this.editPwdTime;
	}
	
	@JsonIgnore
	public java.util.Date getEditPwdTimeByLike() {
		return this.editPwdTime;
	}
	

    /** set 用户头像. */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/** get 用户头像. */
	public String getFilePath() {
		return this.filePath;
	}
	
	@JsonIgnore
	public String getFilePathByLike() {
		return "%"+this.filePath+"%";
	}
	

    /** set 创建人(经营户备案Id). */
	public void setCreatedPersonId(Long createdPersonId) {
		this.createdPersonId = createdPersonId;
	}
	
	/** get 创建人(经营户备案Id). */
	public Long getCreatedPersonId() {
		return this.createdPersonId;
	}
	
	@JsonIgnore
	public Long getCreatedPersonIdByLike() {
		return this.createdPersonId;
	}
	

    /** set 创建人名称. */
	public void setCreatedPersonName(String createdPersonName) {
		this.createdPersonName = createdPersonName;
	}
	
	/** get 创建人名称. */
	public String getCreatedPersonName() {
		return this.createdPersonName;
	}
	
	@JsonIgnore
	public String getCreatedPersonNameByLike() {
		return "%"+this.createdPersonName+"%";
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
	
    /** constructor */
	public UserEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param loginName			登录名
	 * @param password			登录密码
	 * @param nickName			昵称
	 * @param mobile			用户手机
	 * @param identityCard			身份证号
	 * @param status			状态
	 * @param sysId			系统id
	 * @param typeId			用户类型id(是市场管理员还是商家)
	 * @param typeCode			用户类型编码(商家就存商家编码、市场就存市场编码)
	 * @param lastLoginTime			最后一次登录时间
	 * @param loginFaildTimes			登录失败次数
	 * @param oldPasswords			前几次使用过的密码
	 * @param editPwdTime			上一次修改密码的时间
	 * @param filePath			用户头像
	 * @param createdPersonId			创建人(经营户备案Id)
	 * @param createdPersonName			创建人名称
	 * @param createdDate			创建时间
	 */
	public UserEntity(String loginName,String password,String nickName,String mobile,String identityCard,Integer status,String sysId,String typeId,String typeCode,java.util.Date lastLoginTime,Long loginFaildTimes,String oldPasswords,java.util.Date editPwdTime,String filePath,Long createdPersonId,String createdPersonName,java.util.Date createdDate){
		this();
		this.loginName = loginName;
		this.password = password;
		this.nickName = nickName;
		this.mobile = mobile;
		this.identityCard = identityCard;
		this.status = status;
		this.sysId = sysId;
		this.typeId = typeId;
		this.typeCode = typeCode;
		this.lastLoginTime = lastLoginTime;
		this.loginFaildTimes = loginFaildTimes;
		this.oldPasswords = oldPasswords;
		this.editPwdTime = editPwdTime;
		this.filePath = filePath;
		this.createdPersonId = createdPersonId;
		this.createdPersonName = createdPersonName;
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("UserEntity[")
			.append("UserId=").append(getUserId()).append(", ")
			.append("LoginName=").append(getLoginName()).append(", ")
			.append("Password=").append(getPassword()).append(", ")
			.append("NickName=").append(getNickName()).append(", ")
			.append("Mobile=").append(getMobile()).append(", ")
			.append("IdentityCard=").append(getIdentityCard()).append(", ")
			.append("Status=").append(getStatus()).append(", ")
			.append("SysId=").append(getSysId()).append(", ")
			.append("TypeId=").append(getTypeId()).append(", ")
			.append("TypeCode=").append(getTypeCode()).append(", ")
			.append("LastLoginTime=").append(getLastLoginTime()).append(", ")
			.append("LoginFaildTimes=").append(getLoginFaildTimes()).append(", ")
			.append("OldPasswords=").append(getOldPasswords()).append(", ")
			.append("EditPwdTime=").append(getEditPwdTime()).append(", ")
			.append("FilePath=").append(getFilePath()).append(", ")
			.append("CreatedPersonId=").append(getCreatedPersonId()).append(", ")
			.append("CreatedPersonName=").append(getCreatedPersonName()).append(", ")
			.append("CreatedDate=").append(getCreatedDate())
		.append("]").toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    	result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserEntity == false) return false;
		if(this == obj) return true;
		UserEntity other = (UserEntity)obj;
		if (getUserId() == null) {
			if (other.getUserId() != null)
				return false;
		} else if (!getUserId().equals(other.getUserId()))
			return false;
		return false;
	}
}
