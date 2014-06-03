package net.pcheck.crowler.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.pcheck.constants.Constants;
import net.pcheck.constants.Constants.SiteEnum;
import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.Query;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vivek.gupta
 */
public class FlipkartScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private String getProductTitleFromElement(Element element) {
        return element.select(properties.getProperty("flipkart.product.displayBlock")).first().attr("title");
    }

    private String getProductImageUrlFromElement(Element element) {
        String imgUrl = element.select("img").first().attr(properties.getProperty("flipkart.product.imgAttrName"));
        if (imgUrl == "")
            imgUrl = element.select("img").first().attr(properties.getProperty("flipkart.product.imgAttrNameIfNot"));
        return imgUrl;
    }
    
    private String getProductCategoryNameFromElement(Element element) {
    	String productCategoryName = "";
    	productCategoryName = element.select(properties.getProperty("flipkart.product.categoryDiv")).first().text();
    	productCategoryName = categoryMapping.get(productCategoryName) != null ? categoryMapping.get(productCategoryName): "";
        return productCategoryName;
    }

    
    
    private String getProductLinkFromElement(Element element) {
        return "http://www.flipkart.com" + element.select(properties.getProperty("flipkart.product.displayBlock")).first().attr("href");
    }

    private String getProductPriceFromElement(Element element) {
        return element.select(properties.getProperty("flipkart.product.prodPriceAttrName")).text();
    }

    private void loadProperties() {
        try {
            properties.load(FlipkartScrapingService.class.getClassLoader().getResourceAsStream("flipkart.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
        String url = "http://www.flipkart.com/search?q=";
        url += query.getQueryString();
        url += "&start=";
        int page = query.getPageNumber();
        page = 1 + (page - 1) * 20;
        url += page;
        loadProperties();
        List<Product> productList = new ArrayList<Product>();
        if(query.getSearchType().equals(Constants.SearchType.ByProductId)){
        	productList.add(searchProductById(url, query));
        }else {
        	productList = searchProductByName(url, query);
        }
        return productList;
    }
    
    private String getProductIdFromElement(Element element) {
    	String productId = "";    	
    	productId = element.attr("data-pid");
        return productId;
    }

    //public abstract Product runSiteDetailPageRoutine(Query query);
    
    public Product searchProductById(String url, Query query){
    	Product product = null;
    	Document document = null;
    	try {
    		
    		Response response = Jsoup.connect(url.replaceAll(" ", "%20")).followRedirects(false).execute();
            int status = response.statusCode();
            
            if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM 
            		|| status == HttpURLConnection.HTTP_SEE_OTHER){        
                String redirectUrl = response.header("location");
                System.out.println("Redirect to: " + redirectUrl);
                url = response.url().getProtocol()+"://"+response.url().getHost()+redirectUrl;            
                document = Jsoup.connect(url).timeout(100000).get();
            }
	    	if(document != null){
		    	Element element = document.getElementById(properties.getProperty("flipkart.productdetail.hoderDivId"));
		    	if(element != null){
		    		Elements titleHoders = element.select(properties.getProperty("flipkart.productdetail.titleHolderDiv"));
		    		String productTile = titleHoders.first().child(0).text();
		    		if(titleHoders.first().children().size()>1){
		    			productTile += titleHoders.first().child(1).text() != null?titleHoders.first().child(1).text():"";
		    		}
		    		String productPrice = element.select("span.fk-font-verybig.pprice.fk-bold").text();
		    		
		    		String imageUrl = element.select("ul.ccarousel li span img").first().attr("data-carousel-src");
		    		
		    		product = new Product(productTile, productPrice, imageUrl, url,"");
		    		product.setProductId(query.getQueryString());
		    		product.setSiteName(SiteEnum.FLIPKART.name());
		    	}
	    	}
            
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	return product;
    }
    
    
    public List<Product> searchProductByName(String url,  Query query){
    	List<Product> productList = new ArrayList<Product>();
        try {
            System.out.println("Flipkart here");
            Document document = Jsoup.connect(url).timeout(100000).get();
            System.out.println("Flipkart here");
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("flipkart.fetchNumber"));
            }
            Elements elements = document.select(properties.getProperty("flipkart.product.holderDiv"));
            int count = 0;
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
	                product.setSiteName(SiteEnum.FLIPKART.name());
	                productList.add(product);
                }catch(Exception e){
                	e.printStackTrace();
                }

                //logger.log(s+" at price "+price+" image url "+img+" link to product "+prodLink);
            }
            if (elements == null || elements.isEmpty() == true) {
                elements = document.select(properties.getProperty("flipkart.product.productsDiv"));
                for (Element ele : elements) {
                    if (count == maximumProductsToFetch) break;
                    count++;
                    Element imageDiv = ele.select(properties.getProperty("flipkart.product.imageDiv")).first();
                    String image = imageDiv.attr(properties.getProperty("flipkart.product.imgAttrName"));
                    if (image.equals("")) {
                        image = imageDiv.attr(properties.getProperty("flipkart.product.imgAttrNameIfNot"));
                    }
                    Element titleDiv = ele.select(properties.getProperty("flipkart.product.titleDiv")).first();
                    String title = titleDiv.text();
                    Element priceDiv = ele.select(properties.getProperty("flipkart.product.priceDiv")).first();
                    String price = priceDiv.text();
                    
                    String productCategoryName = ele.select(properties.getProperty("flipkart.product.categoryDiv")).first().text();
                    productCategoryName = categoryMapping.get(productCategoryName) != null ? categoryMapping.get(productCategoryName): "";
                    String productLink = "http://www.flipkart.com" + titleDiv.attr("href");
                    Product product = new Product(title, price, image, productLink, productCategoryName);
                    product.setSiteName(SiteEnum.FLIPKART.name());
                    if (product != null) productList.add(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Flipkart exception");
            System.out.println(e.getClass().getName());
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            System.out.println(errors.toString());
            System.out.println("got exception");
        }
        
        return productList;
    }
   
}
