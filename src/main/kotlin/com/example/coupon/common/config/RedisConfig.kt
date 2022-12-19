package com.example.coupon.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig(

    @Value("\${spring.redis.host}")
    private val host: String,

    @Value("\${spring.redis.port}")
    private val port: Int
){

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory{
        return LettuceConnectionFactory(
            RedisStandaloneConfiguration(
                host, port
            )
        )
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *>{
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}