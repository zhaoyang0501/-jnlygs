package xwgl.core.sys.service;

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
import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.repository.DeptmentRepository;
@Service
public class DeptmentService  extends SimpleCurdService<Deptment, Long> {
	@Autowired
	public DeptmentRepository deptmentRepository;
	
	public List<Deptment> queryRootList(){
		return this.deptmentRepository.queryByParent(null);
	}
	
	public Page<Deptment> findAll(final int pageNumber, final int pageSize,final String name,final Long pid){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<Deptment> spec = new Specification<Deptment>() {
             public Predicate toPredicate(Root<Deptment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             
             if (StringUtils.isNotBlank(name)) {
                  predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
             }
             
             if (pid!=null) {
                 predicate.getExpressions().add(cb.equal(root.get("parent").get("id").as(Long.class),pid));
             }
             return predicate;
             }
        };
        Page<Deptment> result = (Page<Deptment>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
  } 
}
