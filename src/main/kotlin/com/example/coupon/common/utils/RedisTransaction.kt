package com.example.coupon.common.utils

import org.springframework.dao.DataAccessException
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.SessionCallback
import kotlin.jvm.Throws

object RedisTransaction {
    fun transaction(operations: RedisOperations<String, String>, command: (operation: RedisOperations<String, String>) -> Unit){
        operations.execute(object : SessionCallback<Void?> {
            @Throws(DataAccessException::class)
            override fun <K : Any?, V : Any?> execute(callbackOperations: RedisOperations<K, V>): Void? {

                callbackOperations.multi()
                command.invoke(operations)
                callbackOperations.exec()
                return null
            }
        })
    }
}