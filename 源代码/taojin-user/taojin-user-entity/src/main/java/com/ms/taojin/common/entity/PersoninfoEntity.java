
package com.ms.taojin.common.entity;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 人员信息
 * @author dwx
 * @Date 2018-07-09 18:27:44
 * @since 1.0
 */
@TableName("t_base_personinfo")
public class PersoninfoEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 自增id. */
	private Long id;
	
	/** 登录用户名. */
	private String loginName;
	
	/** 姓名. */
	private String personName;
	
	/** 部门. */
	private String department;
	
	/** 性别：0：女 1：男. */
	private Long sex;
	
	/** 工号. */
	private String jobno;
	
	/** 邮箱. */
	@Email(message="邮箱格式不正确")
	private String email;
	
	/** 是否可用 1：可用  0：不可用. */
	private Integer enabled;
	
	/** 备注. */
	private String remark;
	
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
	

    /** set 登录用户名. */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** get 登录用户名. */
	public String getLoginName() {
		return this.loginName;
	}
	
	@JsonIgnore
	public String getLoginNameByLike() {
		return "%"+this.loginName+"%";
	}
	

    /** set 姓名. */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	/** get 姓名. */
	public String getPersonName() {
		return this.personName;
	}
	
	@JsonIgnore
	public String getPersonNameByLike() {
		return "%"+this.personName+"%";
	}
	

    /** set 部门. */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/** get 部门. */
	public String getDepartment() {
		return this.department;
	}
	
	@JsonIgnore
	public String getDepartmentByLike() {
		return "%"+this.department+"%";
	}
	

    /** set 性别：0：女 1：男. */
	public void setSex(Long sex) {
		this.sex = sex;
	}
	
	/** get 性别：0：女 1：男. */
	public Long getSex() {
		return this.sex;
	}
	
	@JsonIgnore
	public Long getSexByLike() {
		return this.sex;
	}
	

    /** set 工号. */
	public void setJobno(String jobno) {
		this.jobno = jobno;
	}
	
	/** get 工号. */
	public String getJobno() {
		return this.jobno;
	}
	
	@JsonIgnore
	public String getJobnoByLike() {
		return "%"+this.jobno+"%";
	}
	

    /** set 邮箱. */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** get 邮箱. */
	public String getEmail() {
		return this.email;
	}
	
	@JsonIgnore
	public String getEmailByLike() {
		return "%"+this.email+"%";
	}
	

    /** set 是否可用 1：可用  0：不可用. */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	/** get 是否可用 1：可用  0：不可用. */
	public Integer getEnabled() {
		return this.enabled;
	}
	
	@JsonIgnore
	public Integer getEnabledByLike() {
		return this.enabled;
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
	public PersoninfoEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param loginName			登录用户名
	 * @param personName			姓名
	 * @param department			部门
	 * @param sex			性别：0：女 1：男
	 * @param jobno			工号
	 * @param email			邮箱
	 * @param enabled			是否可用 1：可用  0：不可用
	 * @param remark			备注
	 * @param createdDate			创建时间
	 * @param createdPersonId			创建人id
	 * @param createdPerson			创建人
	 * @param updatedDate			更新时间
	 * @param updatedPersonId			更新人id
	 * @param updatedPerson			修改人
	 */
	public PersoninfoEntity(String loginName,String personName,String department,Long sex,String jobno,String email,Integer enabled,String remark,java.util.Date createdDate,Long createdPersonId,String createdPerson,java.util.Date updatedDate,Long updatedPersonId,String updatedPerson){
		this();
		this.loginName = loginName;
		this.personName = personName;
		this.department = department;
		this.sex = sex;
		this.jobno = jobno;
		this.email = email;
		this.enabled = enabled;
		this.remark = remark;
		this.createdDate = createdDate;
		this.createdPersonId = createdPersonId;
		this.createdPerson = createdPerson;
		this.updatedDate = updatedDate;
		this.updatedPersonId = updatedPersonId;
		this.updatedPerson = updatedPerson;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("PersoninfoEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("LoginName=").append(getLoginName()).append(", ")
			.append("PersonName=").append(getPersonName()).append(", ")
			.append("Department=").append(getDepartment()).append(", ")
			.append("Sex=").append(getSex()).append(", ")
			.append("Jobno=").append(getJobno()).append(", ")
			.append("Email=").append(getEmail()).append(", ")
			.append("Enabled=").append(getEnabled()).append(", ")
			.append("Remark=").append(getRemark()).append(", ")
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
		if(obj instanceof PersoninfoEntity == false)
			return false;
		if(this == obj)
			return true;
		PersoninfoEntity other = (PersoninfoEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
