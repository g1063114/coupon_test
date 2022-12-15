package com.example.coupon.coupon.domain

import com.example.coupon.common.domain.BaseDeleteEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "coupon.coupon_stock")
@DynamicInsert
@DynamicUpdate
class CouponStock(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "total_stock", nullable = false)
    var totalStock: Long,

): BaseDeleteEntity()