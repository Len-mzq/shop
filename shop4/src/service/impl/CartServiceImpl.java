package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import entity.Cart;
import service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDao cartd;

	@Override // 往购物车和商品的关系表中添加数据
	public void add(Integer cart_id, Integer product_id, Integer amount) {
		cartd.add(cart_id, product_id, amount);
	}

	@Override // 查询购物车和商品的关系表中商品的数量
	public Integer searchAmount(Integer cart_id, Integer product_id) {
		Integer count = cartd.searchAmount(cart_id, product_id);
		return count;
	}

	@Override // 更新购物车和商品的关系表中数据
	public void updatePro_cart(Integer cart_id, Integer product_id, Integer amount, Integer amount0) {
		cartd.updatePro_cart(cart_id, product_id, amount, amount0);
	}
	
	@Override // 更新购物车和商品的关系表中数据(+/-)
	public void updatePro_cart2(Integer cart_id, Integer product_id, Integer amount) {
		cartd.updatePro_cart2(cart_id, product_id, amount);
	}

	@Override  // 删除购物车和商品的关系表中数据
	public void deletePro_cart(Integer cart_id, Integer product_id) {
		cartd.deletePro_cart(cart_id, product_id);
	}

	@Override // 把商品添加到购物车
	public void addToCart(Integer amount, String price) {
		cartd.addToCart(amount, price);
	}

	@Override // 查询购物车中商品的数量
	public Integer searchAmountInCart(Integer cart_id) {
		Integer count = cartd.searchAmountInCart(cart_id);
		return count;
	}

	@Override // 查询购物车中商品的数量
	public Integer searchPriceInCart(Integer cart_id) {
		Integer price = cartd.searchPriceInCart(cart_id);
		return price;
	}

	@Override // 查询购物车中商品的信息
	public Cart searchAllInCart(Integer cart_id) {
		Cart cart = cartd.searchAllInCart(cart_id);
		return cart;
	}

	@Override // 更新购物车
	public void updateCart(Integer amount, String price, Integer cart_id, Integer amount_cart, double price_cart) {
		cartd.updateCart(amount, price, cart_id, amount_cart, price_cart);
	}

	@Override // 根据用户名查用户id
	public Integer searchUserId(String username) {
		Integer id = cartd.searchUserId(username);
		return id;
	}

	@Override // 根据用户名查用户cart_id
	public Integer searchCart_id(String username) {
		Integer cart_id = cartd.searchCart_id(username);
		return cart_id;
	}

	@Override // 查询cart_id是否已经存在
	public boolean searchCart_idExit(Integer cart_id) {
		Integer rs = cartd.searchCart_idExit(cart_id);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}
	}

	@Override // 查询购物车里商品是否已经存在
	public boolean searchPro_idExit(Integer pro_id, Integer cart_id) {
		Integer rs = cartd.searchPro_idExit(pro_id, cart_id);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}

	}

}
