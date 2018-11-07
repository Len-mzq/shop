package service;

import entity.Cart;
import entity.User;

public interface UserService {

	// 查询用户是否已经存在
	public User searchAll(String username, String password);

	// 查询电话号是否已经存在
	public boolean searchPhone(String phone);

	// 查询昵称是否已经存在
	public boolean searchNickName(String nickname);

	// 查询用户名是否已经存在
	public boolean searchUserName(String username);

	// 查询用户注册时间
	public String searchTime(String username);

	// 创建一个新的购物车
	public void creatCart(Cart c);

	// 用户注册
	public boolean add(String phone, String nickname, String username, String password,int cart_id);

}
