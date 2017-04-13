package xwgl.core.sys.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import xwgl.common.exception.AlreadyExistedException;
import xwgl.common.service.SimpleCurdService;
import xwgl.common.util.PoiUtils;
import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.entity.Role;
import xwgl.core.sys.entity.User;
import xwgl.core.sys.repository.DeptmentRepository;
import xwgl.core.sys.repository.RoleRepository;
import xwgl.core.sys.repository.UserRepository;

@Service
public class UserService extends SimpleCurdService<User, Long> {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
    public final static String BIND_MAIL_SEND = "BIND_MAIL_SEND";

    public final static String BIND_MAIL_CODE = "BIND_MAIL_CODE";

    public final static String FORGET_SEND = "FORGET_SEND";

    public final static String FORGET_CODE = "FORGET_CODE";

    public final static String CODE_SUBJECT = "您在培训系统中绑定手机的验证码";
    public final static String FORGET_SUBJECT = "您在培训系统中找回密码的验证码";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private DeptmentRepository deptmentRepository;

    public User getUserByToken(String token) {
        User user = (User) redisTemplate.opsForValue().get(token);
        return user;
    }

    @Transactional
    public void BindUser(Long userid) {
        this.userRepository.bindUser(userid);
    }

    @Transactional
    public User registerUser(String username, String password, String chinesename) throws AlreadyExistedException {
        User user = userRepository.findByUsername(username);
        if (user != null)
            throw new AlreadyExistedException("用户名已经存在");
        else
            user = new User();
        user.setChinesename(chinesename);
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
        return userRepository.save(user);
    }

    public Page<User> findAll(final int pageNumber, final int pageSize, final String name, final Long deptid,
            final Boolean isFreeze,final Boolean party) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotBlank(name)) {
                    predicate.getExpressions().add(cb.like(root.get("chinesename").as(String.class), "%" + name + "%"));
                }
                if (deptid != null) {
                    predicate.getExpressions().add(cb.equal(root.get("deptment").get("id").as(Long.class), deptid));
                }
                if (isFreeze != null) {
                    predicate.getExpressions().add(cb.equal(root.get("isFreeze").as(Boolean.class), isFreeze));
                }
                if (party != null) {
                    predicate.getExpressions().add(cb.equal(root.get("party").as(Boolean.class), true));
                }
                return predicate;
            }
        };
        Page<User> result = (Page<User>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
    }
    
    public Page<User> findAll(final int pageNumber, final int pageSize, final String name,final String attr,final Long deptid,
            final Boolean isFreeze,final Boolean party) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotBlank(name)) {
                    predicate.getExpressions().add(cb.like(root.get(attr).as(String.class), "%" + name + "%"));
                }
                if (deptid != null && deptid != 1) {
                    predicate.getExpressions().add(cb.equal(root.get("deptment").get("id").as(Long.class), deptid));
                }
                if (isFreeze != null) {
                    predicate.getExpressions().add(cb.equal(root.get("isFreeze").as(Boolean.class), isFreeze));
                }
                if (party != null) {
                    predicate.getExpressions().add(cb.equal(root.get("party").as(Boolean.class), true));
                }
                return predicate;
            }
        };
        Page<User> result = (Page<User>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
    }

    public void forgetSendCode(User user) {
        String sendkey = user.getId() + "_" + FORGET_SEND;
        String codekey = user.getId() + "_" + FORGET_CODE;
        if (redisTemplate.opsForValue().get(sendkey) != null) {
            throw new RuntimeException("请一分钟后再试");
        } else {
            redisTemplate.opsForValue().set(sendkey, new Date());
            redisTemplate.expire(sendkey, 1, TimeUnit.MINUTES);

            int code = (int) (Math.random() * 9000) + 1000;

           
            redisTemplate.opsForValue().set(codekey, String.valueOf(code));
            redisTemplate.expire(codekey, 1, TimeUnit.HOURS);
        }
    }

    public void BindMailSendCode(User user, String mail) {
        String sendkey = user.getId() + "_" + BIND_MAIL_SEND;
        String codekey = user.getId() + "_" + BIND_MAIL_CODE;
        if (redisTemplate.opsForValue().get(sendkey) != null) {
            throw new RuntimeException("请一分钟后再试");
        } else {
            redisTemplate.opsForValue().set(sendkey, new Date());
            redisTemplate.expire(sendkey, 1, TimeUnit.MINUTES);

            int code = (int) (Math.random() * 9000) + 1000;


            redisTemplate.opsForValue().set(codekey, String.valueOf(code));
            redisTemplate.expire(codekey, 1, TimeUnit.HOURS);
        }
    }

    public Boolean isForgetCodeSucess(User user, String code) {
        String codekey = user.getId() + "_" + FORGET_CODE;
        String codeInRedis = (String) redisTemplate.opsForValue().get(codekey);

        if (codeInRedis != null && codeInRedis.equals(code)) {
            redisTemplate.delete(codekey);
            return true;
        } else
            return false;
    }

    public Boolean isCodeSucess(User user, String code) {
        String codekey = user.getId() + "_" + BIND_MAIL_CODE;
        String codeInRedis = (String) redisTemplate.opsForValue().get(codekey);

        if (codeInRedis != null && codeInRedis.equals(code)) {
            redisTemplate.delete(codekey);
            return true;
        } else
            return false;
    }

    /***
     * 登录不成功返回null
     * 
     * @return
     */
    public User login(String username, String password) {
    	log.info("用户尝试登陆{}password{}",username,password);
        Assert.notNull(username);
        if (username.length() == 8) {
            return this.userRepository.findByEmpidAndPassword(username, DigestUtils.md5Hex(password));
        } else if (username.contains("@139.com")) {
            username = username.split("@")[0];
        }
        return this.userRepository.findByUsernameAndPassword(username, DigestUtils.md5Hex(password));
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<Role> findAllRoles() {
        return (List<Role>) this.roleRepository.findAll();
    }

    public Role findRole(Long id) {
        return this.roleRepository.findOne(id);
    }

    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    public List<User> findUserByRole(Long roleid) {
        return this.userRepository.findUserByRole(roleid);
    }

    public	List<User>	converUsersFromExcel(String filepath) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException{
	  	List<User> list = new ArrayList<User>();
        List<Deptment> depts = ( List<Deptment>)deptmentRepository.findAll(); 
	  	Workbook workbook = WorkbookFactory.create(new FileInputStream(ResourceUtils.getFile(filepath))); 
        Sheet sheet = workbook.getSheetAt(0);
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            Row row = sheet.getRow(i);
            User user = new User();
            user.setUsername(PoiUtils.getStringCellValue(row.getCell(0)));
            user.setEmpid(PoiUtils.getStringCellValue(row.getCell(1)));
            user.setChinesename(PoiUtils.getStringCellValue(row.getCell(2)));
            String sex = PoiUtils.getStringCellValue(row.getCell(3));
            user.setDeptment(getDept(depts,PoiUtils.getStringCellValue(row.getCell(4))));
            user.setPassword(DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
            if("男".equals(sex))
            	user.setSex("男");
            else if("女".equals(sex))
            	user.setSex("女");
            list.add(user);
        }
        validateQuestions(list,null);
        return list; 
    }
    
    public	List<String>	converDeleteUsersFromExcel(String filepath) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException{
	  	List<String> list = new ArrayList<String>();
	  	Workbook workbook = WorkbookFactory.create(new FileInputStream(ResourceUtils.getFile(filepath))); 
        Sheet sheet = workbook.getSheetAt(0);
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            list.add(PoiUtils.getStringCellValue(sheet.getRow(i).getCell(0)));
        }
        return list; 
    }
    /***
     * 批量调整人员部门
     * @param filepath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     */
    public	List<User>	converDeptUsersFromExcel(String filepath) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException{
	  	List<User> list = new ArrayList<User>();
        List<Deptment> depts = ( List<Deptment>)deptmentRepository.findAll(); 
	  	Workbook workbook = WorkbookFactory.create(new FileInputStream(ResourceUtils.getFile(filepath))); 
        Sheet sheet = workbook.getSheetAt(0);
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            Row row = sheet.getRow(i);
            User user = new User();
            user.setUsername(PoiUtils.getStringCellValue(row.getCell(0)));
            user.setEmpid(PoiUtils.getStringCellValue(row.getCell(1)));
            user.setChinesename(PoiUtils.getStringCellValue(row.getCell(2)));
            String sex = PoiUtils.getStringCellValue(row.getCell(3));
            user.setDeptment(getDept(depts,PoiUtils.getStringCellValue(row.getCell(4))));
            user.setPassword(DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
            if("男".equals(sex))
            	user.setSex("男");
            else if("女".equals(sex))
            	user.setSex("女");
            list.add(user);
        }
        validateQuestions(list,null);
        return list; 
    }
    private	Deptment	getDept(List<Deptment> lists,String deptname){
		for(Deptment deptment:lists){
			if(deptment.getName().equals(deptname))
				return deptment;
		}
		return null;
    }

    
    private void validateQuestions(List<User> users,List<User> usersInsys){
		for(User user:users){
			if(StringUtils.isEmpty(user.getChinesename())){
				user.setRemark((user.getRemark()==null?"":user.getRemark())+" 姓名不能为空");
				return;
			}
			
			if(StringUtils.isEmpty(user.getEmpid())){
				user.setRemark((user.getRemark()==null?"":user.getRemark())+" 工号不能为空");
				return;
			}
			
			if(StringUtils.isEmpty(user.getUsername())){
				user.setRemark((user.getRemark()==null?"":user.getRemark())+" 用户名（手机号码）不能为空");
				return;
			}
			/**本文件中检查用户名、工号是否有重复*/
			for(User checkuser:users){
				if(checkuser!=user&&checkuser.getUsername().equals(user.getUsername())){
					user.setRemark((user.getRemark()==null?"":user.getRemark())+"  该用户名在excel文件中重复");
					return;
				}
				if(checkuser!=user&&checkuser.getEmpid().equals(user.getEmpid())){
					user.setRemark((user.getRemark()==null?"":user.getRemark())+"  该工号在excel文件中重复");
					return;
				}
			}
			
			/**与数据库中用户名、工号是否有重复*/
			/*for(User userinsys:usersInsys){
				if(userinsys.getUsername().equals(user.getUsername())){
					user.setRemark((user.getRemark()==null?"":user.getRemark())+"  该用户名在系统中已经存在");
					return;
				}
				if(userinsys.getEmpid().equals(user.getEmpid())){
					user.setRemark((user.getRemark()==null?"":user.getRemark())+"  该工号在系统中已经存在");
					return;
				}
			}*/
			
		}
	}
	
    public void updateUser(Long userid, String img) {
        this.userRepository.updateUser(userid, img);
    }
    
    public void resetPassword(Long userid) {
        this.userRepository.updatePassword(userid,DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
    }
    
    public void freeze(Long userid) {
        this.userRepository.updateFreeze(userid, true);
    }
    
    public void unFreeze(Long userid) {
        this.userRepository.updateFreeze(userid, false);
    }
    public List<User> findByDeptment(Deptment deptment) {
    	return  this.userRepository.findByDeptment(deptment);
    }

	public void deleteByUsername(String username) {
		  this.userRepository.deleteByUserName(username);
	}
}
