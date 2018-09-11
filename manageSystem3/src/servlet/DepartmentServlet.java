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

import dao.DepartmentDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;
import vo.PageVo;

public class DepartmentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
				request.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null || "search".equals(type)) {
					search(request, response);
				} else if ("showAdd".equals(type)) {
					showAdd(request, response);
				} else if ("doAdd".equals(type)) {
					doAdd(request, response);
				} else if ("doDelete".equals(type)) {
					doDelete(request, response);
				} else if ("showUpdate".equals(type)) {
					showUpdate(request, response);
				} else if ("doUpdate".equals(type)) {
					doUpdate(request, response);
				} else if ("doUpdate3".equals(type)) {
					doUpdate3(request, response);
				} else if ("ProjectManage".equals(type)) {
					ProjectManage(request, response);
				} else if ("ProjectManage2".equals(type)) {
					ProjectManage2(request, response);
				} else if ("ProjectManage3".equals(type)) {
					ProjectManage3(request, response);
				} else if ("ProjectManage4".equals(type)) {
					ProjectManage4(request, response);
				} else if ("deletePro".equals(type)) {
					deletePro(request, response);
				} else if ("deletePro2".equals(type)) {
					deletePro2(request, response);
				} else if ("addProToDep".equals(type)) {
					addProToDep(request, response);
				} else if ("addProToDep2".equals(type)) {
					addProToDep2(request, response);
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/department/DepartmentAdd.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao depd = new DepartmentDao();
		String name = request.getParameter("name");
		Department dep = new Department();
		dep.setName(name);
		depd.add(dep);

		try {
			response.sendRedirect("department");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao depd = new DepartmentDao();
		String ID = request.getParameter("selectId"); // 获取选中的部门id

		depd.deleteBatch(ID);

		try {
			response.sendRedirect("department");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deletePro(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao prod = new ProjectDao();
			String ID = request.getParameter("selectId"); // 获取选中的项目的id
			String name = request.getParameter("depName"); // 获取当前部门的名字
			prod.deletePro(ID, name);
			DepartmentDao depd = new DepartmentDao();
			int depId = depd.searchDepId(name);

			response.sendRedirect("department?type=ProjectManage&selectId=" + depId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deletePro2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			ProjectDao prod = new ProjectDao();
			String ID = request.getParameter("selectId"); // 获取选中的项目的id
			String name = request.getParameter("depName"); // 获取当前部门的名字
			boolean flag = prod.deletePro(ID, name);
			out.print(flag);
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
			String ID = request.getParameter("selectId"); // 获取选中的部门id
			DepartmentDao depd = new DepartmentDao();
			List<Department> depList = depd.search(ID);

			PageVo<Department> pagevo = new PageVo<Department>();
			pagevo.setPageNo(pageNo);
			request.setAttribute("depList", depList);
			request.getRequestDispatcher("WEB-INF/department/DepartmentUpdate.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpdate2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("selectId"); // 获取选中的部门id
			DepartmentDao depd = new DepartmentDao();
			List<Department> depList = depd.search(ids);

			request.setAttribute("dep", depList.get(0));
			request.setAttribute("ids", ids);
			request.getRequestDispatcher("WEB-INF/department/update2.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String pageNo = request.getParameter("pageNo");
			DepartmentDao depd = new DepartmentDao();
			String[] id = request.getParameterValues("id");
			String[] name = request.getParameterValues("name");
			Department dep = new Department();
			for (int i = 0; i < id.length; i++) {
				dep.setId(Integer.parseInt(id[i]));
				dep.setName(name[i]);
				depd.update(dep);
			}

			response.sendRedirect("department?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate3(HttpServletRequest request, HttpServletResponse response) {
		try {
			String deps = request.getParameter("deps");
			String pageNo = request.getParameter("pageNo");
			String[] array = deps.split(";");
			List<Department> list = new ArrayList<Department>();
			for (int i = 0; i < array.length; i++) {
				String[] tdepArray = array[i].split(",");
				Department dep = new Department();
				dep.setId(Integer.parseInt(tdepArray[0]));
				dep.setName(tdepArray[1]);
				dep.setCount(Integer.parseInt(tdepArray[2]));
				list.add(dep);
			}
			DepartmentDao depd = new DepartmentDao();
			depd.update(list);

			response.sendRedirect("department?pageNo=" + pageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示部门列表
			String name = request.getParameter("name"); // 接收参数
			String countS = request.getParameter("count");
			int count = -1;
			if (StringUtils.isNotEmpty(countS)) {
				count = Integer.parseInt(countS);
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

			DepartmentDao depd = new DepartmentDao();
			PageVo<Department> pagevo = new PageVo<Department>();
			Department dep = new Department();
			dep.setName(name);
			dep.setCount(count);
			int depCount = depd.getSearchCount(dep); // 获取部门总数
			pagevo.setTotalRecords(depCount);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Department> list = depd.searchByCondition(dep, pagevo.getStartIndex(), pagevo.getPageSize());
			pagevo.setRecords(list);
			pagevo.setUrl("department?");// 返回页面
			request.setAttribute("dep", dep);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/department/DepartmentView.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ProjectManage(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示项目列表
			String id = request.getParameter("selectId"); // 获取选中的部门id
			int d_id = Integer.parseInt(id);
			String pageNoStr = request.getParameter("pageNo"); // 接收参数
			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			// 如果当前页数小于1，默认显示第一页
			if (StringUtils.isNotEmpty(pageNoStr) && Integer.parseInt(pageNoStr) < 1) {
				pageNo = 1;
			}
			ProjectDao prod = new ProjectDao();
			DepartmentDao depd = new DepartmentDao();
			PageVo<Project> pagevo = new PageVo<Project>();
			Project pro = new Project();
			int proCount = prod.getSearchCount(d_id); // 获取对应部门的项目总数
			pagevo.setTotalRecords(proCount);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Project> list = prod.searchPro(d_id, pagevo.getStartIndex(), pagevo.getPageSize());// 根据部门id查询选择的部门有的项目
			pagevo.setRecords(list);
			pagevo.setUrl("department?");// 返回页面
			String depName = depd.search(d_id);// 根据部门id得到部门名
			List<Project> noList = prod.searchProNo(depName);// 根据部门名查询选择的部门没有的项目
			request.setAttribute("noList", noList);
			request.setAttribute("depName", depName);
			request.setAttribute("d_id", d_id);
			request.setAttribute("pro", pro);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/department/ProjectManage.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ProjectManage2(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示项目列表
			String id = request.getParameter("selectId"); // 获取选中的部门id
			int d_id = Integer.parseInt(id);
			String pageNoStr = request.getParameter("pageNo"); // 接收参数
			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			// 如果当前页数小于1，默认显示第一页
			if (StringUtils.isNotEmpty(pageNoStr) && Integer.parseInt(pageNoStr) < 1) {
				pageNo = 1;
			}
			ProjectDao prod = new ProjectDao();
			DepartmentDao depd = new DepartmentDao();
			PageVo<Project> pagevo = new PageVo<Project>();
			Project pro = new Project();
			int proCount = prod.getSearchCount(d_id); // 获取对应部门的项目总数
			pagevo.setTotalRecords(proCount);// 填充到pagevo中
			// 如果当前页数大于总页数，默认显示最后一页
			if (StringUtils.isNotEmpty(pageNoStr) && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
			pagevo.setPageNo(pageNo);
			List<Project> list = prod.searchPro(d_id, pagevo.getStartIndex(), pagevo.getPageSize());// 根据部门id查询选择的部门有的项目
			pagevo.setRecords(list);
			pagevo.setUrl("department?");// 返回页面
			String depName = depd.search(d_id);// 根据部门id得到部门名
			List<Project> noList = prod.searchProNo(depName);// 根据部门名查询选择的部门没有的项目
			request.setAttribute("noList", noList);
			request.setAttribute("depName", depName);
			request.setAttribute("d_id", d_id);
			request.setAttribute("pro", pro);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/department/ProjectManage2-ajax.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ProjectManage3(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示项目列表
			String id = request.getParameter("selectId"); // 获取选中的部门id
			int d_id = Integer.parseInt(id);
			ProjectDao prod = new ProjectDao();
			DepartmentDao depd = new DepartmentDao();
			PageVo<Project> pagevo = new PageVo<Project>();
			Project pro = new Project();
			List<Project> list = prod.searchPro(d_id, 0, 100);// 根据部门id查询选择的部门有的项目
			pagevo.setRecords(list);
			String depName = depd.search(d_id);// 根据部门id得到部门名
			List<Project> noList = prod.searchProNo(depName);// 根据部门名查询选择的部门没有的项目
			request.setAttribute("noList", noList);
			request.setAttribute("depName", depName);
			request.setAttribute("d_id", d_id);
			request.setAttribute("pro", pro);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/department/ProjectManage3.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ProjectManage4(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 显示项目列表
			String id = request.getParameter("selectId"); // 获取选中的部门id
			int d_id = Integer.parseInt(id);
			ProjectDao prod = new ProjectDao();
			DepartmentDao depd = new DepartmentDao();
			PageVo<Project> pagevo = new PageVo<Project>();
			Project pro = new Project();
			List<Project> list = prod.searchPro(d_id, 0, 100);// 根据部门id查询选择的部门有的项目
			pagevo.setRecords(list);
			String depName = depd.search(d_id);// 根据部门id得到部门名
			List<Project> noList = prod.searchProNo(depName);// 根据部门名查询选择的部门没有的项目
			request.setAttribute("noList", noList);
			request.setAttribute("depName", depName);
			request.setAttribute("d_id", d_id);
			request.setAttribute("pro", pro);
			request.setAttribute("pagevo", pagevo);
			request.getRequestDispatcher("WEB-INF/department/ProjectManage4.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addProToDep(HttpServletRequest request, HttpServletResponse response) {
		ProjectDao prod = new ProjectDao();
		String selectId = request.getParameter("selectId");// 获取部门id
		String proName = request.getParameter("proName");// 获取项目名
		int proId = prod.searchId(proName);// 根据项目名获取项目id
		int depId = Integer.valueOf(selectId);// 把部门id转成int类型
		prod.addProToDep(depId, proId);
		try {
			response.sendRedirect("department?type=ProjectManage&selectId=" + selectId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addProToDep2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			ProjectDao prod = new ProjectDao();
			String selectId = request.getParameter("selectId");// 获取部门id
			String proId = request.getParameter("proId");// 获取项目id
			int depId = Integer.valueOf(selectId);// 把部门id转成int类型
			boolean flag = prod.addProToDep2(depId, proId);
			out.print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
