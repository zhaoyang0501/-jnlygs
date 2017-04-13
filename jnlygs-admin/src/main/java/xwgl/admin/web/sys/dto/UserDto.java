package xwgl.admin.web.sys.dto;

import java.util.Set;

import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.entity.Role;
import xwgl.core.sys.entity.User;

public class UserDto {
	
	private Long id;
	
	private String username;
	
	private String email;
	
	private String remark;
	
	private String tel;
	
	private String chinesename ="";
	
	private String sex;
	
	private Boolean isFreeze = false;
	
	private Boolean isBind =false;
	
	private String headimg;
	
	private String empid;
	
	private Deptment deptment;
	
	private Integer score = 0;
	
	private Boolean gag;
	
	private Boolean party;
	
	private Set<Role> roles;
	
	
	public Boolean getGag() {
		return gag;
	}
	public void setGag(Boolean gag) {
		this.gag = gag;
	}
	public UserDto() {
		super();
	}
	public UserDto(User user) {
		this.id=user.getId();
		this.chinesename=user.getChinesename();
		this.deptment=user.getDeptment();
		this.email=user.getEmail();
		this.empid=user.getEmpid();
		this.headimg=user.getHeadimg();
		this.isBind=user.getIsBind();
		this.isFreeze=user.getIsFreeze();
		this.remark=user.getRemark();
		this.roles=user.getRoles();
		this.score=user.getScore();
		this.sex=user.getSex();
		this.tel=user.getTel();
		this.gag=user.getGag();
		this.username=user.getUsername();
		this.party=user.getParty();
		
	}

	public Boolean getParty() {
		return party;
	}
	public void setParty(Boolean party) {
		this.party = party;
	}
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
