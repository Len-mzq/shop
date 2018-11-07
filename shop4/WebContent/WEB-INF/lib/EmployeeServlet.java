package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import vo.PageVo;

public class EmployeeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
				request.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null || "search".equals(type)) {
					search(request, response);
				} else if ("showAdd".equals(type)) {
					showAdd(request, response);
				} else if ("showAdd2".equals(type)) {
					showAdd2(request, response);
				} else if ("doAdd".equals(type)) {
					doAdd(request, response);
				} else if ("doAdd2".equals(type)) {
					doAdd2(request, response);
				} else if ("upload".equals(type)) {
					upload(request, response);
				} else if ("doDelete".equals(type)) {
					doDelete(request, response);
				} else if ("showUpdate".equals(type)) {
					showUpdate(request, response);
				} else if ("showUpdate2".equals(type)) {
					showUpdate2(request, response);
				} else if ("doUpdate".equals(type)) {
					doUpdate(request, response);
				} else if ("doUpdate2".equals(type)) {
					doUpdate2(request, response);
				} else if ("doUpdate3".equals(type)) {
					doUpdate3(request, response);
				} else if ("search".equals(type)) {
					search(request, response);
				}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showEmployeeByPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示员工列表
			String pageNoStr = request.getParameter("pageNo"); // 接收参数
			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			// 如果当前页数小于1，默认显示第一页
			if (StringUtils.isNotEmpty(pageNoStr) && Integer.parseInt(pageNoStr) < 1) {
				pageNo = 1;
			}

			EmployeeDao empd = new EmployeeDao();
			PageVo<Employee> pagevo = new PageVo<Employee>();
			int count = empd.getCount(); // 获取员工总数
			pagevo.setTotalRecords(count);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Employee> list = empd.searchByPage(pagevo.getStartIndex(), pagevo.getPageSize());// 获取每页员工列表
			pagevo.setRecords(list);
			pagevo.setUrl("employee?pageNo=");// 返回页面
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/employee/EmployeeView.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empd = new EmployeeDao();
			List<Department> depList = empd.searchDepartment();// 查出部门信息
			request.setAttribute("depList", depList);// 传参
			request.getRequestDispatcher("WEB-INF/employee/Add.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empd = new EmployeeDao();
			List<Department> depList = empd.searchDepartment();// 查出部门信息
			request.setAttribute("depList", depList);// 传参
			request.getRequestDispatcher("WEB-INF/employee/Add2.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String picName = "";
			String name = "";
			String sex = "";
			int age = -1;
			String department = "";
			String path = "d:/tu/";
			// 创建一个DiskFileItemFactory工厂类
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			// 创建一个ServletFileUpload核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析request对象，得到一个表单集合
			// 因为版本问题导致，parseRequest参数应该用request构造一个对象再传进去
			// List<FileItem> items = upload.parseRequest(request);
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myPicture")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					picName = uuid.toString() + houzhui;
					File savedFile = new File(path, picName);
					item.write(savedFile);
				} else if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("age")) {
					String tempAge = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					age = Integer.valueOf(tempAge);
				} else if (item.getFieldName().equals("department")) {
					department = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				}
			}
			// String name = request.getParameter("name");
			// String sex = request.getParameter("sex");
			// int age = Integer.valueOf(request.getParameter("age"));
			// String department = request.getParameter("department");
			EmployeeDao empd = new EmployeeDao();
			Department dep = new Department();
			dep.setName(department);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			emp.setPic(picName);

			int id = empd.searchID(department);
			empd.add(emp, id);

			response.sendRedirect("employee");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.valueOf(request.getParameter("age"));
			String department = request.getParameter("department");
			String pictureName = request.getParameter("picture");
			EmployeeDao empd = new EmployeeDao();
			Department dep = new Department();
			dep.setName(department);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			emp.setPic(pictureName);

			int id = empd.searchID(department);
			empd.add(emp, id);

			response.sendRedirect("employee");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String picName = "";
			String path = "d:/tu/";
			// 创建一个DiskFileItemFactory工厂类
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			// 创建一个ServletFileUpload核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析request对象，得到一个表单集合
			// 因为版本问题导致，parseRequest参数应该用request构造一个对象再传进去
			// List<FileItem> items = upload.parseRequest(request);
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myPicture")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					picName = uuid.toString() + houzhui;
					File savedFile = new File(path, picName);
					item.write(savedFile);
				}
			}
			PrintWriter out = response.getWriter();
			out.print(picName);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao empd = new EmployeeDao();
		String ID = request.getParameter("selectId"); // 获取选中的员工id

		empd.deleteBatch(ID);

		try {
			response.sendRedirect("employee");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo"); // 接收参数
		int pageNo = 1;
		if (StringUtils.isNotEmpty(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		try {
			String ID = request.getParameter("selectId"); // 获取选中的员工id
			EmployeeDao empd = new EmployeeDao();
			List<Employee> empList = empd.search(ID);

			PageVo<Employee> pagevo = new PageVo<Employee>();
			pagevo.setPageNo(pageNo);
			List<Department> depList = empd.searchDepartment();// 查出部门信息
			request.setAttribute("depList", depList);// 传参
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("WEB-INF/employee/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpdate2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("selectId"); // 获取选中的员工id
			EmployeeDao empd = new EmployeeDao();
			List<Employee> empList = empd.search(ids);

			request.setAttribute("emp", empList.get(0));
			request.setAttribute("ids", ids);
			request.getRequestDispatcher("WEB-INF/employee/update2.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String pageNo = request.getParameter("pageNo");
			EmployeeDao empd = new EmployeeDao();
			String[] id = request.getParameterValues("id");
			String[] name = request.getParameterValues("name");
			String[] sex = request.getParameterValues("sex");
			String[] age = request.getParameterValues("age");
			String[] department = request.getParameterValues("department");
			Employee emp = new Employee();
			Department dep = new Department();
			for (int i = 0; i < id.length; i++) {
				emp.setId(Integer.parseInt(id[i]));
				emp.setName(name[i]);
				emp.setSex(sex[i]);
				emp.setAge(Integer.parseInt(age[i]));
				int d_id = empd.searchID(department[i]);
				emp.setD_id(d_id);
				empd.update(emp);
			}

			response.sendRedirect("employee?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String pageNo = request.getParameter("pageNo");
			String ids = request.getParameter("ids");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.valueOf(request.getParameter("age"));
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			EmployeeDao empd = new EmployeeDao();
			empd.update(ids, emp);

			response.sendRedirect("employee?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate3(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			String pageNo = request.getParameter("pageNo");
			String[] array = emps.split(";");
			List<Employee> list = new ArrayList<Employee>();
			EmployeeDao empd = new EmployeeDao();
			for (int i = 0; i < array.length; i++) {
				String[] tempArray = array[i].split(",");
				Employee emp = new Employee();
				Department dep = new Department();
				emp.setId(Integer.parseInt(tempArray[0]));
				emp.setName(tempArray[1]);
				emp.setSex(tempArray[2]);
				emp.setAge(Integer.parseInt(tempArray[3]));
				int d_id = empd.searchID(tempArray[4]);
				emp.setD_id(d_id);
				list.add(emp);
			}
			empd.update(list);

			response.sendRedirect("employee?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示员工列表
			String name = request.getParameter("name"); // 接收参数
			String sex = request.getParameter("sex");
			String ageS = request.getParameter("age");
			String department = request.getParameter("department");
			int age = -1;
			if (StringUtils.isNotEmpty(ageS)) {
				age = Integer.parseInt(ageS);
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

			EmployeeDao empd = new EmployeeDao();
			int d_id = empd.searchID(department);
			PageVo<Employee> pagevo = new PageVo<Employee>();
			Department dep = new Department();
			dep.setName(department);
			Employee emp = new Employee();
			emp.setAge(age);
			emp.setName(name);
			emp.setSex(sex);
			emp.setDep(dep);
			emp.setD_id(d_id);

			int count = empd.getSearchCount(emp); // 获取员工总数
			pagevo.setTotalRecords(count);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Employee> list = empd.searchByCondition(emp, pagevo.getStartIndex(), pagevo.getPageSize());
			pagevo.setRecords(list);
			pagevo.setUrl("employee?");// 返回页面
			List<Department> depList = empd.searchDepartment();// 查出部门信息
			request.setAttribute("depList", depList);// 传参
			request.setAttribute("emp", emp);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/employee/EmployeeView.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
