package net.fclique.modal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promotion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
	private String merchantName;
	private String merchantUrl;
	private Date startTime;
	private Date endTime;
	private String offerUrl;
	private String offerName;
	private String offerDescription;
	private String offerCondition;
	private String img730x150;
	private String img482x234;
	private String img720x280;
	private String img540x210;
	private String img360x140;
	private String img270x105;
	private String coupon;	
	private String discount;
	private String city;
	private String state;
	private String offerType;
	private String couponType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getOfferUrl() {
		return offerUrl;
	}
	public void setOfferUrl(String offerUrl) {
		this.offerUrl = offerUrl;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public String getOfferCondition() {
		return offerCondition;
	}
	public void setOfferCondition(String offerCondition) {
		this.offerCondition = offerCondition;
	}
	public String getImg730x150() {
		return img730x150;
	}
	public void setImg730x150(String img730x150) {
		this.img730x150 = img730x150;
	}
	public String getImg482x234() {
		return img482x234;
	}
	public void setImg482x234(String img482x234) {
		this.img482x234 = img482x234;
	}
	public String getImg720x280() {
		return img720x280;
	}
	public void setImg720x280(String img720x280) {
		this.img720x280 = img720x280;
	}
	public String getImg540x210() {
		return img540x210;
	}
	public void setImg540x210(String img540x210) {
		this.img540x210 = img540x210;
	}
	public String getImg360x140() {
		return img360x140;
	}
	public void setImg360x140(String img360x140) {
		this.img360x140 = img360x140;
	}
	public String getImg270x105() {
		return img270x105;
	}
	public void setImg270x105(String img270x105) {
		this.img270x105 = img270x105;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMerchantUrl() {
		return merchantUrl;
	}
	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
		
}
