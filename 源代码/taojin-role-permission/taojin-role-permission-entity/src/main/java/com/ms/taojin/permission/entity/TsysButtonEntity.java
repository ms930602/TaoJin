/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.entity;

import com.ms.taojin.common.entity.BaseEntity;

/**
 * <p>
 * @author lansongtao
 * @Date 2017-04-13 14:53:37
 * @since 1.0
 */
public class TsysButtonEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private String api;
	
	/** . */
	private Long id;
	
	/** . */
	private Long moduleId;
	
	/** . */
	private String name;
	
	/** . */
	private java.util.Date createtime;
	
	/** . */
	private String zhName;
	
	/** . */
	private Long lag;
	
	

    /** set . */
	public void setApi(String api) {
		this.api = api;
	}
	
	/** get . */
	public String getApi() {
		return this.api;
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
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	/** get . */
	public Long getModuleId() {
		return this.moduleId;
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
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	/** get . */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	

    /** set . */
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
	
	/** get . */
	public String getZhName() {
		return this.zhName;
	}
	

    /** set . */
	public void setLag(Long lag) {
		this.lag = lag;
	}
	
	/** get . */
	public Long getLag() {
		return this.lag;
	}
	
    /** constructor */
	public TsysButtonEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param api			
	 * @param moduleId			
	 * @param name			
	 * @param createtime			
	 * @param zhName			
	 * @param lag			
	 */
	public TsysButtonEntity(String api,Long moduleId,String name,java.util.Date createtime,String zhName,Long lag){
		this();
		this.api = api;
		this.moduleId = moduleId;
		this.name = name;
		this.createtime = createtime;
		this.zhName = zhName;
		this.lag = lag;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("ButtonEntity[")
			.append("Api=").append(getApi()).append(", ")
			.append("Id=").append(getId()).append(", ")
			.append("ModuleId=").append(getModuleId()).append(", ")
			.append("Name=").append(getName()).append(", ")
			.append("Createtime=").append(getCreatetime()).append(", ")
			.append("ZhName=").append(getZhName()).append(", ")
			.append("Lag=").append(getLag())
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
		if(obj instanceof TsysButtonEntity == false) return false;
		if(this == obj) return true;
		TsysButtonEntity other = (TsysButtonEntity)obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return false;
	}
}