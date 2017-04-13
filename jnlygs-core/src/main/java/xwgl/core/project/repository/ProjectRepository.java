package xwgl.core.project.repository;
import java.util.List;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.project.entity.Project;
import xwgl.core.sys.entity.User;

public interface ProjectRepository extends SimpleCurdRepository<Project ,Long>{
	public List<Project> findByUser(User user);
}

