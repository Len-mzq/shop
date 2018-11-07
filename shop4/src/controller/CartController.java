package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.Product;
import service.CartService;
import service.ProductService;

@Controller
public class CartController {
	@Autowired
	CartService service;
	@Autowired
	ProductService productservice;

	@RequestMapping(value = "showCart")
	public ModelAndView showCart(HttpSession session) {
		String username = (String) session.getAttribute("username");
		ModelAndView mv = new ModelAndView("list/cart");
		Integer cart_id = service.searchCart_id(username);
		List<Product> pro = productservice.searchByIds(cart_id);
		Cart cart = service.searchAllInCart(cart_id);
		mv.addObject("pro", pro);
		mv.addObject("cart", cart);
		return mv;
	}

	@RequestMapping(value = "addAndShowCart")
	public ModelAndView addToCart(HttpSession session, Integer product_id, Integer amount, String price,
			Integer subtotal) {
		String username = (String) session.getAttribute("username");
		ModelAndView mv = new ModelAndView("list/cart");
		Integer cart_id = service.searchCart_id(username);
		boolean flag = service.searchCart_idExit(cart_id);// �жϹ��ﳵid�Ƿ����
		if (flag) {
			Integer amount_cart = service.searchAmountInCart(cart_id);//��ѯ���ﳵ����Ʒ������
			Integer price_cart = service.searchPriceInCart(cart_id);//��ѯ���ﳵ����Ʒ�ļ۸�
			if(amount_cart!=null) {
				service.updateCart(amount, price, cart_id,amount_cart,price_cart);// ��������ݣ��͸��¸ù��ﳵ����
			}else {
				service.addToCart(amount, price);// ���û�����ݣ����������
			}
			boolean flag1 = service.searchPro_idExit(product_id, cart_id);// �жϹ��ﳵ����Ʒ�Ƿ��Ѿ�����
			if (flag1) {
				Integer amount0 = service.searchAmount(cart_id, product_id);//��ѯ���ﳵ����Ʒ�Ĺ�ϵ������Ʒ������
				service.updatePro_cart(cart_id, product_id, amount,amount0);// ������ڣ��͸��¹�ϵ������Ʒ������
			} else {
				service.add(cart_id, product_id, amount);
			}
		} else {
			service.addToCart(amount, price);// ��������ڣ��ʹ����µĹ��ﳵ�����������
			service.add(cart_id, product_id, amount);
		}
		List<Product> pro = productservice.searchByIds(cart_id);
		Cart cart = service.searchAllInCart(cart_id);
		mv.addObject("pro", pro);
		mv.addObject("cart", cart);
		return mv;
	}
	
	@RequestMapping(value = "updateCart")
	public void updateCart(HttpSession session,Integer amount,Integer product_id,double price) {
		String username = (String) session.getAttribute("username");
		Integer cart_id = service.searchCart_id(username);
		service.updatePro_cart2(cart_id, product_id, amount);
	
	}
	
	@RequestMapping(value = "deleteCart")
	public String deleteCart(HttpSession session,Integer product_id) {
		String username = (String) session.getAttribute("username");
		Integer cart_id = service.searchCart_id(username);
		service.deletePro_cart(cart_id, product_id);
		return "redirect:showCart.do";
	}

}
