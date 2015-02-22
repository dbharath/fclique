package net.fclique.crowler.model;

import java.util.List;

/**
 * Created by devesh.bharathan
 */
public class SiteResponse
{
    private String siteName;
    private List<Product> products;
    public SiteResponse(String siteName, List<Product> products) {
        this.siteName = siteName;
        this.products = products;
    }
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
	    
}
