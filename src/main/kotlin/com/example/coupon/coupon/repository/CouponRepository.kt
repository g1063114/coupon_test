package com.example.coupon.coupon.repository

import com.example.coupon.coupon.domain.Coupon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponRepository: JpaRepository<Coupon, Long> {
}