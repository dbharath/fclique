package net.pcheck.mapper;

import java.util.ArrayList;
import java.util.List;

import net.paxcel.uibean.ProductUIBean;
import net.pcheck.modal.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProductMapper {
	public ProductUIBean toUIBean(Product product) {
		ProductUIBean uiBean = null;

		if (product != null) {
			uiBean = new ProductUIBean();
			uiBean.setBrand(product.getBrand());
			uiBean.setCashback(product.getCashback());
			uiBean.setCategory(product.getCategory());
			uiBean.setCodAvailable(product.getCodAvailable());
			uiBean.setDiscount(product.getDiscount());
			uiBean.setEmiAvailable(product.getEmiAvailable());
			uiBean.setImage1(product.getImage1());
			uiBean.setImage2(product.getImage2());
			uiBean.setImage3(product.getImage3());
			uiBean.setImage4(product.getImage4());
			uiBean.setImage5(product.getImage5());
			uiBean.setImage6(product.getImage6());
			uiBean.setImage7(product.getImage7());
			uiBean.setImage8(product.getImage8());
			uiBean.setMrp(product.getMrp());
			uiBean.setOffers(product.getOffers());
			uiBean.setPrice(product.getPrice());
			uiBean.setProductId(product.getProductId());
			uiBean.setProductName(product.getTitle());
			uiBean.setProductUrl(product.getProductUrl());			
		}

		return uiBean;
	}

	public List<ProductUIBean> toUIBean(List<Product> products) {
		List<ProductUIBean> uiBeans = new ArrayList<ProductUIBean>();

		for (Product product : products) {
			uiBeans.add(toUIBean(product));
		}
		return uiBeans;
	}

	public Page<ProductUIBean> toUIBean(Page<Product> products, Pageable pageable) {
		Page<ProductUIBean> uiBeans = new PageImpl<ProductUIBean>(
				toUIBean(products.getContent()), pageable,
				products.getTotalElements());

		return uiBeans;
	}

	

}
