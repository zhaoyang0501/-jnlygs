package xwgl.core.sys.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import xwgl.common.entity.BaseEntity;
@Entity
@Table(name = "t_sys_right")
public class Right  extends BaseEntity<Long>  {
	
	private String code;
	
	private String name;
	
	private String remark;
	
	public Right() {
		super();
	}

	public Right(Long id) {
		super.setId(id);
	}

	@ManyToOne
    @JoinColumn(name = "parent_id")
	private Right parent;
	
	@OneToMany(mappedBy = "parent")
	@JsonIgnore
	private List<Right> childrens;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Right getParent() {
		return parent;
	}

	public void setParent(Right parent) {
		this.parent = parent;
	}

	public List<Right> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Right> childrens) {
		this.childrens = childrens;
	}
	
}
