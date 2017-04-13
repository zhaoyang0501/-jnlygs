
package xwgl.core.project.service;

import java.util.Date;
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
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.entity.Order;
import xwgl.core.project.entity.Project;
import xwgl.core.project.repository.OrderRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class OrderService extends SimpleCurdService< Order, Long>{
     @Autowired
     private OrderRepository orderRepository;

 	public List<Order> findTop3() {
 		return orderRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Order> findAll() {
         return (List<Order>) orderRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public List<Order> findByUser(final FrontUser user) {
    	 Specification<Order> spec = new Specification<Order>() {
             public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (user != null) {
                  predicate.getExpressions().add(cb.equal(root.get("user").as(FrontUser.class), user));
             }
             return predicate;
             }
        };
         return (List<Order>) orderRepository.findAll(spec);
     }
     
     public Page<Order> findAll(final int pageNumber, final int pageSize,final String name,final Date s,final Date e,final String state){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Order> spec = new Specification<Order>() {
              public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("user").get("name").as(String.class), "%"+name+"%"));
              }
              if (s!=null) {
                  predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("orderDate").as(Date.class), s));
              }
              if (e!=null) {
                  predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("orderDate").as(Date.class), s));
               }
              if (state!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("state").as(String.class), state));
               }
              return predicate;
              }
         };
         Page<Order> result = (Page<Order>) orderRepository.findAll(spec, pageRequest);
         return result;
     	}
     public Page<Order> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Order> spec = new Specification<Order>() {
              public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("user").get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Order> result = (Page<Order>) orderRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Order> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Order> spec = new Specification<Order>() {
              public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Order> result = (Page<Order>) orderRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			orderRepository.delete(id);
		}
		public Order find(Long id){
			  return orderRepository.findOne(id);
		}
		
}