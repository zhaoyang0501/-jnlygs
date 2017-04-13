package xwgl.admin.web.sys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xwgl.common.web.AbstractFileUploadController;
@Controller
@RequestMapping("sys/usersUpload")
public class UserFileUploadController extends AbstractFileUploadController{
	
	
	@Value("${cmcc.path.uploadpath}")
	private String uploadpath;
	
	private final List<String>  allowTypes = new ArrayList<String>(){
			private static final long serialVersionUID = -7478928976664848255L;
			{
				 add("xls");
				 add("xlsx");
			 }
		 };
		 
	@Override
	public List<String> getAllowTypes() {
		return allowTypes;
	}


	@Override
	public String getFileUploadPath() {
		return uploadpath+File.separator+"sys"+File.separator+"excel";
	}


	@Override
	public String getFileRelativePath() {
		return File.separator+"sys"+File.separator+"excel";
	}

}
