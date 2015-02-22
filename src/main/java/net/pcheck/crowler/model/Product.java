package net.pcheck.crowler.model;

import java.util.Comparator;

/**
 * Created by devesh.bharathan
 */
public class Product
{
	private String name;
	private String priceLabel;
	private String imageUrl;
	private String productLink;
    private String siteName;
    private Double price;
    private String productCategoryName;
    private String productId;
    public Product(){
    	
    }
	public Product(String name, String price, String imageUrl, String productLink, String productCategoryName)
	{
		this.name = name;
		this.priceLabel = price;
		this.price = sanitizePrice(price);;
		this.imageUrl = imageUrl;
		this.productLink = productLink;
		this.productCategoryName = productCategoryName;
	}

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}
	
	public void setPriceLabel(String priceLabel)
	{
		this.priceLabel = priceLabel;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public void setProductLink(String productLink)
	{
		this.productLink = productLink;
	}

	public String getName()
	{

		return name;
	}

	public String getPriceLabel() {
		return priceLabel;
	}	
	public Double getPrice() {
		return price;
	}

	public String getImageUrl()	{
		return imageUrl;
	}

	public String getProductLink()	{
		return productLink;
	}	
	
	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String toString()
	{
		return getName() + "|" + getPriceLabel() + "|" + getProductLink() + "|" + getImageUrl();
	}
	
	@Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass())
            result = false;
        else {
        	Product product = (Product) object;
            if (this.name.equals(product.name) && this.siteName.equals(product.siteName)
            		&& (this.productId != null && this.productId.equals("") && product.productId != null && product.productId.equals(""))
            		&& (this.productCategoryName != null && this.productCategoryName.equals("") && product.productCategoryName != null && product.productCategoryName.equals(""))){
            	result = true;            	
            }   
        }
        return result;

    }

    @Override
    public int hashCode() {
    	Integer hashCode = this.name.hashCode() + this.price.hashCode() + this.siteName.hashCode();
    	if(this.productId != null && this.productId.equals("")){
    		hashCode+= this.productId.hashCode();
    	}
    	if(this.productCategoryName != null && this.productCategoryName.equals("")){
    		hashCode+= this.productCategoryName.hashCode();
    	}   
    	return hashCode;
    }
	
	public static Comparator<Product> priceComparator = new Comparator<Product>() {

        @Override
        public int compare(Product e1, Product e2) {
            return (int) (e1.getPrice() - e2.getPrice());
        }
    };
    
    public static Comparator<Product> categoryNameComparator = new Comparator<Product>() {

        @Override
        public int compare(Product e1, Product e2) {
        	int lastCmp = e1.getProductCategoryName().compareTo(e2.getProductCategoryName());            
            return lastCmp;
        }
    };
    
    public static Comparator<Product> categoryNameThenPriceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product e1, Product e2) {
        	int lastCmp = e1.getProductCategoryName().compareTo(e2.getProductCategoryName());
            if (lastCmp != 0) {
               return lastCmp;
            } else {
               Double x1 = e1.getPrice();
               Double x2 = e2.getPrice();
               return x1.compareTo(x2);
            }
        }
    };
    
    
    
	
	private Double sanitizePrice(String priceString) {
        priceString = priceString.replaceAll("\\s+", "");
        priceString = priceString.replaceAll("\\u00A0", "");
        priceString = priceString.replace(",", "");
        priceString = priceString.replace("Rs.", "");
        priceString = priceString.replace("Rs", "");
        if(priceString.equalsIgnoreCase("N/A")){
        	return new Double(0.0);
        }
        return Double.valueOf(priceString);
    }
}
