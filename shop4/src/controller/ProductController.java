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
			// ������File���飬�õ�ÿһ��File����
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
		// �����ǰҳ��С��1��Ĭ����ʾ��һҳ
		if (pageNo != null && pageNo < 1) {
			pageNo = 1;
		}
		int count = service.getSearchCount(pro); // ��ȡ��Ʒ����
		PageVo<Product> pagevo = new PageVo<Product>();
		pagevo.setTotalRecords(count);// ��䵽pagevo��
		if (count != 0) {
			// �����ǰҳ��������ҳ����Ĭ����ʾ���һҳ
			if (pageNo != null && pageNo > pagevo.getTotalPageSize()) {
				pageNo = pagevo.getTotalPageSize();
			}
		}
		pagevo.setPageNo(pageNo);
		List<Product> list = service.searchByCondition(pro, pagevo.getStartIndex(), pagevo.getPageSize());
		pagevo.setRecords(list);
		pagevo.setUrl("product?");// ����ҳ��
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
