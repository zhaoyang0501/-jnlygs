package xwgl.common.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
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

/***
 * 文件上传控制器
 * @author panchaoyang
 *
 */

public abstract class AbstractFileUploadController {
	 
	  
	  private static final org.slf4j.Logger log = LoggerFactory.getLogger(AbstractFileUploadController.class);
	  /***
	  * 被运行的文件类型list 小写
	  * @return
	  */
	  public abstract List<String> getAllowTypes();
	
	  /***
	   * 文件上传的绝对路基 exmple： /usr/webapp/cmccadmin/
	   * @return
	   */
	  public abstract String getFileUploadPath();

	  /***
	   * 文件相对路径：存数据库用
	   * @return
	   */
	  public abstract String getFileRelativePath();
	   
	  @RequestMapping(value="/upload", method=RequestMethod.POST)
	  @ResponseBody
	  public  Response handleFileUpload( @RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception, IOException{
	        if (!file.isEmpty()) {
	        	String filename =  file.getOriginalFilename();  
	        	String fileType = StringUtils.getFilenameExtension(filename);
	        	
	        	if(!getAllowTypes().contains(fileType.toLowerCase()))
	        		return new FailedResponse("不被允许的文件类型");
	        	
	        	/**新的文件名*/
	        	String newfilename = System.currentTimeMillis()+"."+ StringUtils.getFilenameExtension(filename);
                
	        	/**文件存放的文件夹 exmple: 20150401/XXX.file*/
	        	String fileDirectory =File.separator+ DateFormatUtils.format(new Date(), "yyyyMMdd")+File.separator;
	        	
	        	/**文件的绝对路径前缀*/
	        	String realPath = getFileUploadPath() +fileDirectory+ newfilename; 
                
	        	log.info("文件{}，被上传到{}",filename,realPath);
                
                try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath));
					
				} catch (Exception e) {
					return new FailedResponse("服务器无法创建目录！");
				}
                return new ObjectResponse<FileUploadDTO>(new FileUploadDTO(filename, getFileRelativePath()+ fileDirectory+ newfilename));
	        }
	        else
	        	return new FailedResponse("上传失败");
	 }

}