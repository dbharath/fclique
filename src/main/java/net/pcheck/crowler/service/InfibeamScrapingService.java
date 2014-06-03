package net.pcheck.crowler.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.pcheck.constants.Constants.SiteEnum;
import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.Query;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vivek.gupta
 */
public class InfibeamScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(InfibeamScrapingService.class.getClassLoader().getResourceAsStream("infibeam.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProductTitleFromElement(Element element) {
        String title = element.getElementsByClass(properties.getProperty("infibeam.product.title")).first().text();
        return title;
    }

    private String getProductPriceFromElement(Element element) {
        String price = "";
        Element priceElement = element.select(properties.getProperty("infibeam.product.priceDiv")).first();
        if (priceElement.select(properties.getProperty("infibeam.product.priceSpan")).first() != null) {
            price = priceElement.select(properties.getProperty("infibeam.product.priceSpan")).first().text();
        } else {
            price = priceElement.select(properties.getProperty("infibeam.product.priceAlt")).first().text();
        }

        return price;
    }

    private String getProductImageUrlFromElement(Element element) {
        String img = element.getElementsByTag(properties.getProperty("infibeam.product.image")).first().attr(properties.getProperty("infibeam.product.imageAttr"));
        return img;
    }

    private String getProductLinkFromElement(Element element) {
        String link = element.select(properties.getProperty("infibeam.product.link")).first().attr(properties.getProperty("infibeam.product.linkAttr"));
        return "http://www.infibeam.com" + link;
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
        String url = "http://www.infibeam.com/search?q=";
        // url+=productName;
        url += URLEncoder.encode(query.getQueryString());
        url += "&page=" + query.getPageNumber();
        List<Product> productList = new ArrayList<Product>();
        loadProperties();
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome").get();
            Elements elements = document.select(properties.getProperty("infibeam.product.holderUl"));
            Elements elements1 = new Elements();
            for (Element e : elements) {
                elements1.addAll(e.select(properties.getProperty("infibeam.product.holderLi")));
            }
            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("infibeam.fetchNumber"));
            }
            for (Element element : elements1) {
                if (count == maximumProductsToFetch) break;
                count++;
                try {
	                Product product = new Product(
	                        getProductTitleFromElement(element), getProductPriceFromElement(element), 
	                        getProductImageUrlFromElement(element), getProductLinkFromElement(element),
	                        getProductCategoryNameFromElement(element)
	                );
	                
	                product.setProductId(getProductIdFromElement(element));
	                product.setSiteName(SiteEnum.INFOBEAM.name());
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
    
   
    private String getProductCategoryNameFromElement(Element element) {    	
    	String categoryName = "";    	
    	categoryName = getProductLinkFromElement(element).split("/")[3];
    	categoryName = categoryMapping.get(categoryName) != null ? categoryMapping.get(categoryName): "";
        return categoryName;
    }
    private String getProductIdFromElement(Element element) {
    	String productId = "";    	
        return productId;
    }
}
