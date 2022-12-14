package com.example.coupon.coupon.service

import com.example.coupon.common.utils.RedisTransaction
import com.example.coupon.common.utils.RedisUtil
import com.example.coupon.common.utils.executeCallBack
import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.domain.CouponIssuance
import com.example.coupon.coupon.feign.CouponFeignClient
import com.example.coupon.coupon.repository.CouponIssuanceRepository
import com.example.coupon.coupon.repository.CouponRepository
import com.fasterxml.jackson.databind.ObjectMapper
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.SessionCallback
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class CouponService(
    val couponRepository: CouponRepository,
    val redisUtil: RedisUtil,
    val redisTemplate: RedisTemplate<String, String>,
    val couponIssuanceRepository: CouponIssuanceRepository,
    val couponFeignClient: CouponFeignClient
){
    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 쿠폰 생성
     */
    fun saveCoupon(request: ReqCouponSaveDTO){
        couponRepository.save(request.toEntity())
    }

    /**
     * 쿠폰 발급
     */
    @Synchronized
    fun saveCouponIssuance(request: ReqCouponIssuanceSaveDTO) {
        val coupon = couponRepository.findByIdOrNull(request.couponId)
            ?: throw Exception("coupon is null")
        val stock = coupon.couponStock.totalStock

        redisTemplate.executeCallBack { operations ->
            operations.watch(request.getKey())
            val memberCount = redisUtil.memberCount(operations, request)
            if (validationTotalStock(stock, memberCount)) {
                operations.multi()
                redisUtil.memberCount(operations, request)
                redisUtil.memberAdd(operations, request)
                operations.exec()
            }
            val currentCount = redisUtil.memberCount(operations, request)
            if ( memberCount != currentCount){
                val data = LinkedHashMap<String,Any>()
                data["member_id"] = request.memberId
                data["coupon_id"] = request.couponId
                try {
                    couponFeignClient.couponSave("save_coupon", data)
                } catch (e: FeignException) {
                    log.info(e.message)
                    null
                }
            }
        }
    }

    fun feignTest(){
        val data = LinkedHashMap<String,Any>()
        data["member_id"] = 4
        data["coupon_id"] = 1
        try {
            couponFeignClient.couponSave("save_coupon", data)
        } catch (e: FeignException) {
            log.info(e.message)
            null
        }
    }

    private fun validationTotalStock(total: Long, current: Long): Boolean{
        return total > current
    }

}