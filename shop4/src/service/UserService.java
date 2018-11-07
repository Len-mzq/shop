package service;

import entity.Cart;
import entity.User;

public interface UserService {

	// ��ѯ�û��Ƿ��Ѿ�����
	public User searchAll(String username, String password);

	// ��ѯ�绰���Ƿ��Ѿ�����
	public boolean searchPhone(String phone);

	// ��ѯ�ǳ��Ƿ��Ѿ�����
	public boolean searchNickName(String nickname);

	// ��ѯ�û����Ƿ��Ѿ�����
	public boolean searchUserName(String username);

	// ��ѯ�û�ע��ʱ��
	public String searchTime(String username);

	// ����һ���µĹ��ﳵ
	public void creatCart(Cart c);

	// �û�ע��
	public boolean add(String phone, String nickname, String username, String password,int cart_id);

}
