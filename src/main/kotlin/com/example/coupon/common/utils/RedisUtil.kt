package com.example.coupon.common.utils

import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.SessionCallback
import org.springframework.stereotype.Component

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, String>
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun totalStockAdd(operations: RedisOperations<String, String>, stock: Long){
        val key = "stock:total"
        operations.opsForSet().add(key, stock.toString())
    }
    fun memberCount(operations: RedisOperations<String, String>, dto: ReqCouponIssuanceSaveDTO): Long{
        val key = dto.getKey()
        return operations.opsForSet().size(key) ?: 0
    }

    fun memberAdd(operations: RedisOperations<String, String>, dto: ReqCouponIssuanceSaveDTO){
        val key = dto.getKey()
        operations.opsForSet().add(key, dto.memberId.toString())
    }
}
inline fun <reified K : Any, reified V: Any, reified T> RedisTemplate<K, V>.executeCallBack(crossinline callback: (RedisOperations<K, V>) -> T): T{
    val callback = object : SessionCallback<T> {
        override fun <KK, VV> execute(operations: RedisOperations<KK,VV>) = callback(operations as RedisOperations<K, V>) as T
    }
    return execute(callback)
}