package net.fclique.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.fclique.modal.Promotion;
import net.fclique.service.PromotionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coupons")
public class CouponController {

	@Resource
	private PromotionService promotionService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model){	
		Pageable pageable = new PageRequest(0, 10);
		Page<Promotion> coupons = promotionService.findAll(pageable);		
		model.addAttribute("coupons", coupons);
		return "coupon/index";
    }
	
	@RequestMapping(value = "/listp", method = RequestMethod.GET)
	public String search(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<Promotion> coupons = null;

			/*List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}*/ 

			coupons = promotionService.findAll(pageable);			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("products", coupons);
			return "coupon/index";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
