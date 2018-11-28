
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 物品信息
 * @author 蒙赛
 * @Date 2018-11-28 09:30:09
 * @since 1.0
 */
@TableName("t_ms_item")
public class ItemEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 游戏主键. */
	private Long 游戏主键;
	
	/** 名称. */
	private String name;
	
	/** 类型 1永久 2 时限 3元宝. */
	private String type;
	
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
	

    /** set 游戏主键. */
	public void set游戏主键(Long 游戏主键) {
		this.游戏主键 = 游戏主键;
	}
	
	/** get 游戏主键. */
	public Long get游戏主键() {
		return this.游戏主键;
	}
	
	@JsonIgnore
	public Long get游戏主键ByLike() {
		return this.游戏主键;
	}
	

    /** set 名称. */
	public void setName(String name) {
		this.name = name;
	}
	
	/** get 名称. */
	public String getName() {
		return this.name;
	}
	
	@JsonIgnore
	public String getNameByLike() {
		return "%"+this.name+"%";
	}
	

    /** set 类型 1永久 2 时限 3元宝. */
	public void setType(String type) {
		this.type = type;
	}
	
	/** get 类型 1永久 2 时限 3元宝. */
	public String getType() {
		return this.type;
	}
	
	@JsonIgnore
	public String getTypeByLike() {
		return this.type;
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
	public ItemEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param 游戏主键			游戏主键
	 * @param name			名称
	 * @param type			类型 1永久 2 时限 3元宝
	 * @param remark			备注
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public ItemEntity(Long 游戏主键,String name,String type,String remark,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.游戏主键 = 游戏主键;
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("ItemEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("游戏主键=").append(get游戏主键()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Type=").append(getType()).append(", ")
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
		if(obj instanceof ItemEntity == false)
			return false;
		if(this == obj)
			return true;
		ItemEntity other = (ItemEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
