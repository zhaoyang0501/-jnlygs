package xwgl.admin.web.news.dto;

import xwgl.core.news.entity.News;

public class NewsSuggestDto {
	private String title;
	private String url;
	
	public NewsSuggestDto() {
		super();
	}
	
	public NewsSuggestDto(News news,String url) {
		this.setTitle(news.getTitle());
		this.setUrl(url+news.getId());
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
