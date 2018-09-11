package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import util.CreateMD5;
import util.RandomNumber;
import util.ValidateCode;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("showRegister".equals(type)) {
			showRegister(request, response);
		} else if ("doRegister".equals(type)) {
			doRegister(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		}
	}

	private void showRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/login/Register.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			Date d = new Date(); // 创建日期对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 给定模式,创建格式化对象
			String time = CreateMD5.encryptionByMD5(sdf.format(d)); // 按照给定模式格式化日期对象,并对日期进行MD5加密
			PrintWriter out = response.getWriter();
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			String psw = CreateMD5.encryptionByMD5(password + time);
			UserDao ud = new UserDao();
			boolean flag = ud.searchUserName(user);// flag等于true，说明用户名已存在
			if (user.equals("")) {
				String data = "username";
				out.print(data);
			} else if (password.equals("")) {
				String data = "password";
				out.print(data);
			} else if (flag) {
				int have = 1;
				out.print(have);
			} else {// flag等于false的时候才进else
				flag = ud.add(user, psw, time);
				out.print(flag);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			Cookie[] cookie = request.getCookies();
			if (cookie != null) {
				for (Cookie cookies : cookie) {
					if ("username".equals(cookies.getName())) {
						name = cookies.getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login/Login.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();// 创建session对象
			PrintWriter out = response.getWriter();
			String user = request.getParameter("username");
			UserDao ud = new UserDao();
			String ver = request.getParameter("verification");
			if (ver.equals(session.getAttribute("random"))) {
				boolean flag = false;
				String time = ud.searchTime(user);// 根据用户名查出用户的注册时间
				if (time != null) {
					String psw = CreateMD5.encryptionByMD5(request.getParameter("password") + time);
					flag = ud.search(user, psw);
					session.setAttribute("user", user);
					Cookie cookie = new Cookie("username", user);// 创建cookie对象
					cookie.setMaxAge(60 * 60 * 60 * 24);// 设置cookie缓存的时间
					response.addCookie(cookie);
				}
				out.print(flag);
			} else {
				int a = -1;
				out.print(a);
			}

			// if (flag) {
			//
			// request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request,
			// response);
			// } else {
			// request.getRequestDispatcher("WEB-INF/login/Fail.jsp").forward(request,
			// response);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		// catch (ServletException e) {
		// e.printStackTrace();
		// }
	}

	// 验证码
	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			RandomNumber rn = new RandomNumber();
			// 设置页面不缓存
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ValidateCode vc = rn.generateImage();
			// 创建OutputStream流对象
			ServletOutputStream outStream = response.getOutputStream();

			// 输出图象到页面
			ImageIO.write(vc.getImage(), "JPEG", outStream);// 参数(哪张图片，图片格式，使用哪个流)
			outStream.close();

			// 将认证码存入SESSION
			HttpSession session = request.getSession();
			session.setAttribute("random", vc.getRand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
