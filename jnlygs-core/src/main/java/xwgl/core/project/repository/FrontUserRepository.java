package xwgl.core.project.repository;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.project.entity.FrontUser;

public interface FrontUserRepository   extends SimpleCurdRepository<FrontUser ,Long>{
	FrontUser findByUsernameAndPassword(String username, String password);
	FrontUser findByUsername(String username);
}
