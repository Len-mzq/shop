package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;

	@RequestMapping(value = "showLogin")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("list/login");
		return mv;
	}

	@RequestMapping(value = "doLogin")
	@ResponseBody
	public String doLogin(String username, String password,HttpSession session) {
		User user = service.searchAll(username, password);
		if (user != null) {
			session.setAttribute("username", username);
			return "true";
		}
		return "false";
	}
	
	
	@RequestMapping(value = "showIndex")
	public ModelAndView showIndex() {
		ModelAndView mv = new ModelAndView("list/index");
		return mv;
	}
	

	@RequestMapping(value = "showRegister")
	public ModelAndView showRegister() {
		ModelAndView mv = new ModelAndView("list/register");
		return mv;
	}

	
	@RequestMapping(value = "doRegister")
	@ResponseBody
	public String doRegister(String phone, String nickname, String username, String password) {
		boolean flag1 = service.searchPhone(phone);// flag1等于true，说明电话号已存在
		boolean flag2 = service.searchNickName(nickname);// flag2等于true，说明昵称已存在
		boolean flag3 = service.searchUserName(username);// flag3等于true，说明用户名已存在
		if (phone.equals("")) {
			return "phone";
		} else if (nickname.equals("")) {
			return "nickname";
		} else if (username.equals("")) {
			return "username";
		} else if (password.equals("")) {
			return "password";
		} else if (flag1) {
			return "havePhone";
		} else if (flag2) {
			return "haveNickname";
		} else if (flag3) {
			return "haveUsername";
		} else {// flag等于false的时候才进else
			Cart c = new Cart();
			service.creatCart(c);
			service.add(phone, nickname, username, password,c.getId());
			return "true";
		}
	}
}
