package com.wisdom.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity

@Table(name = "module")
public class Module implements Serializable {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int unsigned NOT NULL comment '备注:模块id'")
	private Integer moduleId;
	private Integer parentId;
	@JsonProperty("text")
	private String moduleName;
	@Column(columnDefinition = "int DEFAULT 0 comment '备注：模块权重,列表顺序'")
	private Integer moduleWeight;
	@Column(columnDefinition = "varchar(50) comment '备注：模块对应页面url路径'")
	private String moduleUrl;
	@Column(columnDefinition = "dateTime comment '备注：模块创建时间'")
	private Date moduleCreateTime;
	@Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false, insertable = false)
	private Timestamp moduleLastUpdateTime;
	@Column(columnDefinition = "int Default 1 comment '备注：是否可用：0不可用 1可用'")
	private Integer status;

	@Transient
	private List<Module> children;
	@Transient
	private Boolean checked;

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(Integer moduleId, Integer parentId, String moduleName, Integer moduleWeight, String moduleUrl,
			Date moduleCreateTime, Timestamp moduleLastUpdateTime, Integer status, List<Module> children,
			Boolean checked) {
		super();
		this.moduleId = moduleId;
		this.parentId = parentId;
		this.moduleName = moduleName;
		this.moduleWeight = moduleWeight;
		this.moduleUrl = moduleUrl;
		this.moduleCreateTime = moduleCreateTime;
		this.moduleLastUpdateTime = moduleLastUpdateTime;
		this.status = status;
		this.children = children;
		this.checked = checked;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModuleWeight() {
		return moduleWeight;
	}

	public void setModuleWeight(Integer moduleWeight) {
		this.moduleWeight = moduleWeight;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Date getModuleCreateTime() {
		return moduleCreateTime;
	}

	public void setModuleCreateTime(Date moduleCreateTime) {
		this.moduleCreateTime = moduleCreateTime;
	}

	public Timestamp getModuleLastUpdateTime() {
		return moduleLastUpdateTime;
	}

	public void setModuleLastUpdateTime(Timestamp moduleLastUpdateTime) {
		this.moduleLastUpdateTime = moduleLastUpdateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", parentId=" + parentId + ", moduleName=" + moduleName
				+ ", moduleWeight=" + moduleWeight + ", moduleUrl=" + moduleUrl + ", moduleCreateTime="
				+ moduleCreateTime + ", moduleLastUpdateTime=" + moduleLastUpdateTime + ", status=" + status
				+ ", children=" + children + ", checked=" + checked + "]";
	}

}