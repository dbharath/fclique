package net.fclique.crowler.model;

import java.util.List;

public class GroupedProduct {
	
	private String groupName;
	private List<Product> products;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
