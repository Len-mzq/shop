package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Img;
import entity.Product;

public interface ProductDao {

	// ����������ѯ���ݿ�t_product���е����ݣ���ҳ.
	public List<Product> searchByCondition(@Param(value = "pro") Product product,
			@Param(value = "startIndex") int startIndex, @Param(value = "pageSize") int pageSize);
	
	// ����id��ѯĳһ����Ʒ������.
	public Product searchById(Integer id);
	
	// ����ids��ѯ������Ʒ������.
	public List<Product> searchByIds(Integer cart_id);
	
	// ������Ʒid��ѯ��ƷͼƬ
	public Img searchImg(Integer id);
	
	// �����Ʒ.
	public int add(Product pro);

	// ɾ����Ʒ.
	public int deleteBatch(String[] id);

	// �޸���Ʒ��Ϣ.
	public int update(Product pro);

	// ��ȡ��������ѯ������Ʒ����.
	public int getSearchCount(Product pro);
}
