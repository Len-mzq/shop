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

	@Override // �����ﳵ����Ʒ�Ĺ�ϵ�����������
	public void add(Integer cart_id, Integer product_id, Integer amount) {
		cartd.add(cart_id, product_id, amount);
	}

	@Override // ��ѯ���ﳵ����Ʒ�Ĺ�ϵ������Ʒ������
	public Integer searchAmount(Integer cart_id, Integer product_id) {
		Integer count = cartd.searchAmount(cart_id, product_id);
		return count;
	}

	@Override // ���¹��ﳵ����Ʒ�Ĺ�ϵ��������
	public void updatePro_cart(Integer cart_id, Integer product_id, Integer amount, Integer amount0) {
		cartd.updatePro_cart(cart_id, product_id, amount, amount0);
	}
	
	@Override // ���¹��ﳵ����Ʒ�Ĺ�ϵ��������(+/-)
	public void updatePro_cart2(Integer cart_id, Integer product_id, Integer amount) {
		cartd.updatePro_cart2(cart_id, product_id, amount);
	}

	@Override  // ɾ�����ﳵ����Ʒ�Ĺ�ϵ��������
	public void deletePro_cart(Integer cart_id, Integer product_id) {
		cartd.deletePro_cart(cart_id, product_id);
	}

	@Override // ����Ʒ��ӵ����ﳵ
	public void addToCart(Integer amount, String price) {
		cartd.addToCart(amount, price);
	}

	@Override // ��ѯ���ﳵ����Ʒ������
	public Integer searchAmountInCart(Integer cart_id) {
		Integer count = cartd.searchAmountInCart(cart_id);
		return count;
	}

	@Override // ��ѯ���ﳵ����Ʒ������
	public Integer searchPriceInCart(Integer cart_id) {
		Integer price = cartd.searchPriceInCart(cart_id);
		return price;
	}

	@Override // ��ѯ���ﳵ����Ʒ����Ϣ
	public Cart searchAllInCart(Integer cart_id) {
		Cart cart = cartd.searchAllInCart(cart_id);
		return cart;
	}

	@Override // ���¹��ﳵ
	public void updateCart(Integer amount, String price, Integer cart_id, Integer amount_cart, double price_cart) {
		cartd.updateCart(amount, price, cart_id, amount_cart, price_cart);
	}

	@Override // �����û������û�id
	public Integer searchUserId(String username) {
		Integer id = cartd.searchUserId(username);
		return id;
	}

	@Override // �����û������û�cart_id
	public Integer searchCart_id(String username) {
		Integer cart_id = cartd.searchCart_id(username);
		return cart_id;
	}

	@Override // ��ѯcart_id�Ƿ��Ѿ�����
	public boolean searchCart_idExit(Integer cart_id) {
		Integer rs = cartd.searchCart_idExit(cart_id);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}
	}

	@Override // ��ѯ���ﳵ����Ʒ�Ƿ��Ѿ�����
	public boolean searchPro_idExit(Integer pro_id, Integer cart_id) {
		Integer rs = cartd.searchPro_idExit(pro_id, cart_id);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}

	}

}
