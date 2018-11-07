package service;

import entity.Cart;

public interface CartService {
	// �����ﳵ����Ʒ�Ĺ�ϵ�����������
	public void add(Integer cart_id, Integer product_id, Integer amount);

	// ��ѯ���ﳵ����Ʒ�Ĺ�ϵ������Ʒ������
	public Integer searchAmount(Integer cart_id, Integer product_id);

	// ���¹��ﳵ����Ʒ�Ĺ�ϵ��������
	public void updatePro_cart(Integer cart_id, Integer product_id, Integer amount, Integer amount0);

	// ���¹��ﳵ����Ʒ�Ĺ�ϵ��������(+/-)
	public void updatePro_cart2(Integer cart_id, Integer product_id, Integer amount);
	
	// ɾ�����ﳵ����Ʒ�Ĺ�ϵ��������
	public void deletePro_cart(Integer cart_id, Integer product_id);

	// ����Ʒ��ӵ����ﳵ
	public void addToCart(Integer amount, String price);

	// ��ѯ���ﳵ����Ʒ������
	public Integer searchAmountInCart(Integer cart_id);

	// ��ѯ���ﳵ����Ʒ�ļ۸�
	public Integer searchPriceInCart(Integer cart_id);

	// ��ѯ���ﳵ����Ʒ����Ϣ
	public Cart searchAllInCart(Integer cart_id);

	// ���¹��ﳵ
	public void updateCart(Integer amount, String price, Integer cart_id, Integer amount_cart, double price_cart);

	// �����û������û�id
	public Integer searchUserId(String username);

	// �����û������û�cart_id
	public Integer searchCart_id(String username);

	// ��ѯcart_id�Ƿ��Ѿ�����
	public boolean searchCart_idExit(Integer cart_id);

	// ��ѯ���ﳵ����Ʒ�Ƿ��Ѿ�����
	public boolean searchPro_idExit(Integer pro_id, Integer cart_id);

}
