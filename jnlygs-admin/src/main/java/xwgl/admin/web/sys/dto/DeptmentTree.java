package xwgl.admin.web.sys.dto;

import xwgl.core.sys.entity.Deptment;

public class DeptmentTree {
	
	public String id;
	
	public String parent;
	
	public String text;
	
	public DeptmentTree(Deptment dept){
		this.id=dept.getId().toString();
		this.parent=dept.getParent()==null?"#":dept.getParent().getId().toString();
		this.text=dept.getName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
