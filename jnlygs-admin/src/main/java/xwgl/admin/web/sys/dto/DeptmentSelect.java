package xwgl.admin.web.sys.dto;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import xwgl.core.sys.entity.Deptment;

public class DeptmentSelect {
	
	public String id;
	
	public String parent;
	
	public String text;
	
	public DeptmentSelect(Deptment dept){
		this.id=dept.getId().toString();
		int lev = this.getLevel(dept);
		this.text=StringUtils.leftPad(dept.getName(), dept.getName().length()+(lev-1)*4, "-");
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
	
	private  Integer getLevel(Deptment dept){
		if(dept.getParent()==null)
			return 1;
		else return 1+getLevel(dept.getParent());
	}
	
	public static void convertToSelectDto(Deptment deptment,List<DeptmentSelect> list){
		list.add( new DeptmentSelect(deptment));
		if(CollectionUtils.isEmpty(deptment.getChildrens())){
			return ;
		}else{
			for(Deptment dep:deptment.getChildrens()){
				convertToSelectDto(dep,list);
			}
		}
	}
}
