package xwgl.core.sys.repository;
import java.util.List;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.sys.entity.Deptment;
public interface DeptmentRepository   extends SimpleCurdRepository<Deptment ,Long>{
	public List<Deptment> queryByParent(Deptment dept);
}
