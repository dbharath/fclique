package net.fclique.controller;

import java.util.List;

import net.fclique.crowler.model.Query;
import net.fclique.crowler.model.ResponseAccumulator;
import net.fclique.crowler.model.SiteResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/product")
public class ProductSearchController {
	
	 @RequestMapping(value = "/search", method = RequestMethod.GET)	
	 public String products(@RequestParam(value = "q")String productName, 
	    		 @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
	    		 @RequestParam(value = "maxProductToFetch", defaultValue = "50") Integer maxProductToFetch,Model model)   {
		 if(maxProductToFetch == 0 || maxProductToFetch == null){
			 maxProductToFetch = 50;
		 }
		 if(pageNum == 0 || pageNum == null){
			 pageNum = 1;
		 }
		 Query currentQuery = new Query(productName, pageNum, maxProductToFetch);
	     List<SiteResponse> siteResponses = ResponseAccumulator.getResponseAccumulator().getSiteResponses(currentQuery);
	     model.addAttribute("siteResponses", siteResponses);
	     return "/products"; 	        
	 }

}
