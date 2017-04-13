package xwgl.core.project.entity;
import java.io.Serializable;
import java.util.Date;

/***
 * 分类
 *
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import xwgl.common.entity.BaseEntity;
/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_order")
public class Order  extends BaseEntity<Long> implements Serializable{
	
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date orderDate;
	
	private String orderTime;
	
	private String addr;
	
	private String remark;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private FrontUser user;
	
	
	private String state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;
	
	private Double price;
	
	private Double toalprice;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getToalprice() {
		return toalprice;
	}
	public void setToalprice(Double toalprice) {
		this.toalprice = toalprice;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public FrontUser getUser() {
		return user;
	}
	public void setUser(FrontUser user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}