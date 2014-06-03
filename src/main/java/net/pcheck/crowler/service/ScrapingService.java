package net.pcheck.crowler.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.Query;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by vivek.gupta
 */
public abstract class ScrapingService {
    Map<Query, List<Product>> productsCache = new ConcurrentHashMap<Query, List<Product>>();
    public static Date currentTime = new Date();
    public abstract List<Product> runSiteRoutine(Query query);
    public static Map<String, String> categoryMapping = new HashMap<String, String>();
    static {
    	categoryMapping.put("Mobiles", "Mobiles");
    	categoryMapping.put("Mobile", "Mobiles");
    	categoryMapping.put("Phablets", "Mobiles");
    	categoryMapping.put("IN_Mobile_Phones", "Mobiles");
    	categoryMapping.put("mobile-phones", "Mobiles");
    	categoryMapping.put("IN_Mobile_Accessories", "Mobile Accessories");
    	categoryMapping.put("mobile-phone-accessories", "Mobile Accessories");    	
    	categoryMapping.put("Cases Covers", "Mobile Accessories");
    	categoryMapping.put("Cases And Covers", "Mobile Accessories");
    	categoryMapping.put("Mobile_Accessories", "Mobile Accessories");
    	categoryMapping.put("Hands Free", "Mobile Accessories");
    	categoryMapping.put("Carry Cases", "Mobile Accessories");
    	categoryMapping.put("Screen Protectors", "Mobile Accessories");
    	categoryMapping.put("Batteries", "Batteries");
    	categoryMapping.put("Earphones And Headsets", "Mobile Accessories");
    }
    

    public List<Product> getProductsForPage(Query query) {

        if (productsCache.containsKey(query) && productsCache.get(query) != null && productsCache.get(query).size() > 0) {
            return productsCache.get(query);
        }

        List<Product> products = runSiteRoutine(query);
        if (products != null && products.size() > 0)
            productsCache.put(query, products);

        return sanitize(products);
    }
    
    private List<Product> sanitize(List<Product> products)
    {
        List<Product> ret = new ArrayList<Product>(0);
        for(Product product: products)
        {
            if(product.getPrice().equals("") || product.getPrice().equals("Rs.") || product.getPrice().equals("Rs"))
                continue;
            if(product.getName().equals(""))
                continue;
            ret.add(product);
        }
        return ret;
    }

    public Document manualRedirectHandler(String url) throws IOException   {
        Response response = Jsoup.connect(url.replaceAll(" ", "%20")).followRedirects(false).execute();
        int status = response.statusCode();
        
        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM 
        		|| status == HttpURLConnection.HTTP_SEE_OTHER){        
            String redirectUrl = response.header("location");
            System.out.println("Redirect to: " + redirectUrl);
            url = response.url().getProtocol()+"://"+response.url().getHost()+redirectUrl;            
            return Jsoup.connect(url).timeout(10000).get();
        }

        return Jsoup.parse(response.body());
    }
}
