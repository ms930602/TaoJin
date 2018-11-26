
package com.ms.taojin.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

/**
 * 上传文件
 * @author wangwei
 * @Date 2018-08-03 09:55:30
 * @since 1.0
 */
@TableName("t_base_uploadfilerecode")
public class BaseUploadfilerecodeEntity extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** 自增ID. */
	private Long id;
	
	/** 主体对象类型. */
	private String sourceObjectType;
	
	/** 文件类型. */
	private String fileType;
	
	/** 文件大小. */
	private Long fileSize;
	
	/** 主题对象Id. */
	private Long sourceObjectId;
	
	/** 对象存储路径. */
	private String filePath;
	
	/** 系统文件名. */
	private String systemFileName;
	
	/** 文件名. */
	private String fileName;
	
	/** 上传时间. */
	private java.util.Date uploadDate;
	
	

    /** set 自增ID. */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 自增ID. */
	public Long getId() {
		return this.id;
	}
	
	@JsonIgnore
	public Long getIdByLike() {
		return this.id;
	}
	

    /** set 主体对象类型. */
	public void setSourceObjectType(String sourceObjectType) {
		this.sourceObjectType = sourceObjectType;
	}
	
	/** get 主体对象类型. */
	public String getSourceObjectType() {
		return this.sourceObjectType;
	}
	
	@JsonIgnore
	public String getSourceObjectTypeByLike() {
		return "%"+this.sourceObjectType+"%";
	}
	

    /** set 文件类型. */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	/** get 文件类型. */
	public String getFileType() {
		return this.fileType;
	}
	
	@JsonIgnore
	public String getFileTypeByLike() {
		return "%"+this.fileType+"%";
	}
	

    /** set 文件大小. */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	/** get 文件大小. */
	public Long getFileSize() {
		return this.fileSize;
	}
	
	@JsonIgnore
	public Long getFileSizeByLike() {
		return this.fileSize;
	}
	

    /** set 主题对象Id. */
	public void setSourceObjectId(Long sourceObjectId) {
		this.sourceObjectId = sourceObjectId;
	}
	
	/** get 主题对象Id. */
	public Long getSourceObjectId() {
		return this.sourceObjectId;
	}
	
	@JsonIgnore
	public Long getSourceObjectIdByLike() {
		return this.sourceObjectId;
	}
	

    /** set 对象存储路径. */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/** get 对象存储路径. */
	public String getFilePath() {
		return this.filePath;
	}
	
	@JsonIgnore
	public String getFilePathByLike() {
		return "%"+this.filePath+"%";
	}
	

    /** set 系统文件名. */
	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}
	
	/** get 系统文件名. */
	public String getSystemFileName() {
		return this.systemFileName;
	}
	
	@JsonIgnore
	public String getSystemFileNameByLike() {
		return "%"+this.systemFileName+"%";
	}
	

    /** set 文件名. */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/** get 文件名. */
	public String getFileName() {
		return this.fileName;
	}
	
	@JsonIgnore
	public String getFileNameByLike() {
		return "%"+this.fileName+"%";
	}
	

    /** set 上传时间. */
	public void setUploadDate(java.util.Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	/** get 上传时间. */
	public java.util.Date getUploadDate() {
		return this.uploadDate;
	}
	
	@JsonIgnore
	public java.util.Date getUploadDateByLike() {
		return this.uploadDate;
	}
	
    /** constructor */
	public BaseUploadfilerecodeEntity() {
		super();
	}

	/**
	 * constructor.<p>
	 * @param sourceObjectType			主体对象类型
	 * @param fileType			文件类型
	 * @param fileSize			文件大小
	 * @param sourceObjectId			主题对象Id
	 * @param filePath			对象存储路径
	 * @param systemFileName			系统文件名
	 * @param fileName			文件名
	 * @param uploadDate			上传时间
	 */
	public BaseUploadfilerecodeEntity(String sourceObjectType,String fileType,Long fileSize,Long sourceObjectId,String filePath,String systemFileName,String fileName,java.util.Date uploadDate){
		this();
		this.sourceObjectType = sourceObjectType;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.sourceObjectId = sourceObjectId;
		this.filePath = filePath;
		this.systemFileName = systemFileName;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("BaseUploadfilerecodeEntity[")
			.append("Id=").append(getId()).append(", ")
			.append("SourceObjectType=").append(getSourceObjectType()).append(", ")
			.append("FileType=").append(getFileType()).append(", ")
			.append("FileSize=").append(getFileSize()).append(", ")
			.append("SourceObjectId=").append(getSourceObjectId()).append(", ")
			.append("FilePath=").append(getFilePath()).append(", ")
			.append("SystemFileName=").append(getSystemFileName()).append(", ")
			.append("FileName=").append(getFileName()).append(", ")
			.append("UploadDate=").append(getUploadDate())
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
		if(obj instanceof BaseUploadfilerecodeEntity == false)
			return false;
		if(this == obj)
			return true;
		BaseUploadfilerecodeEntity other = (BaseUploadfilerecodeEntity)obj;
		
		return this.toString().equals(other.toString());
	}
}
