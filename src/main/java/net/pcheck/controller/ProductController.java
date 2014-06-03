package net.pcheck.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.paxcel.uibean.ProductUIBean;
import net.pcheck.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Resource
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String startPage(Model model) {
		Pageable pageable = new PageRequest(0, 10);
		Page<ProductUIBean> products = null;

		/*List<FilterRequest> filters = new ArrayList<FilterRequest>();

		if (filter != null) {
			filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
		}*/

		products = productService.findAll(pageable);
		long total = products.getTotalElements();		
		model.addAttribute("products", products);
		return "product/products";
	}

	@RequestMapping(value = "/listp", method = RequestMethod.GET)
	public String search(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<ProductUIBean> products = null;

			/*List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}*/

			products = productService.findAll(pageable);
			long total = products.getTotalElements();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("products", products);
			return "product/products";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	


}
