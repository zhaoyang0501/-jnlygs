package xwgl.core.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import xwgl.common.entity.BaseEntity;

@Entity
@Table(name = "t_Project")
public class Project extends BaseEntity<Long> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2293830335471184935L;

	private String title;
	
	@Column(columnDefinition="TEXT") 
	private String remark;
	
	@ManyToOne
	private Category category;
	
	private String user;
	
	private String unit;
	
	private String name;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	private Double price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date createDate;
}
