package xwgl.core.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.entity.User;


public interface UserRepository   extends SimpleCurdRepository<User ,Long>{
	
	public User findByUsername(String username);
	
	public List<User> findByDeptment(Deptment depement);
	
	public User findByUsernameAndPassword(String username,String password);
	
	public User findByEmpidAndPassword(String empid,String password);
	
	@Query("select b from User b  inner join fetch b.roles as a where a.id=?1 ")
	public List<User> findUserByRole(Long rolecode);
	
	
	@Transactional @Modifying @Query("update User u set u.isBind=true where u.id = ?1")
	public void bindUser(Long userid);
	
	@Transactional @Modifying @Query("update User u set u.headimg=?2 where u.id = ?1")
    public void updateUser(Long userid,String headimg);


	@Transactional @Modifying @Query("update User u set u.password=?2 where u.id = ?1")
    public void updatePassword(Long userid,String password);
	
	@Transactional @Modifying @Query("update User u set u.isFreeze=?2 where u.id = ?1")
    public void updateFreeze(Long userid,Boolean freeze);
	
	@Transactional @Modifying @Query("delete from  User u where u.username=?1")
    public void deleteByUserName(String username);
	
	@Query("select b from User b")
	public List<User> queryAllUser();
}
