package xwgl.core.news.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xwgl.common.entity.BaseEntity;
import xwgl.core.sys.entity.User;

@Entity
@Table(name = "t_news_news")
public class News extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = -5927031699239008428L;

	private String title;
	
	private String body;
	
	private String summary;
	
	private String img;
	
	@OneToOne
	private NewsCategory category;
	
	@OneToOne
	private User user;
	
	

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NewsCategory getCategory() {
		return category;
	}
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setCategory(NewsCategory category) {
		this.category = category;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
