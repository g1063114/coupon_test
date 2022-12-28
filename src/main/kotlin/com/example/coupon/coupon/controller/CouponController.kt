package com.example.coupon.coupon.controller

import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.controller.dto.ReqCouponSaveDTO
import com.example.coupon.coupon.service.CouponService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/coupon")
class CouponController(
    val couponService: CouponService,
){
    @PostMapping
    fun saveCoupon(
        @Valid @RequestBody request: ReqCouponSaveDTO
    ){
        couponService.saveCoupon(request)
    }

    @PostMapping("/issuance")
    fun saveCouponIssuance(
        @Valid @RequestBody request: ReqCouponIssuanceSaveDTO
    ){
        couponService.saveCouponIssuance(request)
    }

    @GetMapping
    fun localConnectionTest(): String{
        return "connenction"
    }

    @PostMapping("/feign")
    fun feignTest(){
        couponService.feignTest()
    }
}