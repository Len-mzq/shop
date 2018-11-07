package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.Cart;
import entity.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userd;

	@Override // ��ѯ�û��Ƿ��Ѿ�����
	public User searchAll(String username, String password) {
		User user = userd.searchAll(username, password);
		return user;
	}

	@Override // ��ѯ�绰���Ƿ��Ѿ�����
	public boolean searchPhone(String phone) {
		Integer rs = userd.searchPhone(phone);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}

	}

	@Override // ��ѯ�ǳ��Ƿ��Ѿ�����
	public boolean searchNickName(String nickname) {
		Integer rs = userd.searchNickName(nickname);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}
	}

	@Override // ��ѯ�û����Ƿ��Ѿ�����
	public boolean searchUserName(String username) {
		Integer rs = userd.searchUserName(username);
		if (rs == null) {
			return false;
		} else {
			return rs > 0;
		}
	}

	@Override // ��ѯ�û�ע��ʱ��
	public String searchTime(String username) {
		String time = userd.searchTime(username);
		return time;
	}

	@Override // �û�ע��
	public boolean add(String phone, String nickname, String username, String password,int cart_id) {
		int rs = userd.add(phone, nickname, username, password,cart_id);
		return rs > 0;
	}

	@Override
	public void creatCart(Cart c) {
		userd.creatCart(c);
	}

}
