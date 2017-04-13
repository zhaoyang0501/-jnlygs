package xwgl.admin.web.sys;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xwgl.admin.web.sys.dto.DeptmentSelect;
import xwgl.common.dto.json.DataTableResponse;
import xwgl.common.dto.json.Response;
import xwgl.common.web.AbstractBaseCURDController;
import xwgl.core.sys.entity.Deptment;
import xwgl.core.sys.service.DeptmentService;

@Controller
@RequestMapping("sys/deptment")
public class DeptmentController extends AbstractBaseCURDController<Deptment,Long>  {
	
	@Override
	public DeptmentService getSimpleCurdService() {
		return (DeptmentService)super.getSimpleCurdService();
	}	
	
	@Override
	public String getBasePath() {
		return "sys/deptment";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		List<Deptment> deptments = this.getSimpleCurdService().queryRootList();
		List<DeptmentSelect> deptmentselect = new ArrayList<DeptmentSelect>();
		for(Deptment dept:deptments){
			DeptmentSelect.convertToSelectDto(dept,deptmentselect);
		}
		model.addAttribute("deptmentselects",deptmentselect);
		return this.getBasePath()+"/index";
	}
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, String name,Long deptid) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<Deptment> m = this.getSimpleCurdService().findAll(pageNumber, pageSize, name,deptid);
		return new DataTableResponse<Deptment>( m.getContent(),(int) m.getTotalElements() );
	}
}
