package com.example.coupon.coupon.repository

import com.example.coupon.coupon.domain.CouponIssuance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponIssuanceRepository: JpaRepository<CouponIssuance, Long> {
}