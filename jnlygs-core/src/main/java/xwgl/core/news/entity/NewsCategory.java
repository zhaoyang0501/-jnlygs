package xwgl.core.news.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import xwgl.common.entity.BaseEntity;

@Entity
@Table(name = "t_news_category")
@JsonIgnoreProperties(value={"createDate","updateDate"})
public class NewsCategory extends BaseEntity<Long>{
	
	@ApiModelProperty(value="分类名称")
	private String  name;
	
	@ApiModelProperty(value="备注")
	private String remark;
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
	
	
}
