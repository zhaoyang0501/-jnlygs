package xwgl.admin.web.center;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xwgl.common.dto.json.FailedResponse;
import xwgl.common.dto.json.SuccessResponse;
import xwgl.core.sys.entity.User;
import xwgl.core.sys.service.UserService;
@Controller
@RequestMapping("center")
public class CenterController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="password",method=RequestMethod.POST)
	public String password(Model model,String pw1,String pw2,String pw) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(!DigestUtils.md5Hex(pw).equals(user.getPassword()))
			model.addAttribute("response",new FailedResponse("原始密码不正确！"));
		else{
			user.setPassword(DigestUtils.md5Hex(pw1));
			userService.save(user);
			model.addAttribute("response",new SuccessResponse("密码修改成功！"));
		}
			
		return "center/password";
	}
	@RequestMapping(value="password",method=RequestMethod.GET)
	public String password(Model model) {
		return "center/password";
	}
}
