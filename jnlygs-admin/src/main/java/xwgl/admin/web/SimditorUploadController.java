package xwgl.admin.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import xwgl.common.dto.json.FailedResponse;
import xwgl.common.dto.json.FileUploadDTO;
import xwgl.common.dto.json.ObjectResponse;
import xwgl.common.dto.json.Response;
import xwgl.common.web.AbstractFileUploadController;
@Controller
public class SimditorUploadController {
	 
	  private static final org.slf4j.Logger log = LoggerFactory.getLogger(AbstractFileUploadController.class);
	  /***
	  * 被运行的文件类型list 小写
	  * @return
	  */
	private final List<String>  allowTypes = new ArrayList<String>(){
			private static final long serialVersionUID = -7478928976664848255L;
			{
				 add("png");
				 add("jpg");
				 add("jpeg");
				 add("gif");
				 add("bmp");
			 }
		 };
		 
	 
	  
	  @RequestMapping(value="/simditorupload", method=RequestMethod.POST)
	  @ResponseBody
	  public  Map handleFileUpload( @RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception, IOException{
		  Map<String,Object> map= new HashMap<String,Object>(); 
		  map.put("success", true);
  		  map.put("msg", "上传成功！");
  		
		  if (!file.isEmpty()) {
	        	String filename =  file.getOriginalFilename();  
	        	String fileType = StringUtils.getFilenameExtension(filename);
	        	
	        	if(!allowTypes.contains(fileType.toLowerCase())){
	        		map.put("success", false);
	        		map.put("msg", "文件类型不被允许");
	        		map.put("file_path", "文件类型不被允许");
	        	}
	        	
	        	/**新的文件名*/
	        	String newfilename = System.currentTimeMillis()+"."+ StringUtils.getFilenameExtension(filename);
              
	        	/**文件存放的目录相对于webapp*/
	        	String fileDirectory ="upload/simditor/"+DateFormatUtils.format(new Date(), "yyyyMMdd")+"/";
	        	
	        	/**文件的绝对路径*/
	        	String realPath = request.getSession().getServletContext().getRealPath("/") +fileDirectory+ newfilename; 
              
	        	log.info("文件{}，被上传到{}",filename,realPath);
              //file.transferTo();  
              FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath));
              map.put("file_path", "/"+fileDirectory+ newfilename);
              return map;
	        }
	        else{
	        	map.put("success", false);
        		map.put("msg", "空文件");
        		map.put("file_path", "文件类型不被允许");
	        }
		  return  map;
	        	
	 }
}
