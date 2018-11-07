package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDao;
import entity.Img;
import entity.Product;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao prod;

	@Override // ����������ѯ���ݿ�t_product���е���Ʒ����ҳ.
	public List<Product> searchByCondition(Product product, int startIndex, int pageSize) {
		List<Product> list = prod.searchByCondition(product, startIndex, pageSize);
		return list;
	}

	@Override // ����id��ѯĳһ����Ʒ������.
	public Product searchById(Integer id) {
		Product pro = prod.searchById(id);
		return pro;
	}

	@Override // �����Ʒ.
	public int add(Product pro) {
		prod.add(pro);
		return 0;
	}

	@Override // ɾ����Ʒ.
	public int deleteBatch(String[] id) {
		prod.deleteBatch(id);
		return 0;
	}

	@Override // �޸���Ʒ��Ϣ.
	public int update(Product pro) {
		prod.update(pro);
		return 0;
	}

	@Override // ��ȡ��������ѯ������Ʒ����.
	public int getSearchCount(Product pro) {
		int count = prod.getSearchCount(pro);
		return count;
	}

	@Override // ������Ʒid��ѯ��ƷͼƬ
	public Img searchImg(Integer id) {
		Img img = prod.searchImg(id);
		return img;
	}

	@Override  // ����ids��ѯ������Ʒ������.
	public List<Product> searchByIds(Integer cart_id) {
		List<Product> list = prod.searchByIds(cart_id);
		return list;
	}

}
