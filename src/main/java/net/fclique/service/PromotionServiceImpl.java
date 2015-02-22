package net.fclique.service;

import java.util.List;

import javax.annotation.Resource;

import net.fclique.modal.Promotion;
import net.fclique.repository.PromotionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Resource
	private PromotionRepository  promotionRepository;
	 
	@Override
	@Transactional	
	public List<Promotion> findAll() {
		return promotionRepository.findAll();		
	}

	@Override
	public Page<Promotion> findAll(Pageable pageable) {
		return promotionRepository.findAll(pageable);		 
	}

	@Override
	public Page<Promotion> findCouponByCouponType(Pageable pageable, String couponType) {		
		return promotionRepository.findByCouponIsNotNullAndCouponType(couponType,pageable);
	}
	@Override
	public Page<Promotion> findCouponByMerchant(Pageable pageable, String merchant) {		
		return promotionRepository.findByCouponIsNotNullAndMerchantName(merchant,pageable);
	}
	
	/*@Override
	public Page<Promotion> findAll(Pageable pageable, List<FilterRequest> filters) {
		Predicate predicate = toPredicate(filters);
		return promotionRepository.findAll(predicate, pageable);		 
	}*/
	
	
}
