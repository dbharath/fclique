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


public class ShoppingIndiaTimesScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {
        try {
            properties.load(ShoppingIndiaTimesScrapingService.class.getClassLoader().getResourceAsStream("shoppingindiatimes.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Product> runSiteRoutine(Query query) {
		loadProperties();
		String url = "http://shopping.indiatimes.com/mtkeywordsearch?SEARCH_STRING=";
	    url += query.getQueryString();
	    url += "&catalog=all";
	    System.out.println(url);
	    List<Product> productList = new ArrayList<Product>();
	    try {
            Document document = Jsoup.connect(url).timeout(100000).userAgent("Mozilla/5.0").get();
            Elements elements = document.select(properties.getProperty("shippingindiatimes.product.holderDiv"));

            int count = 0;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("shippingindiatimes.fetchNumber"));
            }
            for (Element element : elements) {
                if (count == maximumProductsToFetch) break;
                count++;
                String productImage = "http://shopping.indiatimes.com"+getProductImageUrlFromElement(element);
                String productLink = "http://shopping.indiatimes.com"+getProductLinkFromElement(element);
                Product product = new Product(
                        getProductTitleFromElement(element), getProductPriceFromElement(element), 
                        productImage, productLink,
                        getProductCategoryNameFromElement(element)
                );
                product.setProductId(getProductIdFromElement(element));
                product.setSiteName(SiteEnum.SHOPPINGINDIATIMES.name());
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
	}

	private String getProductCategoryNameFromElement(Element element) {
		String productLink = getProductLinkFromElement(element);
		String productCategoryName = productLink.split("/")[1];
		productCategoryName = categoryMapping.get(productCategoryName) != null ? categoryMapping.get(productCategoryName): "";
		return productCategoryName;
	}

	private String getProductImageUrlFromElement(Element element) {
		String image = element.select(properties.getProperty("shippingindiatimes.product.imageDiv")).attr("src");
        return image;
	}

	private String getProductPriceFromElement(Element element) {
		String price = element.select(properties.getProperty("shippingindiatimes.product.priceDiv")).select(properties.getProperty("shippingindiatimes.product.newPriceDiv")).first().text();
		price = price.replaceAll("\\` ", "");
		return price;
	}

	private String getProductLinkFromElement(Element element) {
		String productLink = element.select(properties.getProperty("shippingindiatimes.product.linkDiv")).attr("href");
        return productLink;
	}

	private String getProductTitleFromElement(Element element) {
		String title = element.select(properties.getProperty("shippingindiatimes.product.titleDiv")).first().text();
        return title;		
	}
	
    private String getProductIdFromElement(Element element) {
    	String productId = "";    	
    	String productLink = getProductLinkFromElement(element);
		productId = productLink.split("/")[5];
        return productId;
    }


}
