package xwgl.admin.web.project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xwgl.common.dto.json.Response;
import xwgl.common.dto.json.SuccessResponse;
import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.project.entity.Order;

/***
 * @author panchaoyang
 *qq 263608237
 */
@Controller
@RequestMapping("order")
public class OrderController  extends AbstractBaseCURDController<Order,Long> {

	@Override
	protected String getBasePath() {
		return "order";
	}

	@RequestMapping("approve")
	@ResponseBody
	public Response approve(Long id) {
		Order bean = this.simpleCurdService.find(id);
		bean.setState("审核通过");
		getSimpleCurdService().save(bean);
		return new SuccessResponse("审核成功！");
	}
	
}
