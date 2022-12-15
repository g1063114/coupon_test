package com.example.coupon.coupon.service

import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class CouponService(
    val couponRepository: CouponRepository,
){
    /**
     * 쿠폰 생성
     */
    fun saveCoupon(request: ReqCouponSaveDTO){
        couponRepository.save(request.toEntity())
    }


}