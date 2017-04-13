
package xwgl.core.project.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import xwgl.core.project.entity.Category;
import xwgl.core.project.repository.CategoryRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class CategoryService {
     @Autowired
     private CategoryRepository categoryRepository;

 	public List<Category> findTop3() {
 		return categoryRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
 	
 	public List<Category> findByType(String type) {
 		return categoryRepository.findByType(type);
 	}
 	
     public List<Category> findAll() {
         return (List<Category>) categoryRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Category> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Category> spec = new Specification<Category>() {
              public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Category> result = (Page<Category>) categoryRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Category> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Category> spec = new Specification<Category>() {
              public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Category> result = (Page<Category>) categoryRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			categoryRepository.delete(id);
		}
		public Category find(Long id){
			  return categoryRepository.findOne(id);
		}
		public void save(Category category){
			categoryRepository.save(category);
		}
}