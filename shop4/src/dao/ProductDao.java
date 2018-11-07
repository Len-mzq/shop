package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Img;
import entity.Product;

public interface ProductDao {

	// 根据条件查询数据库t_product表中的内容，分页.
	public List<Product> searchByCondition(@Param(value = "pro") Product product,
			@Param(value = "startIndex") int startIndex, @Param(value = "pageSize") int pageSize);
	
	// 根据id查询某一件商品的详情.
	public Product searchById(Integer id);
	
	// 根据ids查询所有商品的详情.
	public List<Product> searchByIds(Integer cart_id);
	
	// 根据商品id查询商品图片
	public Img searchImg(Integer id);
	
	// 添加商品.
	public int add(Product pro);

	// 删除商品.
	public int deleteBatch(String[] id);

	// 修改商品信息.
	public int update(Product pro);

	// 获取按条件查询到的商品总数.
	public int getSearchCount(Product pro);
}
