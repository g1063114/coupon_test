package com.example.coupon.coupon.controller.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ReqCouponIssuanceSaveDTO(

    @NotBlank
    val key: String,

    @NotBlank
    val value: String
)