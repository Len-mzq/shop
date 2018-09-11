package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import dao.ProjectDao;
import entity.Project;
import vo.PageVo;

public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
				request.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null) {
					index(request, response);
				}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
