package xwgl.admin.web.sys;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xwgl.admin.web.sys.dto.RightTree;
import xwgl.common.dto.json.FailedResponse;
import xwgl.common.dto.json.ListResponse;
import xwgl.common.dto.json.Response;
import xwgl.common.dto.json.SuccessResponse;
import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.sys.entity.Right;
import xwgl.core.sys.entity.Role;
import xwgl.core.sys.service.RightService;
import xwgl.core.sys.service.RoleService;



@Controller
@RequestMapping("sys/role")
public class RoleController extends AbstractBaseCURDController<Role,Long>  {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RightService rightService;
	
	@Override
	public RoleService getSimpleCurdService() {
		return (RoleService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/role";
	}
	
	@RequestMapping("allright")
	@ResponseBody
	public List<RightTree> allRight(){
		List<Right> rights = rightService.findAll();
		List<RightTree> rightTrees = new ArrayList<RightTree>();
		for(Right r:rights){
			rightTrees.add(new RightTree(r));
		}
		return rightTrees;
	}
	
	@RequestMapping("roles")
	@ResponseBody
	public Response roles(){
		return new ListResponse<Role>(roleService.findAll());
	}
	
	
	@RequestMapping("updaterights")
	@ResponseBody
	public Response rights(Role role,String ids){
		if(StringUtils.isBlank(ids)){
			return new FailedResponse("没有选择权限！");
		}
		Role tobeupdaterole = new Role();
		if(role.getId()!=null){
			 tobeupdaterole = roleService.find(role.getId());
		}
		tobeupdaterole.setRemark(role.getRemark());
		tobeupdaterole.setName(role.getName());
		String[] idarrays = ids.split(",");
		List <Right> rights = new ArrayList<Right>();
		for(String id:idarrays){
			rights.add(new Right(Long.valueOf(id)));
		}
		tobeupdaterole.setRights(rights);
		roleService.save(tobeupdaterole);
		return new SuccessResponse();
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return this.getBasePath()+"/index";
	}

	
}
