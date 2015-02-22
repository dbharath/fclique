package net.fclique.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.paxcel.csv.eader.CSVConverter;
import net.fclique.modal.Product;
import net.fclique.modal.Promotion;
import net.fclique.service.ProductService;
import net.fclique.utils.UtilDateTime;
import net.fclique.utils.UtilValidate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ibm.icu.text.SimpleDateFormat;

@Controller
@RequestMapping("/csvupload")
public class CSVUploadController {
	
	@Resource
	private ProductService productService;
	
	
	@RequestMapping(value = "/showUpload", method = RequestMethod.GET) 
    public String upload(Model model) {
		model.addAttribute("actionUrl", "upload");
        return "/upload";
    }
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		 Iterator<String> itr =  request.getFileNames();
		 MultipartFile mpf = null;
		 List<Map<String, String>> promotionsMap =  null;		
		 while(itr.hasNext()){		
			try {
				mpf = request.getFile(itr.next()); 
				promotionsMap =  CSVConverter.readCSVToMap(mpf.getInputStream());
				String uploadType = request.getParameter("uploadType");
				uploadType = "001";
				if(uploadType.equals("001")){
					uploadProducts(promotionsMap);
				}else if(uploadType.equals("002")){
					uploadPromotions(promotionsMap);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }		
		return "";
 
	}

	private void uploadPromotions( List<Map<String, String>> promotionsMap){
		List<Promotion> promotions = new ArrayList<Promotion>();
		for (Map<String, String> map : promotionsMap) {
			if(UtilValidate.isNotEmpty(map.get("offerUrl"))){
				Promotion promotion = new Promotion();
				if(UtilValidate.isNotEmpty(map.get("startTime"))){
					SimpleDateFormat format = new SimpleDateFormat();
					try {
						format.parse(map.get("startTime"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					promotion.setStartTime(UtilDateTime.toDate(map.get("startTime")));
				}
				if(UtilValidate.isNotEmpty(map.get("endTime"))){
					promotion.setEndTime(UtilDateTime.toDate(map.get("endTime")));
				}					
				if(UtilValidate.isNotEmpty(map.get("offerName"))){
					promotion.setOfferName(map.get("offerName"));
				}else {
					promotion.setOfferName("");
				}

				if(UtilValidate.isNotEmpty(map.get("offerDescription"))){
					promotion.setOfferDescription(map.get("offerDescription"));;
				}else{
					promotion.setOfferDescription("");;
				}

				if(UtilValidate.isNotEmpty(map.get("offerCondition"))){
					promotion.setOfferCondition(map.get("offerCondition"));
				}else {
					promotion.setOfferCondition("");
				}

				if(UtilValidate.isNotEmpty(map.get("img730x150"))){
					promotion.setImg730x150(map.get("img730x150"));
				}else {
					promotion.setImg730x150("");
				}
				if(UtilValidate.isNotEmpty(map.get("img482x234"))){
					promotion.setImg482x234(map.get("img482x234"));
				}else {
					promotion.setImg482x234("");
				}
				if(UtilValidate.isNotEmpty(map.get("img720x280"))){
					promotion.setImg720x280(map.get("img720x280"));
				}else {
					promotion.setImg720x280("");
				}
				if(UtilValidate.isNotEmpty(map.get("img540x210"))){
					promotion.setImg540x210(map.get("img540x210"));
				}else {
					promotion.setImg540x210("");
				}
				if(UtilValidate.isNotEmpty(map.get("img360x140"))){
					promotion.setImg360x140(map.get("img360x140"));
				}else {
					promotion.setImg360x140("");
				}
				if(UtilValidate.isNotEmpty(map.get("img270x105"))){
					promotion.setImg270x105(map.get("img270x105"));
				}else {
					promotion.setImg270x105("");
				}
				promotions.add(promotion);
			}
			
		}
		
	}
	private void uploadProducts(List<Map<String, String>> promotionsMap){
		String category = "Mobiles";
		String merchantName = "Flipkart";
		List<Product> promotions = new ArrayList<Product>();
		for (Map<String, String> map : promotionsMap) {
			if(UtilValidate.isNotEmpty(map.get("inStock")) && Boolean.parseBoolean(map.get("inStock"))){
				
				Product promotion = new Product();
				promotion.setMerchantName(merchantName);		
				if(UtilValidate.isNotEmpty(map.get("productId"))){							
					promotion.setProductId(map.get("productId"));
				}
									
				if(UtilValidate.isNotEmpty(map.get("title"))){
					promotion.setTitle(map.get("title"));
				}else {
					promotion.setTitle("");
				}
				promotion.setImage1("");
				promotion.setImage2("");
				promotion.setImage3("");
				promotion.setImage4("");
				promotion.setImage5("");
				promotion.setImage6("");
				promotion.setImage7("");
				promotion.setImage8("");
				if(UtilValidate.isNotEmpty(map.get("imageUrlStr"))){
					String[] imageUrls = map.get("imageUrlStr").split(",");
					for(int i=0; i<imageUrls.length; i++){
						if(UtilValidate.isNotEmpty(imageUrls[i])){
							Method method;
							try {
								method = promotion.getClass().getMethod("setImage"+(i+1), String.class);
								method.invoke(promotion, imageUrls[i]);		
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}					
					}
				}
				if(UtilValidate.isNotEmpty(map.get("productBrand"))){
					promotion.setBrand(map.get("productBrand"));
				}else {
					promotion.setBrand("");
				}
				
				if(UtilValidate.isNotEmpty(map.get("price"))){
					promotion.setPrice(Double.valueOf(map.get("price")));
				}else {
					promotion.setPrice(0.0);
				}
				if(UtilValidate.isNotEmpty(map.get("mrp"))){
					promotion.setMrp(Double.valueOf(map.get("mrp")));
				}else {
					promotion.setMrp(0.0);
				}
				if(UtilValidate.isNotEmpty(map.get("productUrl"))){
					promotion.setProductUrl(map.get("productUrl"));
				}else {
					promotion.setProductUrl("");
				}
				promotion.setCategory(category);
								
				if(UtilValidate.isNotEmpty(map.get("cashBack"))){
					promotion.setCashback(map.get("cashBack"));
				}else {
					promotion.setCashback("");
				}
				if(UtilValidate.isNotEmpty(map.get("discount"))){
					String discount = map.get("discount");
					discount = discount.replace("%", "").trim();					
					promotion.setDiscount(discount);
				}else {
					promotion.setDiscount("");
				}
				if(UtilValidate.isNotEmpty(map.get("offers"))){
					String offers = map.get("offers");
					if(offers.equalsIgnoreCase("None") || offers.equalsIgnoreCase("non")){
						promotion.setOffers(null);
					}else {
						promotion.setOffers(map.get("offers"));
					}					
				}else {
					promotion.setOffers(null);
				}
				if(UtilValidate.isNotEmpty(map.get("emiAvailable"))){
					promotion.setEmiAvailable(map.get("emiAvailable"));
				}else {
					promotion.setEmiAvailable("");
				}
				if(UtilValidate.isNotEmpty(map.get("codAvailable"))){
					promotion.setCodAvailable(map.get("codAvailable"));
				}else {
					promotion.setCodAvailable("");
				}
				promotions.add(promotion);
			}			
		} 
		productService.createProducts(promotions);
	}
}
