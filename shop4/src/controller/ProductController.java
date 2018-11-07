package controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Product;
import service.ProductService;
import vo.PageVo;

@Controller
public class ProductController {
	@Autowired
	ProductService service;

	@RequestMapping(value = "deletePicture")
	public void deletePicture(String fileName) {
		File srcFolder = new File("d:/tu");
		File[] fileArray = srcFolder.listFiles();
		if (fileArray != null) {
			// 遍历该File数组，得到每一个File对象
			for (File file : fileArray) {
				if (file.getName().equals(fileName)) {
					file.delete();
				}
			}
		}
	}

	@RequestMapping(value = "search")
	public ModelAndView search(Product pro, Integer pageNo) {
		ModelAndView mv = new ModelAndView("list/list");
		if (pageNo == null) {
			pageNo = 1;
		}
		// 如果当前页数小于1，默认显示第一页
		if (pageNo != null && pageNo < 1) {
			pageNo = 1;
		}
		int count = service.getSearchCount(pro); // 获取商品总数
		PageVo<Product> pagevo = new PageVo<Product>();
		pagevo.setTotalRecords(count);// 填充到pagevo中
		if (count != 0) {
			// 如果当前页数大于总页数，默认显示最后一页
			if (pageNo != null && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
		}
		pagevo.setPageNo(pageNo);
		List<Product> list = service.searchByCondition(pro, pagevo.getStartIndex(), pagevo.getPageSize());
		pagevo.setRecords(list);
		pagevo.setUrl("product?");// 返回页面
		mv.addObject("pro", pro);
		mv.addObject("pagevo", pagevo);
		return mv;
	}
	
	@RequestMapping(value = "showDetails")
	public ModelAndView showDetails(Integer id) {
		ModelAndView mv = new ModelAndView("list/details");
		Product pro= service.searchById(id);
		mv.addObject("pro", pro);
		return mv;
	}
}
