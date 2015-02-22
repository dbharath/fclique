package net.fclique.crowler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.fclique.constants.Constants.SiteEnum;
import net.fclique.crowler.model.Product;
import net.fclique.crowler.model.Query;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by devesh.bharathan
 */
public class TradusScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(TradusScrapingService.class.getClassLoader().getResourceAsStream("tradus.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProductTitleFromElement(Element element) {
        String title = element.select("a").first().attr(properties.getProperty("tradus.product.title"));
        return title;
    }
    
    private String getProductCategoryNameFromElement(Element element) {
    	String categoryName = "";
    	try {
    		categoryName = element.select(properties.getProperty("tradus.product.categoryDiv")).first().text();
    		categoryName = categoryMapping.get(categoryName) != null ? categoryMapping.get(categoryName): "";
    	}catch(Exception e){
    		
    	}
        return categoryName;
    }

    private String getProductPriceFromElement(Element element) {
        String price = "";
        Element ele = element.select(properties.getProperty("tradus.product.priceHolder")).first();
        Element priceElement = ele.select(properties.getProperty("tradus.product.priceAttr")).first();
        if(priceElement != null){
        	price = priceElement.text();
        }else {
        	price = ele.select("span").last().text();        	
        }
        return price;
    }

    private String getProductImageUrlFromElement(Element element) {
        String img = "";
        try {
        Element imgEl = element.select(properties.getProperty("tradus.product.imageHolder")).first();
        img = imgEl.getElementsByAttribute(properties.getProperty("tradus.product.imageAttr")).first().attr(properties.getProperty("tradus.product.imageAttr"));
        }catch(Exception e){
        	
        }
        return img;
    }

    private String getProductLinkFromElement(Element element) {
        String link = element.select("a").first().attr("href");
        return "http://www.tradus.com" + link;
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
    	List<Product> productList = new ArrayList<Product>();
        try {
        	String url = "http://www.tradus.com/search?query=";
            url += query.getQueryString();
            url += "&page=" + query.getPageNumber();             
            loadProperties();
            Document document = Jsoup.connect(url).userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();

            Elements elements = document.select(properties.getProperty("tradus.product.holder"));

            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("tradus.fetchNumber"));
            }            
            for (Element element : elements) {
                if (count == maximumProductsToFetch) break;
                
                try {
	                Product product = new Product(
	                        getProductTitleFromElement(element), getProductPriceFromElement(element), 
	                        getProductImageUrlFromElement(element), getProductLinkFromElement(element),
	                        getProductCategoryNameFromElement(element)
	                );
	                product.setProductId(getProductIdFromElement(element));
	                product.setSiteName(SiteEnum.TRADUS.name());
	                productList.add(product);
	                count++;
                }catch(Exception e){
                	e.printStackTrace();
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    private String getProductIdFromElement(Element element) {
    	String productId = "";    	
    	productId = element.select("a").first().attr("data-nid");
        return productId;
    }
}
