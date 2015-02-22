package net.pcheck.crowler.service;

import java.util.ArrayList;
import java.util.List;

import net.pcheck.crowler.model.Product;
import net.pcheck.crowler.model.SiteResponse;

/**
 * Created by devesh.bharathan
 */
public class AutoSuggestion
{
    private List<SiteResponse> siteResponses;

    public AutoSuggestion(List<SiteResponse> siteResponses)
    {
        this.siteResponses = siteResponses;
    }

   
    public List<Product> getSuggestedList()
    {
        List<Integer> accuratePrices = new ArrayList<Integer>();
        List<Product> ret = new ArrayList<Product>();
        for(int i=0; i < siteResponses.size(); i++) {
            SiteResponse currentResponse = siteResponses.get(i);
            List<Product> currentProducts = currentResponse.getProducts();
            if(currentProducts != null) {
	            for(int j=0; j < currentProducts.size(); j++) {
	                accuratePrices.add(sanitizePrice(currentProducts.get(j).getPriceLabel()));
	            }
            }
        }
        int standardDev = standardDeviation(accuratePrices, average(accuratePrices));
        int minPrice = average(accuratePrices) - 2 * standardDev;

        for(SiteResponse response : siteResponses) {
        	List<Product> products = response.getProducts();
        	if(products != null) {
	            for(Product product : products) {	                
	                int price = sanitizePrice(product.getPriceLabel());
	                if(minPrice <= price) {
	                    ret.add(product);
	                }
	            }
        	}
        }
        
       // Collections.sort(ret, Product.priceComparator);
        return ret;
    }

    private Integer sanitizePrice(String priceString)
    {
        priceString = priceString.replaceAll("\\s+", "");
        priceString = priceString.replaceAll("\\u00A0", "");
        priceString = priceString.replace(",", "");
        priceString = priceString.replace("Rs.", "");
        priceString = priceString.replace("Rs", "");

        return Double.valueOf(priceString).intValue();
    }

    private Integer average(List<Integer> prices)
    {
        int temp = 0;
        for(Integer price : prices)
        {
            temp += price;
        }
        return temp/prices.size();
    }

    private Integer standardDeviation(List<Integer> prices, Integer average)
    {
        Long temp = 0L, variance;
        for(Integer price : prices)
        {
            Double ex = Math.pow(price - average, 2);
            temp += ex.intValue();
        }
        variance = temp/prices.size();
        return ((Double)Math.pow(variance, 0.5)).intValue();
    }
}
