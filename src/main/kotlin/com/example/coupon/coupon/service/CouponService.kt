package com.example.coupon.coupon.service

import com.example.coupon.common.utils.RedisTransaction
import com.example.coupon.common.utils.RedisUtil
import com.example.coupon.common.utils.executeCallBack
import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.domain.CouponIssuance
import com.example.coupon.coupon.repository.CouponIssuanceRepository
import com.example.coupon.coupon.repository.CouponRepository
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
    val couponIssuanceRepository: CouponIssuanceRepository
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
    fun saveCouponIssuance(request: ReqCouponIssuanceSaveDTO){
        val coupon = couponRepository.findByIdOrNull(request.couponId)
            ?: throw Exception("coupon is null")
        val stock = coupon.couponStock.totalStock

        redisTemplate.executeCallBack { operations ->
//            if ( validationTotalStock(stock, redisUtil.memberCount(operations, request)) ) {
//                operations.watch(request.getKey())
//                operations.multi()
//                redisUtil.memberCount(operations, request)
//                redisUtil.memberAdd(operations, request)
//                operations.exec()
//            }
            operations.watch(request.getKey())
            redisUtil.totalStockAdd(operations, stock)
            val memberCount = redisUtil.memberCount(operations, request)
            log.info("=====================Member Count : ${memberCount}======================")
            operations.multi()
            redisUtil.memberAdd(operations, request)
            operations.exec()
            val addCount = redisUtil.memberCount(operations, request)
            log.info("=====================Add Count : ${addCount}======================")
            if ( memberCount != addCount ){
                couponIssuanceRepository.save(
                    CouponIssuance(
                        memberId = request.memberId,
                        couponId = request.couponId,
                        issuancedAt = LocalDateTime.now(),
                        expiredAt = coupon.couponStartDateTime.plusDays(coupon.validDate.toLong()),
                        useFlag = false
                    )
                )
            }
        }

//        redisTemplate.opsForSet().add("stock:count", stock.toString())
//        val size = redisTemplate.opsForSet().size("1:coupon")
//        redisTemplate.opsForSet().add("1:coupon", request.memberId.toString())
//        if ( size != redisTemplate.opsForSet().size("1:coupon") ){
//            RedisTransaction.transaction(redisTemplate) {
//
//            }
//        }
//       if ( validationTotalStock(stock, redisUtil.totalStockCount(redisTemplate)) ){
//            RedisTransaction.transaction(redisTemplate){
//                redisUtil.totalStockAdd(it, stock)
//                redisUtil.memberAdd(it, request)
//                val totalStockCount = redisUtil.totalStockCount(it)
//                log.info("COUNT : ${totalStockCount}")
//                couponIssuanceRepository.save(CouponIssuance(
//                        memberId = request.memberId,
//                        couponId = request.couponId,
//                        issuancedAt = LocalDateTime.now(),
//                        expiredAt = coupon.couponStartDateTime.plusDays(coupon.validDate.toLong()),
//                        useFlag = false
//                    ))
//
//            }
//        }
    }

    private fun validationTotalStock(total: Long, current: Long): Boolean{
        return total > current
    }

}