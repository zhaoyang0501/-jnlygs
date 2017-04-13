package xwgl.core.project.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.project.entity.Order;
import xwgl.core.project.entity.Project;

public interface OrderRepository extends SimpleCurdRepository<Order ,Long>{
}

