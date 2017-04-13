package xwgl.core.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xwgl.common.entity.BaseEntity;

@Entity
@Table(name = "t_sys_role")
public class Role extends BaseEntity<Long>  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2585772904739469109L;

	private String code;
	
	private String name;
	
	private String remark;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_sys_role_right", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
	            @JoinColumn(name = "right_id", referencedColumnName = "id") })
	@JsonIgnoreProperties({ "updateTime", "createTime" })
	@OrderBy("name ASC")
	private List<Right> rights = new ArrayList<Right>();

	 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	
	
	
}
