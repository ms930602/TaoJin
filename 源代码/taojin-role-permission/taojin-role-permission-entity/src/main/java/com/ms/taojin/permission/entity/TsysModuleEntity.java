/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.field;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-04-06 15:35:28
 * @since 1.0
 */
public class TsysModuleEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
    /** . */
	@field("")
	private Long id;
	
	/** 名称. */
	@field("名称")
	private String name;
	
	/** 图标. */
	@field("图标")
	private String iconCls;
	
	/** 名称. */
	@field("名称")
	private String caption;
	
	/** 父节点. */
	@field("父节点")
	private Long parentid;
	
	/** 排序. */
	@field("排序")
	private Long sort;
	
	/** 创建时间. */
	@field("创建时间")
	private java.util.Date createtime;
	private Long sysId;
	
	/** . */
	@field("")
	private String indexT;
	
	/** 系统id. */
	
	
	

    public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	/** set . */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get . */
	public Long getId() {
		return this.id;
	}
	

    /** set . */
	public void setName(String name) {
		this.name = name;
	}
	
	/** get . */
	public String getName() {
		return this.name;
	}
	

    /** set . */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	/** get . */
	public String getIconCls() {
		return this.iconCls;
	}
	

    /** set . */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	/** get . */
	public String getCaption() {
		return this.caption;
	}
	

    /** set . */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	
	/** get . */
	public Long getParentid() {
		return this.parentid;
	}
	

    /** set . */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	/** get . */
	public Long getSort() {
		return this.sort;
	}
	

    /** set . */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	/** get . */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	

    /** set . */
	public void setIndexT(String indexT) {
		this.indexT = indexT;
	}
	
	/** get . */
	public String getIndexT() {
		return this.indexT;
	}
	
    /** constructor */
	public TsysModuleEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param name			
	 * @param iconCls			
	 * @param caption			
	 * @param parentid			
	 * @param sort			
	 * @param createtime			
	 * @param indexT			
	 */
	public TsysModuleEntity(String name,String iconCls,String caption,Long parentid,Long sort,java.util.Date createtime,String indexT){
		this();
		this.name = name;
		this.iconCls = iconCls;
		this.caption = caption;
		this.parentid = parentid;
		this.sort = sort;
		this.createtime = createtime;
		this.indexT = indexT;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("TsysModuleEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("IconCls=").append(getIconCls()).append(", ")
			.append("Caption=").append(getCaption()).append(", ")
			.append("Parentid=").append(getParentid()).append(", ")
			.append("Sort=").append(getSort()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("IndexT=").append(getIndexT())
		.append("]").toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    	result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TsysModuleEntity == false) return false;
		if(this == obj) return true;
		TsysModuleEntity other = (TsysModuleEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}
