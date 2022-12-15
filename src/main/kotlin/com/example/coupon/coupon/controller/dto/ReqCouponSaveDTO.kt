package com.example.coupon.coupon.controller.dto

import com.example.coupon.coupon.domain.Coupon
import com.example.coupon.coupon.domain.CouponStock
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ReqCouponSaveDTO(

    @NotBlank
    val name: String,

    val description: String? = null,

    @Min(value = 1)
    val validDate: Int,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val couponStartDateTime: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val couponEndDateTime: LocalDateTime,

    @Min(value = 1)
    val totalStock: Long
){
    fun toEntity(): Coupon{
        return Coupon(
            name = this.name,
            description = this.description,
            validDate = this.validDate,
            couponStartDateTime = this.couponStartDateTime,
            couponEndDateTime = this.couponEndDateTime,
            couponStock = CouponStock(totalStock = this.totalStock)
        )
    }
}