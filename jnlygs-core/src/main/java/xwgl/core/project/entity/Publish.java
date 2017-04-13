package xwgl.core.project.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xwgl.common.entity.BaseEntity;

@Entity
@Table(name = "t_Publish")
public class Publish extends BaseEntity<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6907033069949712353L;

	private String title;
	
	private String body;
	
	@OneToOne
	private FrontUser user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	
	
	
}
