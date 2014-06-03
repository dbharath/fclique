package net.pcheck.crowler.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.pcheck.constants.Constants.SiteEnum;
import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.Query;
import net.pcheck.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vivek.gupta
 */
public class EbayScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(EbayScrapingService.class.getClassLoader().getResourceAsStream("ebay.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String getProductCategoryNameFromElement(Element element) {
    	String categoryName = "";
    	String url = getProductLinkFromElement(element);
    	try {
			URL linkUrl = new URL(url);
			Map<String, String> map = Utils.getQueryMap(linkUrl.getQuery());
			categoryName = map.get(properties.getProperty("ebay.categoryNamekey"));
			categoryName = categoryMapping.get(categoryName) != null ? categoryMapping.get(categoryName): "";			
		} catch (Exception e) {
			e.printStackTrace();
		}    	
        return categoryName;
    }
    
    private String getProductTitleFromElement(Element element) {
        Element titleElement = element.select(properties.getProperty("ebay.product.titleHolder")).first();
        String title = titleElement.text();
        return title;
    }

    private String getProductPriceFromElement(Element element) {
        String price = "";
        price = element.select(properties.getProperty("ebay.product.priceHolder")).text();
        return price;
    }

    private String getProductImageUrlFromElement(Element element) {
        String img = "";
        Element imgEl = element.select(properties.getProperty(("ebay.product.imageHolder"))).first();
        img = imgEl.select(properties.getProperty("ebay.product.img")).attr(properties.getProperty("ebay.product.imgAttr"));
        return img;
    }

    private String getProductLinkFromElement(Element element) {
        String link = "";
        Element titleElement = element.select(properties.getProperty("ebay.product.titleHolder")).first();
        link = titleElement.select(properties.getProperty("ebay.product.link")).attr(properties.getProperty("ebay.product.linkAttr"));
        return link;
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
        String url = "http://www.ebay.in/sch/i.html?_nkw=";
        url += query.getQueryString();
        url += "&LH_BIN=1";
        url += "&_pgn=" + query.getPageNumber();
        List<Product> productList = new ArrayList<Product>();
        loadProperties();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elements = document.select(properties.getProperty("ebay.product.holder"));
            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("ebay.fetchNumber"));
            }            
            for (Element element : elements) {
                if (count == maximumProductsToFetch) break;
                count++;
                try {
	                Product product = new Product(
	                        getProductTitleFromElement(element), getProductPriceFromElement(element),
	                        getProductImageUrlFromElement(element), getProductLinkFromElement(element),
	                        getProductCategoryNameFromElement(element)
	                );
	                product.setProductId(getProductIdFromElement(element));
	                product.setSiteName(SiteEnum.EBAY.name());
	                productList.add(product);
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
    	String liknUrl = getProductLinkFromElement(element);
    	String [] params = liknUrl.split("/");
    	if(params.length >= 6){
    		productId = params[5].substring(0, params[5].indexOf("?")); 
    	}
        return productId;
    }
}
