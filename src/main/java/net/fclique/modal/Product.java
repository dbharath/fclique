package net.fclique.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String productId;
	@Column(length = 800)
	private String title;
	@Column(length = 800)
	private String image1;
	@Column(length = 800)
	private String image2;
	@Column(length = 800)
	private String image3;
	@Column(length = 800)
	private String image4;
	@Column(length = 800)
	private String image5;
	@Column(length = 800)
	private String image6;
	@Column(length = 800)
	private String image7;
	@Column(length = 800)
	private String image8;
	@Column(length = 800)
	private String productUrl;
	private String brand;
	private String category;
	private Double mrp;
	private Double price;
	@Column(length = 1200)
	private String offers;
	private String discount;
	private String cashback;
	private String codAvailable;
	private String emiAvailable;
	private String merchantName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getImage6() {
		return image6;
	}
	public void setImage6(String image6) {
		this.image6 = image6;
	}
	public String getImage7() {
		return image7;
	}
	public void setImage7(String image7) {
		this.image7 = image7;
	}
	public String getImage8() {
		return image8;
	}
	public void setImage8(String image8) {
		this.image8 = image8;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getOffers() {
		return offers;
	}
	public void setOffers(String offers) {
		this.offers = offers;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCashback() {
		return cashback;
	}
	public void setCashback(String cashback) {
		this.cashback = cashback;
	}
	public String getCodAvailable() {
		return codAvailable;
	}
	public void setCodAvailable(String codAvailable) {
		this.codAvailable = codAvailable;
	}
	public String getEmiAvailable() {
		return emiAvailable;
	}
	public void setEmiAvailable(String emiAvailable) {
		this.emiAvailable = emiAvailable;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	
		


}
