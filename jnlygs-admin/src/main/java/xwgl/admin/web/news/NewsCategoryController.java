package xwgl.admin.web.news;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.news.entity.NewsCategory;

@Controller
@RequestMapping("news/category")
public class NewsCategoryController extends AbstractBaseCURDController<NewsCategory,Long>  {
	
	@Override
	public String getBasePath() {
		return "news/category";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
}
