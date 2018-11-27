
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 游戏
 * @author 蒙赛
 * @Date 2018-11-27 15:29:47
 * @since 1.0
 */
@TableName("t_ms_game")
public class GameEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 名称. */
	private String name;
	
	/** 类型. */
	private String type;
	
	/** 扩展字段1. */
	private Long columnA;
	
	/** 扩展字段2. */
	private Long columnB;
	
	/** 扩展字段3. */
	private String columnC;
	
	/** 扩展字段4. */
	private String columnD;
	
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
	

    /** set 类型. */
	public void setType(String type) {
		this.type = type;
	}
	
	/** get 类型. */
	public String getType() {
		return this.type;
	}
	
	@JsonIgnore
	public String getTypeByLike() {
		return this.type;
	}
	

    /** set 扩展字段1. */
	public void setColumnA(Long columnA) {
		this.columnA = columnA;
	}
	
	/** get 扩展字段1. */
	public Long getColumnA() {
		return this.columnA;
	}
	
	@JsonIgnore
	public Long getColumnAByLike() {
		return this.columnA;
	}
	

    /** set 扩展字段2. */
	public void setColumnB(Long columnB) {
		this.columnB = columnB;
	}
	
	/** get 扩展字段2. */
	public Long getColumnB() {
		return this.columnB;
	}
	
	@JsonIgnore
	public Long getColumnBByLike() {
		return this.columnB;
	}
	

    /** set 扩展字段3. */
	public void setColumnC(String columnC) {
		this.columnC = columnC;
	}
	
	/** get 扩展字段3. */
	public String getColumnC() {
		return this.columnC;
	}
	
	@JsonIgnore
	public String getColumnCByLike() {
		return "%"+this.columnC+"%";
	}
	

    /** set 扩展字段4. */
	public void setColumnD(String columnD) {
		this.columnD = columnD;
	}
	
	/** get 扩展字段4. */
	public String getColumnD() {
		return this.columnD;
	}
	
	@JsonIgnore
	public String getColumnDByLike() {
		return "%"+this.columnD+"%";
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
	public GameEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param name			名称
	 * @param type			类型
	 * @param columnA			扩展字段1
	 * @param columnB			扩展字段2
	 * @param columnC			扩展字段3
	 * @param columnD			扩展字段4
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public GameEntity(String name,String type,Long columnA,Long columnB,String columnC,String columnD,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.name = name;
		this.type = type;
		this.columnA = columnA;
		this.columnB = columnB;
		this.columnC = columnC;
		this.columnD = columnD;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("GameEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Type=").append(getType()).append(", ")
			.append("ColumnA=").append(getColumnA()).append(", ")
			.append("ColumnB=").append(getColumnB()).append(", ")
			.append("ColumnC=").append(getColumnC()).append(", ")
			.append("ColumnD=").append(getColumnD()).append(", ")
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
		if(obj instanceof GameEntity == false)
			return false;
		if(this == obj)
			return true;
		GameEntity other = (GameEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
