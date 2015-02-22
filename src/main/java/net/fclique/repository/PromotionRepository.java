package net.fclique.repository;

import net.fclique.modal.Promotion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	/*@Query("select promo from promotion promo where coupon_type = :type")
	Page<Promotion> findByCouponType(@Param("type") String type, Pageable pageable);*/
	Page<Promotion> findByCouponIsNotNullAndCouponType(String couponType,Pageable page);
	Page<Promotion> findByCouponIsNotNullAndMerchantName(String merchant,Pageable page);
}
