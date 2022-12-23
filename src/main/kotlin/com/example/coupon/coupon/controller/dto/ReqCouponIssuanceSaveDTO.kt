package com.example.coupon.coupon.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ReqCouponIssuanceSaveDTO(

    @Min(value = 1)
    val memberId: Long,

    @Min(value = 1)
    val couponId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val enterDateTime: LocalDateTime
){
    fun getKey(): String{
        return "${couponId}-coupon"
    }
}