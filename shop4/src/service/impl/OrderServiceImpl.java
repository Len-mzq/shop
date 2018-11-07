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

	@Override // ������Ʒid��ѯ��Ʒ��Ϣ
	public List<Product> searchByProduct_id(String orderId) {
		List<Product> list = orderd.searchByProduct_id(orderId);
		return list;
	}

	@Override // ���ݶ���id��ѯ��Ʒ��Ϣ
	public List<Product> searchByOrder_id(String product_id) {
		List<Product> list = orderd.searchByOrder_id(product_id);
		return list;
	}
	
	@Override // ��ѯʡ
	public List<Province> searchProvince() {
		List<Province> list = orderd.searchProvince();
		return list;
	}

	@Override // ��ѯ��
	public List<City> searchCityByProvince(Integer provinceId) {
		List<City> list = orderd.searchCityByProvince(provinceId);
		return list;
	}

	@Override // ��ѯ��
	public List<City> searchAreaByCity(Integer cityId) {
		List<City> list = orderd.searchAreaByCity(cityId);
		return list;
	}

	@Override // ������ַ
	public void addAddress(Integer cart_id, String street, String person, String phone, Integer areaId) {
		orderd.addAddress(cart_id, street, person, phone, areaId);
	}

	@Override // ɾ����ַ
	public void deleteAddress(Integer addressId) {
		orderd.deleteAddress(addressId);

	}

	@Override // ��ѯ��ַ
	public List<Address> searchAddress(Integer user_id) {
		List<Address> list = orderd.searchAddress(user_id);
		return list;
	}

	@Override // ��ѯ������t_order(δ����)
	public List<Orders> searchT_order() {
		List<Orders> list = orderd.searchT_order();
		return list;
	}
	
	@Override  // ��ѯ������t_order(�Ѹ���)
	public List<Orders> searchT_orderIsPay() {
		List<Orders> list = orderd.searchT_orderIsPay();
		return list;
	}

	@Override // ��ѯ���������t_order_item
	public List<Product> searchT_order_item(List<Orders> listOrder) {
		List<Product> list = null;
		for (int i = 0; i < listOrder.size(); i++) {
			list = orderd.searchT_order_item(listOrder.get(i).getId());
		}
		return list;
	}

	@Override // ��������t_order��������
	public void addT_order(String total_price, String creat_time, String order_num, Integer address_id) {
		orderd.addT_order(total_price, creat_time, order_num, address_id);
	}

	@Override // �����������t_order_item��������
	public void addT_order_item(List<Product> pro, Integer orderId, String product_id) {
		String[] proId = product_id.split(",");
		for (int i = 0; i < proId.length; i++) {
			orderd.addT_order_item(pro.get(i).getAmount(), orderId, proId[i]);
		}
	}

	@Override // ���ݶ�������ʱ���ö���id
	public Integer searchIdByTime(String time) {
		Integer id = orderd.searchIdByTime(time);
		return id;
	}

	@Override // ɾ������
	public void deleteOrder(Integer orderId) {
		orderd.deleteOrder(orderId);
	}

	@Override // ɾ����������
	public void deleteOrderItem(Integer orderId) {
		orderd.deleteOrderItem(orderId);
	}

	@Override
	public void updateOrder(String orderNum) {
		orderd.updateOrder(orderNum);
	}

}
