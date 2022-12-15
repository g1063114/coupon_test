package com.example.coupon.coupon.domain

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coupon.coupon_issuance")
@DynamicInsert
@DynamicUpdate
class CouponIssuance(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "member_id")
    val memberId: Long,

    @Column(name = "coupon_id")
    val couponId: Long,

    @Column(name = "issuanced_at", nullable = false)
    val issuancedAt: LocalDateTime,

    @Column(name = "expired_at", nullable = false)
    val expiredAt: LocalDateTime,

    @Column(name = "use_flag", nullable = false)
    val useFlag: Boolean,
)