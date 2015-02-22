package net.fclique.controller;

import java.util.List;
import javax.annotation.Resource;
import net.fclique.modal.Promotion;
import net.fclique.service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/promotion")
public class PromotionController {
	@Resource
	private PromotionService promotionService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView shopListPage() {
		ModelAndView mav = new ModelAndView("shop-list");
		List<Promotion> shopList = promotionService.findAll();
		mav.addObject("shopList", shopList);
		return mav;
	}

}
