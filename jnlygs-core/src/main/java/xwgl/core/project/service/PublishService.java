package xwgl.core.project.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xwgl.common.service.SimpleCurdService;
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.entity.Publish;
import xwgl.core.project.repository.PublishRepository;

@Service
public class PublishService extends SimpleCurdService<Publish, Long> {
	
	@Autowired
	private  PublishRepository publishUserRepository;
	
	public List<Publish> findByUser(FrontUser user){
		return this.publishUserRepository.findByUser(user);
	}
	
	
}
