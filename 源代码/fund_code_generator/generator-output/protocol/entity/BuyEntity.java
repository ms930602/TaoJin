
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 
 * @author 蒙赛
 * @Date 2018-11-30 13:12:52
 * @since 1.0
 */
@TableName("t_ms_buy")
public class BuyEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 买入账号. */
	private String loginName;
	
	/** 游戏外键. */
	private Long gameId;
	
	/** 物品外键. */
	private Long itemId;
	
	/** 货币组号. */
	private String groupNo;
	
	/** 数量. */
	private Long quantity;
	
	/** 买入均价. */
	private java.math.BigDecimal price;
	
	/** 买入时间. */
	private java.util.Date buyTime;
	
	/** 是否期限 0 否 1是. */
	private String timeStatus;
	
	/** 到期日期. */
	private java.util.Date existTime;
	
	/** 0 正常 1 作废 2 卖完. */
	private String status;
	
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
	

    /** set 货币组号. */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	
	/** get 货币组号. */
	public String getGroupNo() {
		return this.groupNo;
	}
	
	@JsonIgnore
	public String getGroupNoByLike() {
		return this.groupNo;
	}
	

    /** set 数量. */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	/** get 数量. */
	public Long getQuantity() {
		return this.quantity;
	}
	
	@JsonIgnore
	public Long getQuantityByLike() {
		return this.quantity;
	}
	

    /** set 买入均价. */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	
	/** get 买入均价. */
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getPriceByLike() {
		return this.price;
	}
	

    /** set 买入时间. */
	public void setBuyTime(java.util.Date buyTime) {
		this.buyTime = buyTime;
	}
	
	/** get 买入时间. */
	public java.util.Date getBuyTime() {
		return this.buyTime;
	}
	
	@JsonIgnore
	public java.util.Date getBuyTimeByLike() {
		return this.buyTime;
	}
	

    /** set 是否期限 0 否 1是. */
	public void setTimeStatus(String timeStatus) {
		this.timeStatus = timeStatus;
	}
	
	/** get 是否期限 0 否 1是. */
	public String getTimeStatus() {
		return this.timeStatus;
	}
	
	@JsonIgnore
	public String getTimeStatusByLike() {
		return this.timeStatus;
	}
	

    /** set 到期日期. */
	public void setExistTime(java.util.Date existTime) {
		this.existTime = existTime;
	}
	
	/** get 到期日期. */
	public java.util.Date getExistTime() {
		return this.existTime;
	}
	
	@JsonIgnore
	public java.util.Date getExistTimeByLike() {
		return this.existTime;
	}
	

    /** set 0 正常 1 作废 2 卖完. */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/** get 0 正常 1 作废 2 卖完. */
	public String getStatus() {
		return this.status;
	}
	
	@JsonIgnore
	public String getStatusByLike() {
		return this.status;
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
	public BuyEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param loginName			买入账号
	 * @param gameId			游戏外键
	 * @param itemId			物品外键
	 * @param groupNo			货币组号
	 * @param quantity			数量
	 * @param price			买入均价
	 * @param buyTime			买入时间
	 * @param timeStatus			是否期限 0 否 1是
	 * @param existTime			到期日期
	 * @param status			0 正常 1 作废 2 卖完
	 * @param remark			备注
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 * @param openStatus			是否公开
	 */
	public BuyEntity(String loginName,Long gameId,Long itemId,String groupNo,Long quantity,java.math.BigDecimal price,java.util.Date buyTime,String timeStatus,java.util.Date existTime,String status,String remark,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName,String openStatus){
		this();
		this.loginName = loginName;
		this.gameId = gameId;
		this.itemId = itemId;
		this.groupNo = groupNo;
		this.quantity = quantity;
		this.price = price;
		this.buyTime = buyTime;
		this.timeStatus = timeStatus;
		this.existTime = existTime;
		this.status = status;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.openStatus = openStatus;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("BuyEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("LoginName=").append(getLoginName()).append(", ")
			.append("GameId=").append(getGameId()).append(", ")
			.append("ItemId=").append(getItemId()).append(", ")
			.append("GroupNo=").append(getGroupNo()).append(", ")
			.append("Quantity=").append(getQuantity()).append(", ")
			.append("Price=").append(getPrice()).append(", ")
			.append("BuyTime=").append(getBuyTime()).append(", ")
			.append("TimeStatus=").append(getTimeStatus()).append(", ")
			.append("ExistTime=").append(getExistTime()).append(", ")
			.append("Status=").append(getStatus()).append(", ")
			.append("Remark=").append(getRemark()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("CreateUserId=").append(getCreateUserId()).append(", ")
			.append("CreateUserName=").append(getCreateUserName()).append(", ")
			.append("LastModifyPersonId=").append(getLastModifyPersonId()).append(", ")
			.append("LastModifyPersonName=").append(getLastModifyPersonName()).append(", ")
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
		if(obj instanceof BuyEntity == false)
			return false;
		if(this == obj)
			return true;
		BuyEntity other = (BuyEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
