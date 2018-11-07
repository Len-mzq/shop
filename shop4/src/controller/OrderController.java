package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Address;
import entity.City;
import entity.Orders;
import entity.Product;
import entity.Province;
import service.CartService;
import service.OrderService;
import util.GenerateOrderNo;

@Controller
public class OrderController {
	@Autowired
	OrderService service;
	@Autowired
	CartService cartService;

	@RequestMapping(value = "showAdd")
	public ModelAndView showCart(HttpSession session,String product_id, String price) {
		ModelAndView mv = new ModelAndView("list/add");
		List<Product> pro = service.searchByProduct_id(product_id);
		String username = (String) session.getAttribute("username");
		Integer cart_id = cartService.searchCart_id(username);
		List<Address> addrList = service.searchAddress(cart_id);
		mv.addObject("pro", pro);
		mv.addObject("price", price);
		mv.addObject("product_id", product_id);
		mv.addObject("addrList", addrList);
		return mv;
	}

	@RequestMapping(value = "showOrders")
	public ModelAndView showOrders(String product_id, String price,Integer addressId) {
		ModelAndView mv = new ModelAndView("list/orders");
		String orderNum = GenerateOrderNo.generate();
		Date d = new Date(); // 创建日期对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 给定模式,创建格式化对象
		String time = sdf.format(d); // 按照给定模式格式化日期对象
		service.addT_order(price, time, orderNum, addressId);
		Integer orderId = service.searchIdByTime(time);
		List<Product> pro = service.searchByProduct_id(product_id);
		service.addT_order_item(pro,orderId,product_id);
		List<Orders> listOrder = service.searchT_order();
		List<Orders> listOrderIsPay = service.searchT_orderIsPay();
		mv.addObject("pro", pro);
		mv.addObject("price", price);
		mv.addObject("listOrder", listOrder);
		mv.addObject("listOrderIsPay", listOrderIsPay);
		return mv;
	}

	@RequestMapping(value = "searchProvince")
	@ResponseBody
	public List<Province> searchProvince() {
		List<Province> list = service.searchProvince();
		return list;
	}

	@RequestMapping(value = "searchCityByProvince")
	@ResponseBody
	public List<City> searchCityByProvince(Integer provinceId) {
		List<City> list = service.searchCityByProvince(provinceId);
		return list;
	}

	@RequestMapping(value = "searchAreaByCity")
	@ResponseBody
	public List<City> searchAreaByCity(Integer cityId) {
		List<City> list = service.searchAreaByCity(cityId);
		return list;
	}

	@RequestMapping(value = "addAddress")
	public String addAddress(HttpSession session,String street, String person, String phone, Integer areaId,String product_id, String price) {
		String username = (String) session.getAttribute("username");
		Integer cart_id = cartService.searchCart_id(username);
		service.addAddress(cart_id,street, person, phone, areaId);
		return "redirect:showAdd.do?product_id="+product_id+"&price"+price;
	}

	@RequestMapping(value = "deleteAddress")
	public String deleteAddress(Integer addressId,String product_id, String price) {
		service.deleteAddress(addressId);
		return "redirect:showAdd.do?product_id="+product_id+"&price"+price;
	}
	
	@RequestMapping(value = "deleteOrder")
	public ModelAndView deleteOrder(Integer orderId) {
		ModelAndView mv = new ModelAndView("list/orders");
		service.deleteOrder(orderId);
		service.deleteOrderItem(orderId);
		List<Orders> listOrder = service.searchT_order();
		List<Orders> listOrderIsPay = service.searchT_orderIsPay();
		mv.addObject("listOrder", listOrder);
		mv.addObject("listOrderIsPay", listOrderIsPay);
		return mv;
	}
	
	@RequestMapping(value = "showPay")
	public ModelAndView showPay(String orderId,String orderNum,String price) {
		ModelAndView mv = new ModelAndView("list/pay");
		List<Product> pro = service.searchByOrder_id(orderId);
		mv.addObject("pro", pro);
		mv.addObject("orderNum", orderNum);
		mv.addObject("price", price);
		return mv;
	}
	
	@RequestMapping(value = "doPay")
	public ModelAndView doPay(String orderNum) {
		ModelAndView mv = new ModelAndView("list/orders");
		service.updateOrder(orderNum);
		List<Orders> listOrder = service.searchT_order();
		List<Orders> listOrderIsPay = service.searchT_orderIsPay();
		mv.addObject("listOrder", listOrder);
		mv.addObject("listOrderIsPay", listOrderIsPay);
		return mv;
	}
}
