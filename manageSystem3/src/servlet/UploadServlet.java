package servlet;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// String path = request.getServletContext().getRealPath("/") + "/pic";

			String path = "d:/tu/";
			//创建一个DiskFileItemFactory工厂类
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			//创建一个ServletFileUpload核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析request对象，得到一个表单集合
			//因为版本问题导致，parseRequest参数应该用request构造一个对象再传进去
			//List<FileItem> items = upload.parseRequest(request);
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myPicture")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					File savedFile = new File(path, uuid.toString() + houzhui);
					item.write(savedFile);
				} 
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
}
