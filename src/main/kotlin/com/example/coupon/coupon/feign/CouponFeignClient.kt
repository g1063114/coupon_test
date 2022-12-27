package com.example.coupon.coupon.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "coupon", url = "\${feign.target-api.coupon}")
interface CouponFeignClient {

    @PostMapping("/api/producer/send/{topic}")
    fun couponSave(
        @PathVariable topic: String,
        @RequestBody msg: LinkedHashMap<String, Any>
    )
}