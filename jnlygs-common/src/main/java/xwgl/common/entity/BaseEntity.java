package xwgl.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;


@MappedSuperclass
public class BaseEntity<ID extends Serializable>  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private  ID id;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") 
    @ApiModelProperty(hidden=true)
    private Date createDate = new Date();
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") 
    @ApiModelProperty(hidden=true)
    private Date updateDate;
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
		
}
