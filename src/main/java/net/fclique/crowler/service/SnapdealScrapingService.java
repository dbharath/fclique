package net.fclique.crowler.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.fclique.constants.Constants.SiteEnum;
import net.fclique.crowler.model.Product;
import net.fclique.crowler.model.Query;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by devesh.bharathan
 */
public class SnapdealScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {
        try {
            properties.load(SnapdealScrapingService.class.getClassLoader().getResourceAsStream("snapdeal.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> GetProductsByCategory(Query query, String category) {
        String productName = URLEncoder.encode(query.getQueryString());
        List<Product> productList = new ArrayList<Product>();
        int maxResults = 20;
        int page = (query.getPageNumber() - 1) * maxResults;
        String u = "http://www.snapdeal.com/json/product/get/search/" + category + "/" + page + "/" + maxResults + "?sort=rlvncy&keyword=" + productName;
        URL link = null;
        try {
            link = new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<LinkedHashMap<String, Object>>> jsonMap = null;
        try {
            jsonMap = mapper.readValue(link, Map.class);
            List<LinkedHashMap<String, Object>> products = jsonMap.get("productDtos");
            if (products == null) return productList;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("snapdeal.fetchNumber"));
            }
            
            int count = 0;
            for (LinkedHashMap<String, Object> item : products) {
                if (count == maximumProductsToFetch) break;
                count++;
                String name = item.get("name").toString();
                String price = item.get("displayPrice").toString();
                String image = "http://n.sdlcdn.com/" + item.get("image");
                String productLink = "http://www.snapdeal.com/" + item.get("pageUrl");
                category = categoryMapping.get(category) != null ? categoryMapping.get(category): "";
                Product product = new Product(name, price, image, productLink, category);
                product.setSiteName(SiteEnum.SNAPDEAL.name());
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;

    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
        String url = "http://www.snapdeal.com/search?keyword=";
        String productName = URLEncoder.encode(query.getQueryString());
        url += productName;
        url += "&santizedKeyword=&catId=&categoryId=0&suggested=false&vertical=&noOfResults=20&clickSrc=go_header&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&url=&utmContent=&catalogID=&dealDetail=";
        loadProperties();
        List<Product> productList = new ArrayList<Product>();

        try {
           Document document = Jsoup.connect(url).get();
           Element topElement = document.getElementById("products-main4");            
           String category = topElement.select("div.filter-prod-head > div.filter-prod-text").get(0).ownText();            
           Elements elements = topElement.select("div.product_grid_row > div.product_grid_cont.gridLayout4"); 
           for (Element productElement : elements) {
        	   try {
        		   String href = productElement.select("div.product-image > a").attr("href");
        		   String imgeUrl = productElement.select("div.product-image > a > img").attr("lazysrc");
        		   String productTitle = productElement.select("div.product-title >a").text();
        		   String price = productElement.select("div.product-price").first().ownText();
        		   Product product = new Product(productTitle,price,imgeUrl, href, category);
        		   product.setSiteName(SiteEnum.SNAPDEAL.name());
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
}
