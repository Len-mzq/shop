package dao;

import org.apache.ibatis.annotations.Param;

import entity.Cart;
import entity.User;

public interface UserDao {

	// 查询用户是否已经存在
	public User searchAll(String username, String password);

	// 查询电话号是否已经存在
	public Integer searchPhone(String phone);

	// 查询昵称是否已经存在
	public Integer searchNickName(String nickname);

	// 查询用户名是否已经存在
	public Integer searchUserName(String username);

	// 查询用户注册时间
	public String searchTime(String username);

	// 创建一个新的购物车
	public void creatCart(Cart c);

	// 用户注册
	public int add(@Param(value = "phone") String phone, @Param(value = "nickname") String nickname,
			@Param(value = "username") String username, @Param(value = "password") String password,
			@Param(value = "cart_id") int cart_id);

}
