package net.pcheck.crowler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import net.pcheck.constants.Constants.SiteEnum;
import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.Query;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by vivek.gupta
 */
public class NaaptolScrapingService extends ScrapingService {
    private Properties properties = new Properties();

    private void loadProperties() {

        try {
            properties.load(NaaptolScrapingService.class.getClassLoader().getResourceAsStream("naaptol.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> runSiteRoutine(Query query) {
        String urlString = "http://www.naaptol.com/faces/jsp/ajax/ajax.jsp?type=srch_catlg&kw=";
        urlString += query.getQueryString();
        int page = query.getPageNumber();
        urlString += "&pi=" + page + "&requesttype=ajaxNextPage&actionname=getCatlogNextPageData";
        List<Product> productList = new ArrayList<Product>();
        loadProperties();

        URL url = null;
        try {
            url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String responseString = "";
            String current;
            while ((current = in.readLine()) != null) {
                responseString += current;
            }
            ObjectMapper mapper = new ObjectMapper();

            List<LinkedHashMap<String, Object>> jsonMap = mapper.readValue(responseString, List.class);
            List<LinkedHashMap<String, Object>> products = jsonMap;
            if (products == null) return productList;
            int maximumProductsToFetch = query.getMaxProductToFetch();
            if(query.getMaxProductToFetch() == null || query.getMaxProductToFetch() == 0){
            	maximumProductsToFetch = Integer.parseInt(properties.getProperty("naaptol.fetchNumber"));
            }            
            int count = 0;
            for (LinkedHashMap<String, Object> item : products) {
                if (count == maximumProductsToFetch) break;
                count++;
                String name = item.get("productName") != null ? item.get("productName").toString() : "";
                String price = item.get("productPrice") != null ? item.get("productPrice").toString() : "";
                String image = item.get("imageName") != null ? "http://images.naaptol.com/usr/local/csp/staticContent/NormImg105x105/" + item.get("imageName") : "";
                String staticUrl = item.get("staticUrl") != null ? item.get("staticUrl").toString().toLowerCase() : "";
                String productCategoryName = item.get("productCategory")!= null ? item.get("productCategory").toString(): "";
                staticUrl = staticUrl.replace(' ', '-');
                String categoryStaticUrl = item.get("categoryStaticUrl") != null ? item.get("categoryStaticUrl").toString().toLowerCase() : "";
                categoryStaticUrl = categoryStaticUrl.replace(' ', '-');
                String productId = item.get("productId") != null ? item.get("productId").toString() : "";
                String productLink = "http://www.naaptol.com/" + categoryStaticUrl + "/" + staticUrl + "/p/" + productId + ".html";
                productCategoryName = categoryMapping.get(productCategoryName) != null ? categoryMapping.get(productCategoryName): "";
                Product product = new Product(name, price, image, productLink, productCategoryName);
                product.setSiteName(SiteEnum.NAAPTOL.name());
                productList.add(product);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return productList;
    }
    
    
}
