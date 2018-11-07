package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderDao;
import entity.Address;
import entity.City;
import entity.Orders;
import entity.Product;
import entity.Province;
import service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderd;

	@Override // 根据商品id查询商品信息
	public List<Product> searchByProduct_id(String orderId) {
		List<Product> list = orderd.searchByProduct_id(orderId);
		return list;
	}

	@Override // 根据订单id查询商品信息
	public List<Product> searchByOrder_id(String product_id) {
		List<Product> list = orderd.searchByOrder_id(product_id);
		return list;
	}
	
	@Override // 查询省
	public List<Province> searchProvince() {
		List<Province> list = orderd.searchProvince();
		return list;
	}

	@Override // 查询市
	public List<City> searchCityByProvince(Integer provinceId) {
		List<City> list = orderd.searchCityByProvince(provinceId);
		return list;
	}

	@Override // 查询区
	public List<City> searchAreaByCity(Integer cityId) {
		List<City> list = orderd.searchAreaByCity(cityId);
		return list;
	}

	@Override // 新增地址
	public void addAddress(Integer cart_id, String street, String person, String phone, Integer areaId) {
		orderd.addAddress(cart_id, street, person, phone, areaId);
	}

	@Override // 删除地址
	public void deleteAddress(Integer addressId) {
		orderd.deleteAddress(addressId);

	}

	@Override // 查询地址
	public List<Address> searchAddress(Integer user_id) {
		List<Address> list = orderd.searchAddress(user_id);
		return list;
	}

	@Override // 查询订单表t_order(未付款)
	public List<Orders> searchT_order() {
		List<Orders> list = orderd.searchT_order();
		return list;
	}
	
	@Override  // 查询订单表t_order(已付款)
	public List<Orders> searchT_orderIsPay() {
		List<Orders> list = orderd.searchT_orderIsPay();
		return list;
	}

	@Override // 查询订单详情表t_order_item
	public List<Product> searchT_order_item(List<Orders> listOrder) {
		List<Product> list = null;
		for (int i = 0; i < listOrder.size(); i++) {
			list = orderd.searchT_order_item(listOrder.get(i).getId());
		}
		return list;
	}

	@Override // 往订单表t_order插入数据
	public void addT_order(String total_price, String creat_time, String order_num, Integer address_id) {
		orderd.addT_order(total_price, creat_time, order_num, address_id);
	}

	@Override // 往订单详情表t_order_item插入数据
	public void addT_order_item(List<Product> pro, Integer orderId, String product_id) {
		String[] proId = product_id.split(",");
		for (int i = 0; i < proId.length; i++) {
			orderd.addT_order_item(pro.get(i).getAmount(), orderId, proId[i]);
		}
	}

	@Override // 根据订单创建时间获得订单id
	public Integer searchIdByTime(String time) {
		Integer id = orderd.searchIdByTime(time);
		return id;
	}

	@Override // 删除订单
	public void deleteOrder(Integer orderId) {
		orderd.deleteOrder(orderId);
	}

	@Override // 删除订单详情
	public void deleteOrderItem(Integer orderId) {
		orderd.deleteOrderItem(orderId);
	}

	@Override
	public void updateOrder(String orderNum) {
		orderd.updateOrder(orderNum);
	}

}
