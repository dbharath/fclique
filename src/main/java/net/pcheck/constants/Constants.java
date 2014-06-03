package net.pcheck.constants;

public class Constants {
	

	public enum SiteEnum {		
		FLIPKART("001"),AMAZON("002"),
		EBAY("003"),HOMESHOP18("004"),
		TRADUS("005"),INFOBEAM("006"),
		NAAPTOL("007"),SHOPPINGINDIATIMES("008"),
		SNAPDEAL("009");
		String siteCode;
		SiteEnum(String siteCode){
			this.siteCode = siteCode;
		}
		
		public static SiteEnum getEnumByCode(String siteCode) {
			SiteEnum site = null;
			SiteEnum[] sites = SiteEnum.values();
			for (SiteEnum siteEnum : sites) {
				if (siteEnum.siteCode.equalsIgnoreCase(siteCode)){
					site = siteEnum;
				}
			}
			return site;
		}	
	}

	public enum SearchType{
		ByProductName("001"),ByProductId("002");
		String searchTypeCode;
		SearchType(String searchTypeCode){
			this.searchTypeCode = searchTypeCode;
		}		
	}
	public enum OfferType{
		BUYONEGETONE("001"),PRODUCT("002"),GENERIC("003"),DISCOUNT("004"),FLATOFF("005");
		String searchTypeCode;
		OfferType(String searchTypeCode){
			this.searchTypeCode = searchTypeCode;
		}		
	}
	public enum CouponType{
		FOOD("001"),MEN("002"),WOMEN("003"),KIDS("004"),ENTERTAINMENT("005"),GENERIC("006"),CLOTHS("007") ;
		String couponTypeCode;
		CouponType(String couponTypeCode){
			this.couponTypeCode = couponTypeCode;
		}		
	}
}
