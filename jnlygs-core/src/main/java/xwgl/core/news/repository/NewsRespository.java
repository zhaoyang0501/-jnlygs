package xwgl.core.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.news.entity.News;
import xwgl.core.news.entity.NewsCategory;

public interface NewsRespository  extends SimpleCurdRepository<News ,Long>{
	
	@Query("select count(1) from  News")
	public long count();
	
	public List<News> findByCategoryOrderByCreateDateDesc(NewsCategory category);
}
