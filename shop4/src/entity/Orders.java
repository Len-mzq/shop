package entity;

import java.util.List;

public class Orders {
	private Integer id;
	private String total_price;// �ܼ�
	private String create_time;// ����ʱ��
	private Integer address_id;
	private String order_num;// ������
	//private Integer count;// ÿ����Ʒ������
	//private Integer product_id;// ��Ʒid
	private List<OrdersItem> listOrderItem;

	public List<OrdersItem> getListOrderItem() {
		return listOrderItem;
	}

	public void setListOrderItem(List<OrdersItem> listOrderItem) {
		this.listOrderItem = listOrderItem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

}
