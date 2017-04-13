package xwgl.admin.web.project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.project.entity.FrontUser;


/***
 * @author panchaoyang
 * qq:263608237
 */
@Controller
@RequestMapping("frontuser")
public class FrontUserController extends AbstractBaseCURDController< FrontUser,Long>  {
	

	@Override
	protected String getBasePath() {
		return "frontuser";
	}
}
