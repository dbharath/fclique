package net.pcheck.crowler.service;

import java.io.IOException;
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
public class AmazonScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(AmazonScrapingService.class.getClassLoader().getResourceAsStream("amazon.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProductTitleFromElement(Element element) {
        String title = element.select(properties.getProperty("amazon.product.titleSpan")).first().text();
        return title;
    }

    private String getProductCategoryNameFromElement(Element element) {
    	String categoryName = "";
        return categoryName;
    }

    
    private String getProductPriceFromElement(Element element) {
        String price = "";
        if (element.select(properties.getProperty("amazon.product.priceSpan")).first() != null) {
            price = element.select(properties.getProperty("amazon.product.priceSpan")).first().text();

        } else if (element.select(properties.getProperty("amazon.product.altPriceSpan")).first() != null)
            price = element.select(properties.getProperty("amazon.product.altPriceSpan")).first().text();

        return price;
    }

    private String getProductImageUrlFromElement(Element element) {
        String img = element.select("img").first().attr(properties.getProperty("amazon.product.imageAttrName"));
        return img;
    }

    private String getProductLinkFromElement(Element element) {
        String link = element.select("a").first().attr("href");
        return link;
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {

        String url = "http://www.amazon.in/s?field-keywords=";
        url += query.getQueryString();
        url += "&page=" + query.getPageNumber();
        System.out.println(url);
        List<Product> productList = new ArrayList<Product>();

        loadProperties();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            Elements elements = document.select(properties.getProperty("amazon.product.holderDiv"));

            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("amazon.fetchNumber"));
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
	                product.setSiteName(SiteEnum.AMAZON.name());
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
    	String productId = element.attr("name");
        return productId;
    }
}
