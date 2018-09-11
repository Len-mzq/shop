package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;

import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import vo.PageVo;

public class ScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
				request.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null || "search".equals(type)) {
					search(request, response);
				} else if ("doUpdate".equals(type)) {
					doUpdate(request, response);
				} else if ("manage".equals(type) || "search2".equals(type)) {
					manage(request, response);
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String scos = request.getParameter("lists");
			String pageNo = request.getParameter("pageNo");
			String[] array = scos.split(";");
			List<Score> list = new ArrayList<Score>();
			for (int i = 0; i < array.length; i++) {
				String[] tscoArray = array[i].split(",");
				Score sco = new Score();
				Employee emp = new Employee();
				Project pro = new Project();
				sco.setId(Integer.parseInt(tscoArray[0]));
				sco.setValue(Integer.valueOf(tscoArray[1]));
				emp.setId(Integer.valueOf(tscoArray[2]));
				pro.setId(Integer.valueOf(tscoArray[3]));
				sco.setEmp(emp);
				sco.setPro(pro);
				list.add(sco);
			}
			ScoreDao scod = new ScoreDao();
			scod.save(list);

			response.sendRedirect("score?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示绩效列表
			String employee = request.getParameter("name"); // 接收搜索框里的参数
			if (StringUtils.isEmpty(employee)) {
				employee = "";
			}
			String grade = request.getParameter("grade");
			if (StringUtils.isEmpty(grade)) {
				grade = "";
			}
			String project = request.getParameter("project");
			if (StringUtils.isEmpty(project)) {
				project = "";
			}
			String department = request.getParameter("department");
			if (StringUtils.isEmpty(department)) {
				department = "";
			}
			String pageNoStr = request.getParameter("pageNo"); // 接收参数
			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			// 如果当前页数小于1，默认显示第一页
			if (StringUtils.isNotEmpty(pageNoStr) && Integer.parseInt(pageNoStr) < 1) {
				pageNo = 1;
			}

			ScoreDao scod = new ScoreDao();
			PageVo<Score> pagevo = new PageVo<Score>();
			Score sco = new Score();
			Employee emp = new Employee();
			Project pro = new Project();
			Department dep = new Department();
			emp.setName(employee);
			pro.setName(project);
			dep.setName(department);
			sco.setDep(dep);
			sco.setEmp(emp);
			sco.setPro(pro);
			sco.setGrade(grade);
			List<Department> depList = scod.searchDep();
			List<Project> proList = scod.searchPro(department);
			//PrintWriter out = response.getWriter();
			//JSONArray jsonarray = JSONArray.fromObject(proList);
			//out.print(proList);
			int count = scod.getCount(sco); // 获取查询到的数据总数
			pagevo.setTotalRecords(count);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Score> list = scod.searchByCondition(sco, pagevo.getStartIndex());
			// List<Score> list = scod.search(pagevo.getStartIndex());
			pagevo.setRecords(list);
			pagevo.setUrl("score?");// 返回页面
			request.setAttribute("sco", sco);
			request.setAttribute("depList", depList);
			request.setAttribute("proList", proList);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/score/ScoreView.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void manage(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示绩效列表
			String employee = request.getParameter("name"); // 接收搜索框里的参数
			if (StringUtils.isEmpty(employee)) {
				employee = "";
			}
			String grade = request.getParameter("grade");
			if (StringUtils.isEmpty(grade)) {
				grade = "";
			}
			String project = request.getParameter("project");
			if (StringUtils.isEmpty(project)) {
				project = "";
			}
			String department = request.getParameter("department");
			if (StringUtils.isEmpty(department)) {
				department = "";
			}
			String pageNoStr = request.getParameter("pageNo"); // 接收参数
			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			// 如果当前页数小于1，默认显示第一页
			if (StringUtils.isNotEmpty(pageNoStr) && Integer.parseInt(pageNoStr) < 1) {
				pageNo = 1;
			}

			ScoreDao scod = new ScoreDao();
			PageVo<Score> pagevo = new PageVo<Score>();
			Score sco = new Score();
			Employee emp = new Employee();
			Project pro = new Project();
			Department dep = new Department();
			emp.setName(employee);
			pro.setName(project);
			dep.setName(department);
			sco.setDep(dep);
			sco.setEmp(emp);
			sco.setPro(pro);
			sco.setGrade(grade);
			List<Department> depList = scod.searchDep();
			List<Project> proList = scod.searchPro(department);
			int count = scod.getCount(sco); // 获取查询到的数据总数
			pagevo.setTotalRecords(count);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Score> list = scod.searchByCondition(sco, pagevo.getStartIndex());

			// List<Score> list = scod.search(pagevo.getStartIndex());
			pagevo.setRecords(list);
			pagevo.setUrl("score?");// 返回页面
			request.setAttribute("sco", sco);
			request.setAttribute("depList", depList);
			request.setAttribute("proList", proList);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/score/ScoreView2.jsp").forward(request, response);
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
