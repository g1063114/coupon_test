package com.example.coupon.coupon.service

import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.repository.CouponRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class CouponService(
    val couponRepository: CouponRepository,
    val redisTemplate: RedisTemplate<String, String>
){
    /**
     * 쿠폰 생성
     */
    fun saveCoupon(request: ReqCouponSaveDTO){
        couponRepository.save(request.toEntity())
    }

    /**
     * 쿠폰 발급
     */
    fun saveCouponIssuance(request: ReqCouponIssuanceSaveDTO){
        redisTemplate.opsForValue().set(request.key, request.value)
    }
}