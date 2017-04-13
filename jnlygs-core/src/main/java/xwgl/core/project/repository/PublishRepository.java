package xwgl.core.project.repository;

import java.util.List;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.entity.Publish;

public interface PublishRepository   extends SimpleCurdRepository<Publish ,Long>{
	public List<Publish> findByUser(FrontUser user);
}
