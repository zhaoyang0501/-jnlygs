package xwgl.admin.web.project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xwgl.common.dto.json.Response;
import xwgl.common.dto.json.SuccessResponse;
import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.project.entity.HotelOrder;

/***
 * @author panchaoyang
 *qq 263608237
 */
@Controller
@RequestMapping("hotelorder")
public class HotelOrderController  extends AbstractBaseCURDController<HotelOrder,Long> {

	@Override
	protected String getBasePath() {
		return "hotelorder";
	}

	@RequestMapping("approve")
	@ResponseBody
	public Response approve(Long id) {
		HotelOrder bean = this.simpleCurdService.find(id);
		bean.setState("审核通过");
		getSimpleCurdService().save(bean);
		return new SuccessResponse("审核成功！");
	}
	
}
