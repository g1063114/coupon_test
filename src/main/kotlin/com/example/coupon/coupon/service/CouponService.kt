package com.example.coupon.coupon.service

import com.example.coupon.common.utils.RedisUtil
import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.repository.CouponRepository
import org.springframework.dao.DataAccessException
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.SessionCallback
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CouponService(
    val couponRepository: CouponRepository,
    val redisUtil: RedisUtil,
    val redisTemplate: RedisTemplate<Any, Any>
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
        val coupon = couponRepository.findByIdOrNull(request.couponId)
        val stock = coupon?.couponStock?.totalStock


    }


}