package dao;

import org.apache.ibatis.annotations.Param;

import entity.Cart;
import entity.User;

public interface UserDao {

	// ��ѯ�û��Ƿ��Ѿ�����
	public User searchAll(String username, String password);

	// ��ѯ�绰���Ƿ��Ѿ�����
	public Integer searchPhone(String phone);

	// ��ѯ�ǳ��Ƿ��Ѿ�����
	public Integer searchNickName(String nickname);

	// ��ѯ�û����Ƿ��Ѿ�����
	public Integer searchUserName(String username);

	// ��ѯ�û�ע��ʱ��
	public String searchTime(String username);

	// ����һ���µĹ��ﳵ
	public void creatCart(Cart c);

	// �û�ע��
	public int add(@Param(value = "phone") String phone, @Param(value = "nickname") String nickname,
			@Param(value = "username") String username, @Param(value = "password") String password,
			@Param(value = "cart_id") int cart_id);

}
