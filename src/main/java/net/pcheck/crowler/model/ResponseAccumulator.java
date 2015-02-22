package net.pcheck.crowler.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.pcheck.constants.Constants;
import net.pcheck.constants.Constants.SearchType;
import net.pcheck.constants.Constants.SiteEnum;
import net.pcheck.crowler.service.ServiceFactory;
import net.pcheck.utils.ListUtil;

/**
 * Created by devesh.bharathan
 */
public class ResponseAccumulator {
    private List<SiteResponse> responses;
    private static ResponseAccumulator responseAccumulator;

    private ResponseAccumulator() {
    }

    public static ResponseAccumulator getResponseAccumulator() {
        if (responseAccumulator == null) {
            responseAccumulator = new ResponseAccumulator();
        }
        return responseAccumulator;
    }

    public List<SiteResponse> getSiteResponses(Query query) {
        this.responses = new ArrayList<SiteResponse>();
        this.responses.add(new SiteResponse("FlipkartResponse", ServiceFactory.getFlipkartScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("AmazonResponse", ServiceFactory.getAmazonScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("ShoppingIndiaTimesResponse", ServiceFactory.getShoppingIndiaTimesScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("TradusResponse", ServiceFactory.getTradusScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("SnapdealResponse", ServiceFactory.getSnapdealScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("EbayResponse", ServiceFactory.getEbayScrapingService().getProductsForPage(query)));        
        this.responses.add(new SiteResponse("HomeShop18Response", ServiceFactory.getHomeShop18ScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("InfibeamResponse", ServiceFactory.getInfibeamScrapingService().getProductsForPage(query)));
        this.responses.add(new SiteResponse("NaaptolResponse", ServiceFactory.getNaaptolScrapingService().getProductsForPage(query)));       
        /*this.responses.add(new SiteResponse("AutoSuggest", new AutoSuggestion(this.responses).getSuggestedList()));*/
        return this.responses;
    }
    
    public List<Product> getProductsByProductIdAndSite(Constants.SiteEnum site, Query query) {
    	List<Product> products = new ArrayList<Product>();
    	if(site.equals(SiteEnum.AMAZON)){    		
    		products = ServiceFactory.getAmazonScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.FLIPKART)){
    		query.setSearchType(SearchType.ByProductId);
    		products = ServiceFactory.getFlipkartScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.EBAY)){
    		products = ServiceFactory.getFlipkartScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.HOMESHOP18)){
    		products = ServiceFactory.getFlipkartScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.TRADUS)){
    		products = ServiceFactory.getFlipkartScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.SNAPDEAL)){
    		products = ServiceFactory.getSnapdealScrapingService().getProductsForPage(query);
    	}     	
    	return products;
    }
    
    public List<Product> getProductsByProductNameAndSite(Constants.SiteEnum site, Query query) {
    	List<Product> products = new ArrayList<Product>();
    	if(site.equals(SiteEnum.AMAZON)){    		
    		products = ServiceFactory.getAmazonScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.FLIPKART)){    		
    		products = ServiceFactory.getFlipkartScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.EBAY)){
    		products = ServiceFactory.getEbayScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.HOMESHOP18)){
    		products = ServiceFactory.getHomeShop18ScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.TRADUS)){
    		products = ServiceFactory.getTradusScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.SHOPPINGINDIATIMES)){
    		products = ServiceFactory.getShoppingIndiaTimesScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.NAAPTOL)){
    		products = ServiceFactory.getNaaptolScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.INFOBEAM)){
    		products = ServiceFactory.getInfibeamScrapingService().getProductsForPage(query);
    	}else if(site.equals(SiteEnum.SNAPDEAL)){
    		products = ServiceFactory.getSnapdealScrapingService().getProductsForPage(query);
    	}        	
    	return products;
    }
    
    
    public Map<String, List<Product>> getGroupedProducts(Query query) {
    	this.responses = getSiteResponses(query);
    	List<Product> products = getProductList(responses);
    	Collections.sort(products, Product.categoryNameThenPriceComparator);
    	return ListUtil.group(products, "productCategoryName");
    }
    
    public List<Product> getProductList(List<SiteResponse> siteResponses) {
    	List<Product> products = new ArrayList<Product>();
    	for(int i=0; i < siteResponses.size(); i++) {
            SiteResponse currentResponse = siteResponses.get(i);
            List<Product> currentProducts = currentResponse.getProducts();     
            products.addAll(currentProducts);
        }
    	return products;
    }
}
