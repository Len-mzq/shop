package servlet;

import java.io.File;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePicsFormServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if(type==null) {
			delete(request, response);
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String picname = request.getParameter("fileName"); // 接收参数
		File srcFolder = new File("d:/tu");
		File[] fileArray = srcFolder.listFiles();
		if (fileArray != null) {
			// 遍历该File数组，得到每一个File对象
			for (File file : fileArray) {
				if (file.getName().equals(picname)) {
					file.delete();
				}
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
