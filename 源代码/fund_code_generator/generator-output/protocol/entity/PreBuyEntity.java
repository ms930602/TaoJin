
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 预买入
 * @author 蒙赛
 * @Date 2018-11-30 13:12:47
 * @since 1.0
 */
@TableName("t_ms_pre_buy")
public class PreBuyEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 游戏外键. */
	private Long gameId;
	
	/** 物品外键. */
	private Long itemId;
	
	/** 买入账号. */
	private String loginName;
	
	/** 预买数量. */
	private java.math.BigDecimal quantity;
	
	/** 预计价格. */
	private java.math.BigDecimal price;
	
	/** 预买入时间. */
	private java.util.Date time;
	
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
	
	/** 0 正常 1作废. */
	private String status;
	
	/** 是否公开. */
	private String openStatus;
	
	

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
	

    /** set 物品外键. */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	/** get 物品外键. */
	public Long getItemId() {
		return this.itemId;
	}
	
	@JsonIgnore
	public Long getItemIdByLike() {
		return this.itemId;
	}
	

    /** set 买入账号. */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** get 买入账号. */
	public String getLoginName() {
		return this.loginName;
	}
	
	@JsonIgnore
	public String getLoginNameByLike() {
		return "%"+this.loginName+"%";
	}
	

    /** set 预买数量. */
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/** get 预买数量. */
	public java.math.BigDecimal getQuantity() {
		return this.quantity;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getQuantityByLike() {
		return this.quantity;
	}
	

    /** set 预计价格. */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	
	/** get 预计价格. */
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getPriceByLike() {
		return this.price;
	}
	

    /** set 预买入时间. */
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	
	/** get 预买入时间. */
	public java.util.Date getTime() {
		return this.time;
	}
	
	@JsonIgnore
	public java.util.Date getTimeByLike() {
		return this.time;
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
	

    /** set 0 正常 1作废. */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/** get 0 正常 1作废. */
	public String getStatus() {
		return this.status;
	}
	
	@JsonIgnore
	public String getStatusByLike() {
		return this.status;
	}
	

    /** set 是否公开. */
	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}
	
	/** get 是否公开. */
	public String getOpenStatus() {
		return this.openStatus;
	}
	
	@JsonIgnore
	public String getOpenStatusByLike() {
		return this.openStatus;
	}
	
    /** constructor */
	public PreBuyEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param gameId			游戏外键
	 * @param itemId			物品外键
	 * @param loginName			买入账号
	 * @param quantity			预买数量
	 * @param price			预计价格
	 * @param time			预买入时间
	 * @param remark			备注
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 * @param status			0 正常 1作废
	 * @param openStatus			是否公开
	 */
	public PreBuyEntity(Long gameId,Long itemId,String loginName,java.math.BigDecimal quantity,java.math.BigDecimal price,java.util.Date time,String remark,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName,String status,String openStatus){
		this();
		this.gameId = gameId;
		this.itemId = itemId;
		this.loginName = loginName;
		this.quantity = quantity;
		this.price = price;
		this.time = time;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.status = status;
		this.openStatus = openStatus;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("PreBuyEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("GameId=").append(getGameId()).append(", ")
			.append("ItemId=").append(getItemId()).append(", ")
			.append("LoginName=").append(getLoginName()).append(", ")
			.append("Quantity=").append(getQuantity()).append(", ")
			.append("Price=").append(getPrice()).append(", ")
			.append("Time=").append(getTime()).append(", ")
			.append("Remark=").append(getRemark()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("CreateUserId=").append(getCreateUserId()).append(", ")
			.append("CreateUserName=").append(getCreateUserName()).append(", ")
			.append("LastModifyPersonId=").append(getLastModifyPersonId()).append(", ")
			.append("LastModifyPersonName=").append(getLastModifyPersonName()).append(", ")
			.append("Status=").append(getStatus()).append(", ")
			.append("OpenStatus=").append(getOpenStatus())
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
		if(obj instanceof PreBuyEntity == false)
			return false;
		if(this == obj)
			return true;
		PreBuyEntity other = (PreBuyEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
