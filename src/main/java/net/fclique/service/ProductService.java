package net.fclique.service;

import java.util.List;

import net.paxcel.uibean.ProductUIBean;
import net.fclique.modal.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
	void createProducts(List<Product> products);
	List<ProductUIBean> findAll();
	Page<ProductUIBean> findAll(Pageable pageable);

}
