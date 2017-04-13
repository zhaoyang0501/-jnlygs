package xwgl.admin.web.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xwgl.common.dto.json.SuccessResponse;
import xwgl.core.news.entity.News;
import xwgl.core.news.service.NewsService;

@Controller
@RequestMapping("news/create")
public class NewsCreateController   {
	
	@Autowired
	public NewsService newsService;
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys",newsService.findAllCategory());
		return "news/create/index";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create(Model model,News news,final RedirectAttributes redirectAttributes) {
		this.newsService.save(news);
		redirectAttributes.addFlashAttribute("response", new SuccessResponse("操作成功！"));
		 return "redirect:/news/create/index";  
	}
}
