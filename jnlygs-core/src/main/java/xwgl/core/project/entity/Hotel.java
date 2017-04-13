package xwgl.core.project.entity;
import java.io.Serializable;

/***
 * 分类
 *
 */
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xwgl.common.entity.BaseEntity;
/***
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_hotel")
public class Hotel extends BaseEntity<Long> implements Serializable{
	
	private String type;

	private String name;
	
	private String remark;
	
	private String site;
	
	private String tel;
	
	private String addr;
	
	private Integer price;
	
	@ManyToOne
	private Category category;
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}