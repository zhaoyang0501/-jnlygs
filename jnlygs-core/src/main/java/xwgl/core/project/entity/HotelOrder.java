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
@Table(name = "t_hotelorder")
public class HotelOrder extends BaseEntity<Long> implements Serializable{
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date orderDate;
	
	private String type;
	
	private String orderTime;
	
	private String addr;
	
	private String remark;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private FrontUser user;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date createDate;
	
	private String state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Hotel hotel;
	
	private Integer price;
	
	private Integer toalprice;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getToalprice() {
		return toalprice;
	}
	public void setToalprice(Integer toalprice) {
		this.toalprice = toalprice;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}