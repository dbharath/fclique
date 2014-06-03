package net.pcheck.service;

import java.util.List;

import net.pcheck.modal.Promotion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {	
	List<Promotion> findAll();
	Page<Promotion> findAll(Pageable pageable);	
	Page<Promotion> findCouponByCouponType(Pageable pageable, String couponType); 
	Page<Promotion> findCouponByMerchant(Pageable pageable, String merchant);
}
