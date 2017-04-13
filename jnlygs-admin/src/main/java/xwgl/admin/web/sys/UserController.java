package xwgl.admin.web.sys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xwgl.admin.web.sys.dto.DeptmentSelect;
import xwgl.admin.web.sys.dto.DeptmentTree;
import xwgl.admin.web.sys.dto.UserDto;
import xwgl.common.dto.json.DataTableResponse;
import xwgl.common.dto.json.FailedResponse;
import xwgl.common.dto.json.ObjectResponse;
import xwgl.common.dto.json.Response;
import xwgl.common.dto.json.SuccessResponse;
import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.entity.Role;
import xwgl.core.sys.entity.User;
import xwgl.core.sys.service.DeptmentService;
import xwgl.core.sys.service.RoleService;
import xwgl.core.sys.service.UserService;



@Controller
@RequestMapping("sys/user")
public class UserController extends AbstractBaseCURDController<User,Long>  {

	@Value("${cmcc.path.uploadpath}")
	private String uploadpath;
	
	@Autowired
	private DeptmentService deptmentService;
	
	@Autowired
	private RoleService roleService;
	@Override
	public UserService getSimpleCurdService() {
		return (UserService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/user";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("roles", this.getSimpleCurdService().findAllRoles());
		List<Deptment> deptments = this.deptmentService.queryRootList();
		List<DeptmentSelect> deptmentselect = new ArrayList<DeptmentSelect>();
		for(Deptment dept:deptments){
			DeptmentSelect.convertToSelectDto(dept,deptmentselect);
		}
		model.addAttribute("deptmentselects",deptmentselect);
		return this.getBasePath()+"/index";
	}

	@RequestMapping("freeze")
	@ResponseBody
	public Response freeze(Long id) {
		getSimpleCurdService().freeze(id);
		return new SuccessResponse("冻结成功！");
	}
	
	
	@RequestMapping("freezeAll")
	@ResponseBody
	public Response freezeAll(String ids) {
		String idsarry[] = ids.split(",");
		for(int i=0;i<idsarry.length;i++){
			getSimpleCurdService().freeze(Long.valueOf(idsarry[i]));
		}
		return new SuccessResponse("冻结成功！");
	}
	
	@RequestMapping("unfreeze")
	@ResponseBody
	public Response unfreeze(Long id) {
		getSimpleCurdService().unFreeze(id);
		return new SuccessResponse("解冻成功！");
	}
	
	@RequestMapping("resetpw")
	@ResponseBody
	public Response resetpw(Long id) {
		getSimpleCurdService().resetPassword(id);
		return new SuccessResponse("解冻成功！");
	}
	
	@RequestMapping("saveuser")
	@ResponseBody
	public Response save(User user,@RequestParam(required=false) String role) {
		if(StringUtils.isNotBlank(role)){
			String[] ids = role.split(",");
			Set<Role> roles = new HashSet<Role>();
			for(int i=0;i<ids.length;i++){
				roles.add(this.getSimpleCurdService().findRole(Long.valueOf(ids[i])));
			}
			user.setRoles(roles);
		}else{
			user.setRoles(null);
		}
		try {
			simpleCurdService.save(user);
		} catch (Exception e) {
			return new FailedResponse("保存失败：已经存在该用户！");
		}
		return new SuccessResponse("保存成功");
	}
	
	@RequestMapping("alldeptments")
	@ResponseBody
	public List<DeptmentTree> allDeptments(){
		List<Deptment> deptements = deptmentService.findAll();
		List<DeptmentTree> deptmentTrees = new ArrayList<DeptmentTree>();
		for(Deptment dept:deptements){
			deptmentTrees.add(new DeptmentTree(dept));
		}
		return deptmentTrees;
	}
	
	@RequestMapping("getuser")
	@ResponseBody
	public Response get(Long id) {
		return new ObjectResponse<UserDto>(new UserDto(simpleCurdService.find(id)));
	}
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, String value,Long deptid,String attr,
			@RequestParam( defaultValue="false")  Boolean isFreeze, Boolean party) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<User> m = this.getSimpleCurdService().findAll(pageNumber, pageSize, value,attr,deptid,isFreeze,party);
		return new DataTableResponse<UserDto>( convertToUserDto(m.getContent()),(int) m.getTotalElements() );
	}
	
	
	
	
	
	@RequestMapping("deleteUsersFromExcel")
	@ResponseBody
	public Response deleteUsersFromExcel(String filepath){
		List<String>  list = new ArrayList<String>();
		try {
			list = this.getSimpleCurdService().converDeleteUsersFromExcel(uploadpath+filepath);
		} catch (Exception e) {
			e.printStackTrace();
			return new FailedResponse("文件解析失败"+e.getMessage());
		} 
		for(String username:list){
			this.getSimpleCurdService().deleteByUsername(username);
		}
		return new SuccessResponse("批量删除成功，总共删除"+list.size()+"个员工！");
	}
	
	private List<UserDto> convertToUserDto(List<User> users){
		List<UserDto> dtos = new ArrayList<UserDto>();
		if(!CollectionUtils.isEmpty(users)){
			for(User user:users){
				dtos.add(new UserDto(user));
			}
		}
		return dtos;
	} 
	
	@ModelAttribute
	public User preget(@RequestParam(required=false) Long id,@RequestParam(required=false) String role) {
		User user = new User();
		if (id!=null){
			//user = this.getSimpleCurdService().find(id);
			user.setDeptment(null);
		}else{
			user.setPassword( DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
		}
		return user;
	}
}
