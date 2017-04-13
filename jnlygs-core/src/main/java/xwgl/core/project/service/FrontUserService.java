package xwgl.core.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xwgl.common.service.SimpleCurdService;
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.repository.FrontUserRepository;

@Service
public class FrontUserService extends SimpleCurdService<FrontUser, Long> {
	
	@Autowired
	private  FrontUserRepository frontUserRepository;
	
	public FrontUser login(String username,String password){
		return  this.frontUserRepository.findByUsernameAndPassword(username, password);
	}
	
	public FrontUser findByUserName(String username){
		return frontUserRepository.findByUsername(username);
	}
}
