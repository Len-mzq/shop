package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Address;
import entity.City;
import entity.Orders;
import entity.Product;
import entity.Province;

public interface OrderDao {

	// 根据商品id查询商品信息
	public List<Product> searchByProduct_id(@Param(value = "product_id") String product_id);
	
	// 根据订单id查询商品信息
	public List<Product> searchByOrder_id(@Param(value = "orderId") String orderId);

	// 查询地址
	public List<Address> searchAddress(Integer user_id);

	// 查询省
	public List<Province> searchProvince();

	// 查询市
	public List<City> searchCityByProvince(Integer provinceId);

	// 查询区
	public List<City> searchAreaByCity(Integer cityId);

	// 新增地址
	public void addAddress(Integer cart_id, String street, String person, String phone, Integer areaId);

	// 删除地址
	public void deleteAddress(Integer addressId);

	// 往订单表t_order插入数据
	public void addT_order(String total_price, String creat_time, String order_num, Integer address_id);

	// 往订单详情表t_order_item插入数据
	public void addT_order_item(Integer amount, Integer orderId, String product_id);

	// 查询订单表t_order(未付款)
	public List<Orders> searchT_order();

	// 查询订单表t_order(已付款)
	public List<Orders> searchT_orderIsPay();

	// 查询订单详情表t_order_item
	public List<Product> searchT_order_item(Integer order_id);

	// 根据订单创建时间获得订单id
	public Integer searchIdByTime(String time);

	// 删除订单
	public void deleteOrder(Integer orderId);

	// 删除订单详情
	public void deleteOrderItem(Integer orderId);
	
	// 修改订单状态（支付）
	public void updateOrder(String orderNum);
	
}
