package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Address;
import entity.City;
import entity.Orders;
import entity.Product;
import entity.Province;

public interface OrderDao {

	// ������Ʒid��ѯ��Ʒ��Ϣ
	public List<Product> searchByProduct_id(@Param(value = "product_id") String product_id);
	
	// ���ݶ���id��ѯ��Ʒ��Ϣ
	public List<Product> searchByOrder_id(@Param(value = "orderId") String orderId);

	// ��ѯ��ַ
	public List<Address> searchAddress(Integer user_id);

	// ��ѯʡ
	public List<Province> searchProvince();

	// ��ѯ��
	public List<City> searchCityByProvince(Integer provinceId);

	// ��ѯ��
	public List<City> searchAreaByCity(Integer cityId);

	// ������ַ
	public void addAddress(Integer cart_id, String street, String person, String phone, Integer areaId);

	// ɾ����ַ
	public void deleteAddress(Integer addressId);

	// ��������t_order��������
	public void addT_order(String total_price, String creat_time, String order_num, Integer address_id);

	// �����������t_order_item��������
	public void addT_order_item(Integer amount, Integer orderId, String product_id);

	// ��ѯ������t_order(δ����)
	public List<Orders> searchT_order();

	// ��ѯ������t_order(�Ѹ���)
	public List<Orders> searchT_orderIsPay();

	// ��ѯ���������t_order_item
	public List<Product> searchT_order_item(Integer order_id);

	// ���ݶ�������ʱ���ö���id
	public Integer searchIdByTime(String time);

	// ɾ������
	public void deleteOrder(Integer orderId);

	// ɾ����������
	public void deleteOrderItem(Integer orderId);
	
	// �޸Ķ���״̬��֧����
	public void updateOrder(String orderNum);
	
}
