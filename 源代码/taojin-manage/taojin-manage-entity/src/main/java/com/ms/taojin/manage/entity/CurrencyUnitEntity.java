
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 游戏货币单位
 * @author 蒙赛
 * @Date 2018-11-28 09:30:03
 * @since 1.0
 */
@TableName("t_ms_currency_unit")
public class CurrencyUnitEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 游戏外键. */
	private Long gameId;
	
	/** 名称. */
	private String name;
	
	/** 系数 该货币转换成最低货币. */
	private java.math.BigDecimal ratio;
	
	/** 组号 区分货币类别. */
	private String group;
	
	/** 组名. */
	private String groupName;
	
	/** 序号. */
	private Long sort;
	
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
	

    /** set 系数 该货币转换成最低货币. */
	public void setRatio(java.math.BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	/** get 系数 该货币转换成最低货币. */
	public java.math.BigDecimal getRatio() {
		return this.ratio;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getRatioByLike() {
		return this.ratio;
	}
	

    /** set 组号 区分货币类别. */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/** get 组号 区分货币类别. */
	public String getGroup() {
		return this.group;
	}
	
	@JsonIgnore
	public String getGroupByLike() {
		return this.group;
	}
	

    /** set 组名. */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/** get 组名. */
	public String getGroupName() {
		return this.groupName;
	}
	
	@JsonIgnore
	public String getGroupNameByLike() {
		return "%"+this.groupName+"%";
	}
	

    /** set 序号. */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	/** get 序号. */
	public Long getSort() {
		return this.sort;
	}
	
	@JsonIgnore
	public Long getSortByLike() {
		return this.sort;
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
	public CurrencyUnitEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param gameId			游戏外键
	 * @param name			名称
	 * @param ratio			系数 该货币转换成最低货币
	 * @param group			组号 区分货币类别
	 * @param groupName			组名
	 * @param sort			序号
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public CurrencyUnitEntity(Long gameId,String name,java.math.BigDecimal ratio,String group,String groupName,Long sort,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.gameId = gameId;
		this.name = name;
		this.ratio = ratio;
		this.group = group;
		this.groupName = groupName;
		this.sort = sort;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("CurrencyUnitEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("GameId=").append(getGameId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Ratio=").append(getRatio()).append(", ")
			.append("Group=").append(getGroup()).append(", ")
			.append("GroupName=").append(getGroupName()).append(", ")
			.append("Sort=").append(getSort()).append(", ")
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
		if(obj instanceof CurrencyUnitEntity == false)
			return false;
		if(this == obj)
			return true;
		CurrencyUnitEntity other = (CurrencyUnitEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
