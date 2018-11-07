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

	@Override // 根据条件查询数据库t_product表中的商品，分页.
	public List<Product> searchByCondition(Product product, int startIndex, int pageSize) {
		List<Product> list = prod.searchByCondition(product, startIndex, pageSize);
		return list;
	}

	@Override // 根据id查询某一件商品的详情.
	public Product searchById(Integer id) {
		Product pro = prod.searchById(id);
		return pro;
	}

	@Override // 添加商品.
	public int add(Product pro) {
		prod.add(pro);
		return 0;
	}

	@Override // 删除商品.
	public int deleteBatch(String[] id) {
		prod.deleteBatch(id);
		return 0;
	}

	@Override // 修改商品信息.
	public int update(Product pro) {
		prod.update(pro);
		return 0;
	}

	@Override // 获取按条件查询到的商品总数.
	public int getSearchCount(Product pro) {
		int count = prod.getSearchCount(pro);
		return count;
	}

	@Override // 根据商品id查询商品图片
	public Img searchImg(Integer id) {
		Img img = prod.searchImg(id);
		return img;
	}

	@Override  // 根据ids查询所有商品的详情.
	public List<Product> searchByIds(Integer cart_id) {
		List<Product> list = prod.searchByIds(cart_id);
		return list;
	}

}
