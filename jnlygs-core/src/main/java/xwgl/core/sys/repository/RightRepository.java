package xwgl.core.sys.repository;
import java.util.List;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.sys.entity.Right;
public interface RightRepository   extends SimpleCurdRepository<Right ,Long>{
	public List<Right> queryByParent(Right dept);
}
