package xwgl.admin.web.sys.dto;

import xwgl.core.sys.entity.Right;

public class RightTree {
	
	public String id;
	
	public String parent;
	
	public String text;
	
	public RightTree(Right right){
		this.id=right.getId().toString();
		this.parent=right.getParent()==null?"#":right.getParent().getId().toString();
		this.text=right.getName();
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
