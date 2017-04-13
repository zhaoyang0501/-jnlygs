package xwgl.admin.web.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.project.entity.Hotel;
import xwgl.core.project.service.CategoryService;


/**
 * @author panchaoyang
 * qq:263608237
 */
@Controller
@RequestMapping("hotel")
public class HotelController extends AbstractBaseCURDController< Hotel,Long>  {
	
	@Autowired
	private CategoryService categoryService;

	@Override
	protected String getBasePath() {
		return "hotel";
	}
	
	@ModelAttribute
	public void preget(Model model) {
		model.addAttribute("categorys",this.categoryService.findByType("2"));
	}
}
