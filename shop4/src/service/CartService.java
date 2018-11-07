package service;

import entity.Cart;

public interface CartService {
	// 往购物车和商品的关系表中添加数据
	public void add(Integer cart_id, Integer product_id, Integer amount);

	// 查询购物车和商品的关系表中商品的数量
	public Integer searchAmount(Integer cart_id, Integer product_id);

	// 更新购物车和商品的关系表中数据
	public void updatePro_cart(Integer cart_id, Integer product_id, Integer amount, Integer amount0);

	// 更新购物车和商品的关系表中数据(+/-)
	public void updatePro_cart2(Integer cart_id, Integer product_id, Integer amount);
	
	// 删除购物车和商品的关系表中数据
	public void deletePro_cart(Integer cart_id, Integer product_id);

	// 把商品添加到购物车
	public void addToCart(Integer amount, String price);

	// 查询购物车中商品的数量
	public Integer searchAmountInCart(Integer cart_id);

	// 查询购物车中商品的价格
	public Integer searchPriceInCart(Integer cart_id);

	// 查询购物车中商品的信息
	public Cart searchAllInCart(Integer cart_id);

	// 更新购物车
	public void updateCart(Integer amount, String price, Integer cart_id, Integer amount_cart, double price_cart);

	// 根据用户名查用户id
	public Integer searchUserId(String username);

	// 根据用户名查用户cart_id
	public Integer searchCart_id(String username);

	// 查询cart_id是否已经存在
	public boolean searchCart_idExit(Integer cart_id);

	// 查询购物车里商品是否已经存在
	public boolean searchPro_idExit(Integer pro_id, Integer cart_id);

}
