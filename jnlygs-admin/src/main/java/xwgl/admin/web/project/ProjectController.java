package xwgl.admin.web.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.project.entity.Project;
import xwgl.core.project.service.CategoryService;
import xwgl.core.project.service.ProjectService;


/***
 * @author panchaoyang
 * qq:263608237
 */
@Controller
@RequestMapping("project")
public class ProjectController extends AbstractBaseCURDController< Project,Long>  {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	protected String getBasePath() {
		return "project";
	}
	
	@ModelAttribute
	public void preget(Model model) {
		model.addAttribute("categorys",this.categoryService.findByType("1"));
	}
}
