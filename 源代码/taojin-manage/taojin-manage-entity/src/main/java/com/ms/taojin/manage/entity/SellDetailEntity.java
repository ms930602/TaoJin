
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 
 * @author 蒙赛
 * @Date 2018-11-28 09:30:22
 * @since 1.0
 */
@TableName("t_ms_sell_detail")
public class SellDetailEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private Long id;
	
	/** 主表外键. */
	private Long mainId;
	
	/** 买入外键. */
	private Long buyId;
	
	/** 比率外键. */
	private Long ratioId;
	
	/** 数量. */
	private java.math.BigDecimal quantity;
	
	/** 卖出价格. */
	private java.math.BigDecimal price;
	
	/** 卖出时间. */
	private java.util.Date sellTime;
	
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
	

    /** set 主表外键. */
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	
	/** get 主表外键. */
	public Long getMainId() {
		return this.mainId;
	}
	
	@JsonIgnore
	public Long getMainIdByLike() {
		return this.mainId;
	}
	

    /** set 买入外键. */
	public void setBuyId(Long buyId) {
		this.buyId = buyId;
	}
	
	/** get 买入外键. */
	public Long getBuyId() {
		return this.buyId;
	}
	
	@JsonIgnore
	public Long getBuyIdByLike() {
		return this.buyId;
	}
	

    /** set 比率外键. */
	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
	}
	
	/** get 比率外键. */
	public Long getRatioId() {
		return this.ratioId;
	}
	
	@JsonIgnore
	public Long getRatioIdByLike() {
		return this.ratioId;
	}
	

    /** set 数量. */
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/** get 数量. */
	public java.math.BigDecimal getQuantity() {
		return this.quantity;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getQuantityByLike() {
		return this.quantity;
	}
	

    /** set 卖出价格. */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	
	/** get 卖出价格. */
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	@JsonIgnore
	public java.math.BigDecimal getPriceByLike() {
		return this.price;
	}
	

    /** set 卖出时间. */
	public void setSellTime(java.util.Date sellTime) {
		this.sellTime = sellTime;
	}
	
	/** get 卖出时间. */
	public java.util.Date getSellTime() {
		return this.sellTime;
	}
	
	@JsonIgnore
	public java.util.Date getSellTimeByLike() {
		return this.sellTime;
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
	public SellDetailEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param mainId			主表外键
	 * @param buyId			买入外键
	 * @param ratioId			比率外键
	 * @param quantity			数量
	 * @param price			卖出价格
	 * @param sellTime			卖出时间
	 * @param remark			备注
	 * @param createtime			创建时间
	 * @param createUserId			创建人外键
	 * @param createUserName			创建人姓名
	 * @param lastModifyPersonId			最后修改人外键
	 * @param lastModifyPersonName			最后修改人姓名
	 */
	public SellDetailEntity(Long mainId,Long buyId,Long ratioId,java.math.BigDecimal quantity,java.math.BigDecimal price,java.util.Date sellTime,String remark,java.util.Date createtime,Long createUserId,String createUserName,Long lastModifyPersonId,String lastModifyPersonName){
		this();
		this.mainId = mainId;
		this.buyId = buyId;
		this.ratioId = ratioId;
		this.quantity = quantity;
		this.price = price;
		this.sellTime = sellTime;
		this.remark = remark;
		this.createtime = createtime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("SellDetailEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("MainId=").append(getMainId()).append(", ")
			.append("BuyId=").append(getBuyId()).append(", ")
			.append("RatioId=").append(getRatioId()).append(", ")
			.append("Quantity=").append(getQuantity()).append(", ")
			.append("Price=").append(getPrice()).append(", ")
			.append("SellTime=").append(getSellTime()).append(", ")
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
		if(obj instanceof SellDetailEntity == false)
			return false;
		if(this == obj)
			return true;
		SellDetailEntity other = (SellDetailEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
