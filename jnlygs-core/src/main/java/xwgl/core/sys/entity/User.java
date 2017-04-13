package xwgl.core.sys.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import xwgl.common.entity.BaseEntity;

@Entity
@Table(name = "t_sys_user",uniqueConstraints={@UniqueConstraint(columnNames = {"username", "empid"})})
public class User extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = 2927194419168198403L;
	
	public static final String DEFAULT_PASSWORD="123456";
	
	@ApiModelProperty(value="用户名（139邮箱）")
	private String username;
	
	@ApiModelProperty(hidden=true)
	@JsonIgnore
	private String password;
	
	private String email;
	
	private String salt;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="电话")
	private String tel;
	
	@ApiModelProperty(value="姓名/昵称")
	private String chinesename ="";
	
	@ApiModelProperty(value="性别")
	private String sex;
	
	@ApiModelProperty(value="是否冻结")
	private Boolean isFreeze = false;
	
	@ApiModelProperty(value="是否绑定邮箱")
	private Boolean isBind =false;
	
	@ApiModelProperty(value="头像")
	private String headimg;
	
	@ApiModelProperty(value="工号")
	private String empid;
	
	@ApiModelProperty(value="部门")
	@OneToOne
	private Deptment deptment;
	
	@ApiModelProperty(value="积分")
	private Integer score = 0;
	
	@ApiModelProperty(value="是否禁言")
	private Boolean gag = false;
	
	
	private Boolean party = false;
	
	@ApiModelProperty(hidden=true)
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Role> roles;
	
	
	
	public User() {
		super();
	}
	public User(Long id) {
		super();
		super.setId(id);
	}
	public Set<Role> getRoles() {
		return roles;
	}
	
	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Deptment getDeptment() {
		return deptment;
	}

	public void setDeptment(Deptment deptment) {
		this.deptment = deptment;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getChinesename() {
		return chinesename;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getGag() {
		return gag;
	}

	public void setGag(Boolean gag) {
		this.gag = gag;
	}
	
	public Boolean getParty() {
		return party;
	}
	
	public void setParty(Boolean party) {
		this.party = party;
	}
	
	
}
