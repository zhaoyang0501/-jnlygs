package xwgl.core.project.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import xwgl.core.project.entity.Category;
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>,JpaSpecificationExecutor<Category>{
	public	List<Category>	findByType(String	type);
}

