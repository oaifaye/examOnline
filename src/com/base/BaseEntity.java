package com.base;

import java.util.Date;

/**
 * 实体类父类
 * 
 * @文件名称: BaseEntity.java
 */
public class BaseEntity {

	private String id;
	/** 创建者(登录帐号) */
	private String createBy;

	/** 创建时间 */
	private Date createDate = new Date();

	/** 最后更新者(登录帐号) */
	private String updateBy;

	/** 最后更新时间 */
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
