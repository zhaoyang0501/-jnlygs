package xwgl.core.news.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import xwgl.common.service.SimpleCurdService;
import xwgl.core.news.entity.News;
import xwgl.core.news.entity.NewsCategory;
import xwgl.core.news.repository.NewsCategoryRespository;
import xwgl.core.news.repository.NewsRespository;
@Service
public class NewsService extends SimpleCurdService<News, Long> {
	
	public static final int PAGE_SIZE=10;
	@Autowired
	private NewsRespository newsRepository;
	
	@Autowired
	private NewsCategoryRespository newsCategoryRespository;
	

	public List<News> findByCategory(NewsCategory categoy){
		return this.newsRepository.findByCategoryOrderByCreateDateDesc(categoy);
	}
	
	public List<NewsCategory> findAllCategory(){
		return (List<NewsCategory>) this.newsCategoryRespository.findAll();
	}
	
	public Long count(){
		return this.newsRepository.count();
	}
	
	/***
	 * 
	 * @param pageNumber start form 1
	 * @param categoryid
	 * @return
	 */
	public Page<News> findAll(final int pageNumber,final Long  categoryid){
		
		if(pageNumber<1)
			throw new IllegalArgumentException("页码不能小于1");
		
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Direction.DESC, "id"));
        Specification<News> spec = new Specification<News>() {
             public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (categoryid!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("category").get("id").as(Long.class), categoryid));
             }
             return predicate;
             }
        };
        return  simpleCurdRepository.findAll(spec, pageRequest);
   }  
	
	public List<News> findByTitle(final String title) {
		 PageRequest pageRequest = new PageRequest(0, 30, new Sort(Direction.DESC, "id"));
		 Specification<News> spec = new Specification<News>() {
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Predicate predicate = cb.conjunction();
            
            if (StringUtils.isNotBlank(title)) {
                 predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+title+"%"));
            }
            
            return predicate;
            }
       };
      return simpleCurdRepository.findAll(spec, pageRequest).getContent();
	}
	
}
