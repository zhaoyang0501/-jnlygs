package xwgl.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DownloadUtil {

	public static void down(String path, String name, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + "."
				+ StringUtils.getFilenameExtension(path));
		URL url = new URL(path);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		OutputStream out = response.getOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
	}
}
