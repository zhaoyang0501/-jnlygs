
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

import xwgl.common.service.SimpleCurdService;
import xwgl.core.project.entity.Category;
import xwgl.core.project.entity.Hotel;
import xwgl.core.project.entity.Project;
import xwgl.core.project.repository.HotelRepository;
import xwgl.core.project.repository.ProjectRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class HotelService extends SimpleCurdService< Hotel, Long>{
     @Autowired
     private HotelRepository hotelRepository;

 	
     public List<Hotel> findAll() {
         return (List<Hotel>) hotelRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Hotel> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Hotel> spec = new Specification<Hotel>() {
              public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Hotel> result = (Page<Hotel>) hotelRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Hotel> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Hotel> spec = new Specification<Hotel>() {
              public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (key != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+key+"%"));
              }
              if (category != null) {
                  predicate.getExpressions().add(cb.equal(root.get("category").as(Category.class), category));
              }
              return predicate;
              }
         };
         Page<Hotel> result = (Page<Hotel>) hotelRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Hotel> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Hotel> spec = new Specification<Hotel>() {
              public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Hotel> result = (Page<Hotel>) hotelRepository.findAll(spec, pageRequest);
         return result;
     	}
		
		public List<Hotel> findBycategory(final Long id){
	         Specification<Hotel> spec = new Specification<Hotel>() {
	              public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	              Predicate predicate = cb.conjunction();
	              if (id != null) {
	                   predicate.getExpressions().add(cb.equal(root.get("category").get("id").as(Long.class), id));
	              }
	              return predicate;
	              }
	         };
	         return  hotelRepository.findAll(spec);
	    }
}