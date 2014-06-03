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
public class HomeShop18ScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(HomeShop18ScrapingService.class.getClassLoader().getResourceAsStream("homeshop18.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProductTitleFromElement(Element element) {
        String title = "";
        Element titleElement = element.getElementsByClass(properties.getProperty("homeshop18.product.titleDiv")).first();
        title = titleElement.getElementsByTag("a").first().text();
        return title;
    }

    private String getProductPriceFromElement(Element element) {
        String price = "";        
        if (element.select(properties.getProperty("homeshop18.product.priceDiv")).first() != null) {
            if (element.select(properties.getProperty("homeshop18.product.priceDiv")).first().getElementsByTag("b").isEmpty() == false) {
                if (element.select(properties.getProperty("homeshop18.product.priceDiv")).first().getElementsByTag("b").first().textNodes().isEmpty() == false)
                    price = element.select(properties.getProperty("homeshop18.product.priceDiv")).first().getElementsByTag("b").first().textNodes().get(0).text();
            }
        }
        return price;
    }

    private String getProductImageUrlFromElement(Element element) {
        String img = "";
        if (element.getElementsByClass(properties.getProperty("homeshop18.product.imageDiv")) != null) {
            Element imageElement = element.getElementsByClass(properties.getProperty("homeshop18.product.imageDiv")).first();
            img = imageElement.getElementsByTag("img").attr("data-original");
        }
        /*"http://www.homeshop18.com" +  */
        return "http://"+img.split("//")[1];  
    }

    private String getProductLinkFromElement(Element element) {
        String link = "";
        Element linkElement = element.getElementsByClass(properties.getProperty("homeshop18.product.titleDiv")).first();
        if (linkElement != null) {
            link = linkElement.getElementsByTag("a").first().attr("href");
        }
        return link;
    }

    public List<Product> runSiteRoutine(Query query) {
        loadProperties();
        List<Product> productList = new ArrayList<Product>();
           
        try {
        	String productName = query.getQueryString();
            String url = "http://www.homeshop18.com/" + productName + "/search:" + productName;
            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("homeshop18.fetchNumber"));
            }     
            System.out.println(url);
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.title-2.clearfix");
            if (elements.isEmpty() != true) {
                Element maxElement = null;
                int maxResults = 0;
                for (Element element : elements) {
                    Element anchorElement = element.getElementsByTag("a").first();
                    if (anchorElement != null) {

                        String result = anchorElement.select("span").first().text();
                        result = result.substring(1, result.length() - 1);
                        if (Integer.parseInt(result) > maxResults) {
                            maxResults = Integer.parseInt(result);
                            maxElement = anchorElement;
                        }
                    }
                }
                if (maxElement != null) {
                    String href = maxElement.attr("href");
                    String category = href.split("/")[2];
                    int page = query.getPageNumber();
                    page = (page - 1) * 24;
                    String finalUrl = "http://www.homeshop18.com/" + productName + "/" + category + "/search:" + productName + "/start:" + page + "/?lazy=true";
                    Document document1 = Jsoup.connect(finalUrl).get();
                    Elements elements1 = document1.select(properties.getProperty("homeshop18.product.holder"));
                    count = 0;
                    for (Element element : elements1) {
                        if (count == maximumProductsToFetch) break;
                        count++;
                        String productLink = "http://www.homeshop18.com"+getProductLinkFromElement(element);
                        Product product = new Product(getProductTitleFromElement(element), getProductPriceFromElement(element), 
                        		getProductImageUrlFromElement(element), productLink,
                        		getProductCategoryNameFromElement(element)
                        );
                        product.setProductId(getProductIdFromElement(element));
                        product.setSiteName(SiteEnum.HOMESHOP18.name());
                        if (product != null) productList.add(product);
                    }
                }
            } else if (document.getElementById("searchResultsDiv") != null) {
                System.out.println("here");
                Element linkElement = document.getElementsByClass("sort_browse").first();
                if (linkElement != null) {
                    linkElement = linkElement.getElementsByTag("a").first();
                    String category = linkElement.attr("href").split("/")[2];
                    int page = query.getPageNumber();
                    page = (page - 1) * 24;
                    String finalUrl = "http://www.homeshop18.com/" + productName + "/" + category + "/search:" + productName + "/start:" + page + "/?lazy=true";
                    Document document1 = Jsoup.connect(finalUrl).get();
                    Elements elements1 = document1.select(properties.getProperty("homeshop18.product.holder"));
                    count = 0;
                    for (Element element : elements1) {
                        if (count == maximumProductsToFetch) break;
                        count++;
                        String productLink = "http://www.homeshop18.com"+getProductLinkFromElement(element);
                        Product product = new Product(
                        		getProductTitleFromElement(element), getProductPriceFromElement(element), 
                        		getProductImageUrlFromElement(element), productLink,
                        		getProductCategoryNameFromElement(element)
                        	);
                        product.setSiteName(SiteEnum.HOMESHOP18.name());
                        product.setProductId(getProductIdFromElement(element));
                        if (product != null) productList.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }
    
    private String getProductCategoryNameFromElement(Element element) {
    	String categoryName = "";
    	String liknUrl = getProductLinkFromElement(element);
    	String [] params = liknUrl.split("/");
    	if(params.length >= 4){
    		categoryName = params[3]; 
    	}
    	categoryName = categoryMapping.get(categoryName) != null ? categoryMapping.get(categoryName): "";
        return categoryName;
    }
    
    private String getProductIdFromElement(Element element) {
    	String productId = "";
    	String liknUrl = getProductLinkFromElement(element);
    	String [] params = liknUrl.split("/");
    	for (String string : params) {
			if(string.startsWith("product:")){
				productId = string.split(":")[1];
			}
		}
    	
        return productId;
    }
}
