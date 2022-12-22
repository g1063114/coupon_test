package com.example.coupon.common.utils

import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import org.springframework.dao.DataAccessException
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.SessionCallback
import org.springframework.stereotype.Component

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<Any, Any>
){
    fun count(operations: RedisOperations<Any, Any>, dto: ReqCouponIssuanceSaveDTO): Long{
        val key = dto.getKey()
        return operations.opsForSet().size(key) ?: 0
    }

    fun add(operations: RedisOperations<Any, Any>, dto: ReqCouponIssuanceSaveDTO): Long{
        val key = dto.getKey()
        return operations.opsForSet().add(key, dto.memberId.toString()) ?: 0
    }


}